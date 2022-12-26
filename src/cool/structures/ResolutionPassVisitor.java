package cool.structures;

import cool.AST.*;

import java.util.ArrayList;
import java.util.List;

public class ResolutionPassVisitor implements ASTVisitor<TypeSymbol> {
    @Override
    public TypeSymbol visit(ProgramNode programNode) {
        programNode.classNodeList.forEach(classNode -> classNode.accept(this));
        return null;
    }

    @Override
    public TypeSymbol visit(TypeNode type) {
        return null;
    }

    @Override
    public TypeSymbol visit(ClassNode classNode) {
        var symbol = classNode.symbol;
        var scope = classNode.symbol;

        if (scope != null && classNode.parent.token != null) {

            var inheritedClass = classNode.parent;

            if (scope.lookup(inheritedClass.token.getText()) == null) {
                SymbolTable.error(
                        classNode.parent.ctx,
                        classNode.parent.token,
                        "Class " + classNode.type.token.getText() + " has undefined parent " + inheritedClass.token.getText()
                );
                return null;
            }

            // TODO: ar fi nice sa am un enum cu illegal parents
            if (inheritedClass.token.getText().equals("SELF_TYPE") ||
                inheritedClass.token.getText().equals("Int")) {

                SymbolTable.error(
                    inheritedClass.ctx,
                    inheritedClass.token,
                    "Class " + classNode.type.token.getText() +  " has illegal parent " + inheritedClass.token.getText()
                );

                return null;
            }

            // TODO: inheritance cycle
        }


        classNode.features.forEach(featureNode -> featureNode.accept(this));

        return null;
    }

    @Override
    public TypeSymbol visit(IDNode idNode) {
        return null;
    }

    @Override
    public TypeSymbol visit(FormalNode formalNode) {
        var id = formalNode.name;
        var type = formalNode.type;

        var currScope = id.getScope();

        if (currScope == null) return null;

        // cautam recursiv tipul parametrului formal

        var formalType = currScope.lookup(type.token.getText());


        if (formalType == null) {
            SymbolTable.error(
                    type.ctx,
                    type.token,
                    "Method " + currScope + " of class " + currScope.getParent() +
                            " has formal parameter " + id.token.getText() +
                            " with undefined type " + type.token.getText()
            );
            return null;
        }

        // TODO: i should set TypeSymbol here ??

        return null;
    }


    @Override
    public TypeSymbol visit(VarDefNode varDefNode) {
        var symbol = varDefNode.name.getSymbol();

        var currentScope = (ClassSymbol)varDefNode.name.getScope();

        if (currentScope == null) {
            return null;
        }

        var parentScope =  currentScope.getParent();

        // TODO: modify this plz
        //TODO: A good idea cred ca ar fi sa fac o trecere pt a determina mostenirile
        // cred ca mai cool are sa fac un LCA :D

        if (parentScope.lookup(symbol.getName()) != null) {
            SymbolTable.error(
                    varDefNode.name.ctx,
                    varDefNode.name.token,
                    "Class " + currentScope + " redefines inherited attribute " + varDefNode.name.token.getText()
            );

            varDefNode.name.setSymbol(null);

            return null;
        }

        var typeSymbol = varDefNode.type;

        return null;
    }

    @Override
    public TypeSymbol visit(IntNode intNode) {
        return null;
    }

    @Override
    public TypeSymbol visit(BoolNode boolNode) {
        return null;
    }

    @Override
    public TypeSymbol visit(AssignNode assignNode) {
        return null;
    }

    @Override
    public TypeSymbol visit(FuncDefNode funcDefNode) {
        var id = funcDefNode.funcName;
        var type = funcDefNode.funcType;

        var symbol = id.getSymbol();

        if (symbol == null) {
            return null;
        }

        // TODO: modifica asta plz, folosit pt askipui test 19
        if (!(id.getScope().getParent() instanceof ClassSymbol))
            return null;

        var parentScope =  (ClassSymbol)id.getScope().getParent();


        if (parentScope.parentClass != null) {
            // clasa in care este definita functia este mostenita
            var inheritedClass = (ClassSymbol) parentScope.lookup(parentScope.parentClass.getName());

            if (inheritedClass.lookup(id.token.getText()) != null) {
                // se pare ca functia curenta a fost suprascria

                // se verifica daca tipurile de retur a functiei a fost suprascris sau nu

                // TODO: change variable name pls
                var overriddenMethodType = (MethodSymbol)inheritedClass.lookup(symbol.getName());

                if (!type.token.getText().equals(overriddenMethodType.type.getName())) {
                    SymbolTable.error(
                            type.ctx,
                            type.token,
                            "Class " + parentScope + " overrides method " + id.token.getText() +
                                    " but changes return type from " +
                                    overriddenMethodType.type.getName() + " to " +
                                    type.token.getText()
                    );

                    return null;
                }

                var currentFuncFormals = funcDefNode.funcParams;
                var overriddenFormals = new ArrayList<Symbol>(overriddenMethodType.symbols.values());

                if (currentFuncFormals.size() != overriddenFormals.size()) {
                    // metoda supradefinta cu un numar diferit de parametrii formali

                    SymbolTable.error(
                            funcDefNode.ctx,
                            funcDefNode.token,
                            "Class " + parentScope + " overrides method " + id.token.getText() +
                                    " with different number of formal parameters"
                    );

                    return null;
                }

                // TODO: mai cool era daca ma ducea recursiv :D
                for (int i = 0; i < overriddenFormals.size(); i++) {

                    var currFormalType = currentFuncFormals.get(i).type;
                    var parentFormalType = ((IdSymbol)overriddenFormals.get(i)).type;

                    if (!currFormalType.token.getText().equals(parentFormalType.getName())) {
                        SymbolTable.error(
                                currentFuncFormals.get(i).type.ctx,
                                currentFuncFormals.get(i).type.token,
                                "Class " + parentScope + " overrides method " + id.token.getText() +
                                        " but changes type of formal parameter " +
                                        currentFuncFormals.get(i).name.token.getText() +
                                        " from " + parentFormalType.getName() + " to " + currFormalType.token.getText()
                        );

                        return null;
                    }

                }

            }

        }

//        if (parentScope.parentClass != null) {
//            System.out.println(id.getScope()+ " -> " + parentScope + " -> " + parentScope.parentClass.getName());
//        }
        //                System.out.println(inheritedClass.lookup(type.token.getText()));
//                System.out.println(type.token.getText() + " -> " + overriddenMethodType.type.getName());

        funcDefNode.funcParams.forEach(funcParam -> funcParam.accept(this));
        funcDefNode.body.accept(this);

        return null;
    }

    @Override
    public TypeSymbol visit(StringNode stringNode) {
        return null;
    }

    @Override
    public TypeSymbol visit(MulDivNode mulDivNode) {
        return null;
    }

    @Override
    public TypeSymbol visit(PlusMinusNode plusMinusNode) {
        return null;
    }

    @Override
    public TypeSymbol visit(NegateNode negateNode) {
        return null;
    }

    @Override
    public TypeSymbol visit(ParenNode parenNode) {
        return null;
    }

    @Override
    public TypeSymbol visit(ComparisonNode comparisonNode) {
        return null;
    }

    @Override
    public TypeSymbol visit(NotNode notNode) {
        return null;
    }

    @Override
    public TypeSymbol visit(NewNode newNode) {
        return null;
    }

    @Override
    public TypeSymbol visit(IsVoidNode isVoidNode) {
        return null;
    }

    @Override
    public TypeSymbol visit(ExplicitDispatchNode explicitDispatchNode) {
        return null;
    }

    @Override
    public TypeSymbol visit(ImplicitDispatch implicitDispatch) {
        return null;
    }

    @Override
    public TypeSymbol visit(IfNode ifNode) {
        return null;
    }

    @Override
    public TypeSymbol visit(WhileNode whileNode) {
        return null;
    }

    @Override
    public TypeSymbol visit(LetDefNode letDefNode) {
        var id = letDefNode.name;
        var type = letDefNode.type;

        var symbol = id.getSymbol();

        if (symbol == null) return null;

        var currentScope = id.getScope();

        if (currentScope.lookup(type.token.getText()) == null) {
            SymbolTable.error(
                    type.ctx,
                    type.token,
                    "Let variable " + id.token.getText() +
                            " has undefined type " + type.token.getText()
            );
            return null;
        }

        return null;
    }

    @Override
    public TypeSymbol visit(LetInNode letInNode) {
        letInNode.args.forEach(arg -> arg.accept(this));
        letInNode.body.accept(this);

        return null;
    }

    @Override
    public TypeSymbol visit(CaseBranchNode caseBranchNode) {
        var id = caseBranchNode.name;
        var type = caseBranchNode.type;

        var symbol = id.getSymbol();

        if (SymbolTable.globals.lookup(type.token.getText()) == null) {
            SymbolTable.error(
                    type.ctx,
                    type.token,
                    "Case variable " + id.token.getText() +
                            " has undefined type " + type.token.getText()
            );

            return null;
        }

        return null;
    }

    @Override
    public TypeSymbol visit(CaseNode caseNode) {
        caseNode.var.accept(this);
        caseNode.branches.forEach(branch -> branch.accept(this));
        return null;
    }

    @Override
    public TypeSymbol visit(BlockNode blockNode) {
        blockNode.blockBody.forEach(expr -> expr.accept(this));
        return null;
    }
}