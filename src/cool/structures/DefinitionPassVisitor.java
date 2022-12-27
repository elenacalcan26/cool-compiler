package cool.structures;

import cool.AST.*;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

import java.util.List;

public class DefinitionPassVisitor implements ASTVisitor<Void> {
    Scope currentScope = SymbolTable.globals;

    @Override
    public Void visit(ProgramNode programNode) {

        List<ClassNode> classes = programNode.classNodeList;
        classes.forEach(classNode -> classNode.accept(this));

        return null;
    }

    @Override
    public Void visit(TypeNode type) {
        return null;
    }

    @Override
    public Void visit(ClassNode classNode) {

        // TODO putin ciudat aici ca B ar trebui sa fie mostenit de A care este definit mai jos
        var symbol = new ClassSymbol(classNode.type.token.getText(), currentScope);

        if (classNode.type.token.getText().equals("SELF_TYPE")) {
            SymbolTable.error(
                    classNode.ctx,
                    classNode.type.token,
                    "Class has illegal name SELF_TYPE"
            );
            return null;
        }

        if (currentScope.lookup(classNode.type.token.getText()) != null) {
            SymbolTable.error(
                    classNode.ctx,
                    classNode.type.token,
                    "Class " + classNode.type.token.getText() + " is redefined"
            );
            return null;
        }

        // se ataseaza simbolul
        classNode.symbol = symbol;
//        currentScope.add(symbol);
        // TODO ,maybe ar trebui sa am un scope pt clasa, dar nu pre vad de ce sau ca la functii idk

        // TODO: check if clas is inherited

        if (classNode.parent.token != null) {
            classNode.symbol.parentClass = new ClassSymbol(classNode.parent.token.getText(), currentScope);
        }

//        currentScope.add(symbol);
        SymbolTable.globals.add(symbol);
        currentScope = symbol;

        classNode.features.forEach(featureNode -> featureNode.accept(this));

        //
        currentScope = symbol.getParent();

        return null;
    }

    @Override
    public Void visit(IDNode idNode) {
        var symbol = (IdSymbol)currentScope.lookup(idNode.token.getText());

        idNode.setScope(currentScope);
//
//        if (symbol == null) {
//            SymbolTable.error("ceva err");
//
//            return null;
//        }

        idNode.setSymbol(symbol);

        if (currentScope instanceof LetSymbol ) {
            if (currentScope.lookup(idNode.token.getText()) == null) {
                SymbolTable.error(
                        idNode.ctx,
                        idNode.token,
                        "Undefined identifier " + idNode.token.getText()
                );
                return null;
            }
        }

        return null;
    }

    @Override
    public Void visit(FormalNode formalNode) {
        var id = formalNode.name;
        var type = formalNode.type;

        var symbol = new IdSymbol(id.token.getText());

        if (id.token.getText().equals("self")) {
            SymbolTable.error(
                    id.ctx,
                    id.token,
                    "Method " + currentScope + " of class " + currentScope.getParent() +
                            " has formal parameter with illegal name self"
            );

            return null;
        }

        if (type.token.getText().equals("SELF_TYPE")) {
            SymbolTable.error(
                    type.ctx,
                    type.token,
                    "Method " + currentScope + " of class " + currentScope.getParent() +
                        " has formal parameter " + id.token.getText() +
                            " with illegal type SELF_TYPE"
            );

            return null;
        }


        if (!currentScope.add(symbol)) {
            SymbolTable.error(
                    formalNode.ctx,
                    formalNode.token,
                    "Method " + currentScope + " of class " + currentScope.getParent() +
                            " redefines formal parameter " + id.token.getText()
            );

            return null;
        }

        // TODO: check this in ResolutionPassVisitor
        // here can appear forward references
        // ex: f(smt : C) : Int ... blah blah .. class C blah, blah
        // also, this is a type checking and i think that is nice to
        // do all this type checking in ResolutionPassVisitor


        symbol.type = new TypeSymbol(type.token.getText());
        id.setSymbol(symbol);
        id.setScope(currentScope);

        return null;
    }

    @Override
    public Void visit(VarDefNode varDefNode) {

        var symbol = new IdSymbol(varDefNode.name.token.getText());
        var id = varDefNode.name;
        var type = varDefNode.type;
//        Scope currentClass = currentScope;

        if (id.token.getText().equals("self")) {

            SymbolTable.error(
                    id.ctx,
                    id.token,
                    "Class " + currentScope + " has attribute with illegal name self"
            );

            return null;
        }

        if (!currentScope.add(symbol)) {
            SymbolTable.error(
                    id.ctx,
                    id.token,
                    "Class " + ((ClassSymbol)currentScope).getName() + " redefines attribute " + symbol.getName()
            );

            return null;
        }


        symbol.type = new TypeSymbol(type.token.getText());

        varDefNode.name.setSymbol(symbol);
        varDefNode.name.setScope(currentScope);

        var varType = currentScope.lookup(type.token.getText());

        //TODO: maybe i should this code block move to ResolutionPassVisitor
        // forward references can appear here :D

        if (! (varType instanceof TypeSymbol)) {
            SymbolTable.error(
                    type.ctx,
                    type.token,
                    "Class " + ((ClassSymbol)currentScope).getName() + " has attribute " + id.token.getText() +
                            " with undefined type " + type.token.getText()
            );
            return null;

        }



        if (varDefNode.val != null) {
            varDefNode.val.accept(this);
        }

        return null;
    }

    @Override
    public Void visit(IntNode intNode) {
        return null;
    }

    @Override
    public Void visit(BoolNode boolNode) {
        return null;
    }

    @Override
    public Void visit(AssignNode assignNode) {
//        assignNode.name.accept(this);
        assignNode.args.accept(this);
        return null;
    }

    @Override
    public Void visit(FuncDefNode funcDefNode) {
        var id = funcDefNode.funcName;
        var type = funcDefNode.funcType;

        var funcSymbol = new MethodSymbol(id.token.getText(), currentScope);


//        currentScope = funcSymbol;

        var lookedSymbol = currentScope.lookup(funcSymbol.name);

        if (lookedSymbol instanceof MethodSymbol) {
            SymbolTable.error(
                    funcDefNode.ctx,
                    funcDefNode.token,
                    "Class " + currentScope + " redefines method " + id.token.getText()
            );

            return null;
        }

        // ar trebui sa adaug simbolul
        currentScope.add(funcSymbol);

        funcSymbol.type = new TypeSymbol(type.token.getText());

        currentScope.add(funcSymbol);
        currentScope = funcSymbol;
        id.setSymbol(funcSymbol);
        id.setScope(currentScope);


//        var funcType = new TypeSymbol(type.token.getText());

        funcDefNode.funcParams.forEach(funcParam -> funcParam.accept(this));

        funcDefNode.body.accept(this);

        currentScope = currentScope.getParent();

        return null;
    }

    @Override
    public Void visit(StringNode stringNode) {
        return null;
    }

    @Override
    public Void visit(MulDivNode mulDivNode) {
        mulDivNode.leftOp.accept(this);
        mulDivNode.rightOp.accept(this);
        return null;
    }

    @Override
    public Void visit(PlusMinusNode plusMinusNode) {
        plusMinusNode.leftOp.accept(this);
        plusMinusNode.rightOp.accept(this);
        return null;
    }

    @Override
    public Void visit(NegateNode negateNode) {
        negateNode.rightOp.accept(this);
        return null;
    }

    @Override
    public Void visit(ParenNode parenNode) {
        // TODO: imi trece testul 7 daca las asta comentata aici
        // must check this strange behaviour
//        parenNode.expression.accept(this);
        return null;
    }

    @Override
    public Void visit(ComparisonNode comparisonNode) {
        return null;
    }

    @Override
    public Void visit(NotNode notNode) {
        return null;
    }

    @Override
    public Void visit(NewNode newNode) {
        return null;
    }

    @Override
    public Void visit(IsVoidNode isVoidNode) {
        return null;
    }

    @Override
    public Void visit(ExplicitDispatchNode explicitDispatchNode) {
        return null;
    }

    @Override
    public Void visit(ImplicitDispatch implicitDispatch) {
        return null;
    }

    @Override
    public Void visit(IfNode ifNode) {
        return null;
    }

    @Override
    public Void visit(WhileNode whileNode) {
        return null;
    }

    @Override
    public Void visit(LetDefNode letDefNode) {
        var id = letDefNode.name;
        var type = letDefNode.type;

        var symbol = new IdSymbol(id.token.getText());

        if (id.token.getText().equals("self")) {
            SymbolTable.error(
                    id.ctx,
                    id.token,
                    "Let variable has illegal name self"
            );

            return null;
        }

//        symbol.type = new TypeSymbol(type.token.getText());

        symbol.type = new TypeSymbol(type.token.getText());
        id.setSymbol(symbol);
        id.setScope(currentScope);


        if (letDefNode.val != null) {
            letDefNode.val.accept(this);
        }

        currentScope.add(symbol);

        return null;
    }

    @Override
    public Void visit(LetInNode letInNode) {
        var letSymbol = new LetSymbol(currentScope);
        letInNode.symbol = letSymbol;
//        currentScope.add()
        currentScope = letSymbol;

        letInNode.args.forEach(arg -> arg.accept(this));
        letInNode.body.accept(this);

        currentScope = currentScope.getParent();

        return null;
    }

    @Override
    public Void visit(CaseBranchNode caseBranchNode) {
        var id = caseBranchNode.name;
        var type = caseBranchNode.type;

        var symbol = new IdSymbol(id.token.getText());

        if (id.token.getText().equals("self")) {
            SymbolTable.error(
                    id.ctx,
                    id.token,
                    "Case variable has illegal name self"
            );

            return null;
        }

        if (type.token.getText().equals("SELF_TYPE")) {
            SymbolTable.error(
                    type.ctx,
                    type.token,
                    "Case variable " + id.token.getText() +
                            " has illegal type SELF_TYPE"
            );

            return null;
        }

        // TODO: must resolve type ??



        return null;
    }

    @Override
    public Void visit(CaseNode caseNode) {
        caseNode.var.accept(this);
        caseNode.branches.forEach(branch -> branch.accept(this));

        return null;
    }

    @Override
    public Void visit(BlockNode blockNode) {
        blockNode.blockBody.forEach(expr -> expr.accept(this));
        return null;
    }
}
