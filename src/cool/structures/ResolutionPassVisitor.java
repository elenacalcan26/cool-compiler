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
        var symbol = idNode.getSymbol();
        var scope = idNode.getScope();

        if (symbol == null) return null;

        if (scope == null) return null;
        var idSymbol = (IdSymbol) scope.lookup(symbol.name);

//        if (idSymbol == null) {
//            SymbolTable.error(idNode.ctx, idNode.token, "ceva");
//
//            return null;
//        }


        return idSymbol.type;
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

        if (parentScope.lookup(symbol.getName()) != null) {
            SymbolTable.error(
                    varDefNode.name.ctx,
                    varDefNode.name.token,
                    "Class " + currentScope + " redefines inherited attribute " + varDefNode.name.token.getText()
            );

//            varDefNode.name.setSymbol(null);

            return null;
        }

        var typeSymbol = varDefNode.type;


        if (varDefNode.val != null)
            varDefNode.val.accept(this);

        // TODO: must check this again

        return ((IdSymbol) symbol).type;
    }

    @Override
    public TypeSymbol visit(IntNode intNode) {
        return TypeSymbol.INT;
    }

    @Override
    public TypeSymbol visit(BoolNode boolNode) {
        return TypeSymbol.BOOL;
    }

    @Override
    public TypeSymbol visit(AssignNode assignNode) {
        var id = assignNode.name;

        if (id.token.getText().equals("self")) {
            SymbolTable.error(
                    id.ctx,
                    id.token,
                    "Cannot assign to self"
            );

            return null;
        }

        var idType = assignNode.name.accept(this);
        var exprType = assignNode.args.accept(this);
//        var clsSym = (ClassSymbol)SymbolTable.globals.lookup(exprType.name);
//        var idCls = (ClassSymbol)SymbolTable.globals.lookup(idType.name);

        if (idType == null || exprType == null) return null;

        var idCls = SymbolTable.globals.lookup(idType.name);


        if (!idType.name.equals(exprType.name)) {

            if (idCls instanceof ClassSymbol && ((ClassSymbol) idCls).isInheritedType(exprType.name)) {

                SymbolTable.error(
                        assignNode.ctx,
                        assignNode.args.token,
                        "Type " + exprType.name +
                                " of assigned expression is incompatible with declared type " +
                                idType.getName() + " of identifier " + id.token.getText()
                );

                return null;

            }

            if (exprType.name.equals(TypeSymbol.OBJECT.name)) {
                SymbolTable.error(
                        assignNode.ctx,
                        assignNode.args.token,
                        "Type " + exprType.name +
                                " of assigned expression is incompatible with declared type " +
                                idType.getName() + " of identifier " + id.token.getText()
                );
                return null;
            }


            // TODO: double check & modify
            if (idCls instanceof TypeSymbol) {
                SymbolTable.error(
                        assignNode.ctx,
                        assignNode.args.token,
                        "Type " + exprType.name +
                                " of assigned expression is incompatible with declared type " +
                                idType.getName() + " of identifier " + id.token.getText()
                );

                return null;
            }

        }

        return idType;

    }

    @Override
    public TypeSymbol visit(FuncDefNode funcDefNode) {
        var id = funcDefNode.funcName;
        var type = funcDefNode.funcType;

        var symbol = id.getSymbol();

        if (symbol == null) {
            return null;
        }

        // TODO: modifica asta plz, folosit pt a skipui test 19
        if (!(id.getScope().getParent() instanceof ClassSymbol))
            return null;

        var parentScope = (ClassSymbol)id.getScope().getParent();


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
        return TypeSymbol.STRING;
    }

    @Override
    public TypeSymbol visit(MulDivNode mulDivNode) {
        var leftType = mulDivNode.leftOp.accept(this);
        var rightType = mulDivNode.rightOp.accept(this);


        if (leftType != null && !leftType.name.equals(TypeSymbol.INT.name)) {
            SymbolTable.error(
                    mulDivNode.leftOp.ctx,
                    mulDivNode.leftOp.token,
                    "Operand of " + mulDivNode.token.getText() +
                            " has type " + leftType +
                            " instead of Int"
            );
            return null;
        }

        if (rightType != null && !rightType.name.equals(TypeSymbol.INT.name)) {
            SymbolTable.error(
                    mulDivNode.rightOp.ctx,
                    mulDivNode.rightOp.token,
                    "Operand of " + mulDivNode.rightOp.token.getText() +
                            " has type " + rightType +
                            " instead of Int"
            );
            return null;
        }

        if (leftType == null || rightType == null) {
            return null;
        }

        return TypeSymbol.INT;
    }

    @Override
    public TypeSymbol visit(PlusMinusNode plusMinusNode) {
        var leftType = plusMinusNode.leftOp.accept(this);
        var rightType = plusMinusNode.rightOp.accept(this);

        if (leftType != null && !leftType.name.equals(TypeSymbol.INT.name)) {
            SymbolTable.error(
                    plusMinusNode.leftOp.ctx,
                    plusMinusNode.leftOp.token,
                    "Operand of " + plusMinusNode.token.getText() +
                            " has type " + leftType +
                            " instead of Int"
            );
            return null;
        }

        if (rightType != null && !rightType.name.equals(TypeSymbol.INT.name)) {
            SymbolTable.error(
                    plusMinusNode.rightOp.ctx,
                    plusMinusNode.rightOp.token,
                    "Operand of " + plusMinusNode.token.getText() +
                            " has type " + rightType +
                            " instead of Int"
            );
            return null;
        }

        if (leftType == null || rightType == null) {
            return null;
        }

        return TypeSymbol.INT;
    }

    @Override
    public TypeSymbol visit(NegateNode negateNode) {
        var negOp = negateNode.rightOp.accept(this);

        if (negOp != null && !negOp.name.equals(TypeSymbol.INT.name)) {
            SymbolTable.error(
                    negateNode.rightOp.ctx,
                    negateNode.rightOp.token,
                    "Operand of " + negateNode.token.getText() +
                            " has type " + negOp +
                            " instead of Int"
            );

            return null;
        }

        if (negOp == null) return null;

        return TypeSymbol.INT;
    }

    @Override
    public TypeSymbol visit(ParenNode parenNode) {
        parenNode.expression.accept(this);
        return null;
    }

    @Override
    public TypeSymbol visit(ComparisonNode comparisonNode) {
        var leftType = comparisonNode.leftOp.accept(this);
        var rightType = comparisonNode.rightOp.accept(this);

        if (comparisonNode.token.getText().equals("=")) {

            if (!(leftType.name.equals(rightType.name)) &&
                    (TypeSymbol.isBasicDataType(leftType) ||
                    TypeSymbol.isBasicDataType(rightType))) {

                SymbolTable.error(
                        comparisonNode.ctx,
                        comparisonNode.token,
                        "Cannot compare " + leftType + " with " + rightType
                );


                return null;
            }


        } else {

            if (leftType.name.equals(TypeSymbol.INT.name) && !rightType.name.equals(TypeSymbol.INT.name)) {

                SymbolTable.error(
                        comparisonNode.rightOp.ctx,
                        comparisonNode.rightOp.token,
                        "Operand of " + comparisonNode.token.getText() +
                                " has type " + rightType +
                                " instead of Int"
                );

                return null;
            }

            if (!leftType.name.equals(TypeSymbol.INT.name) && rightType.name.equals(TypeSymbol.INT.name)) {

                SymbolTable.error(
                        comparisonNode.leftOp.ctx,
                        comparisonNode.leftOp.token,
                        "Operand of " + comparisonNode.token.getText() +
                                " has type " + leftType +
                                " instead of Int"
                );

                return null;
            }

        }

        return TypeSymbol.BOOL;
    }

    @Override
    public TypeSymbol visit(NotNode notNode) {
        var type = notNode.expression.accept(this);

        if (type == null) return null;

        if (!type.name.equals(TypeSymbol.BOOL.name)) {
            SymbolTable.error(
                    notNode.expression.ctx,
                    notNode.expression.token,
                    "Operand of not has type " + type + " instead of Bool"
            );

            return null;
        }

        return TypeSymbol.BOOL;
    }

    @Override
    public TypeSymbol visit(NewNode newNode) {
        var typeSymbol = SymbolTable.globals.lookup(newNode.type.token.getText());

        if (typeSymbol == null) {
            SymbolTable.error(
                    newNode.type.ctx,
                    newNode.type.token,
                    "new is used with undefined type " + newNode.type.token.getText()
            );
        }

        return new TypeSymbol(newNode.type.token.getText());
    }

    @Override
    public TypeSymbol visit(IsVoidNode isVoidNode) {
        return TypeSymbol.BOOL;
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
        var condType = whileNode.cond.accept(this);
        var blockType = whileNode.expression.accept(this);

        if (condType == null) return null;

        if (!condType.name.equals(TypeSymbol.BOOL.name)) {
            SymbolTable.error(
                    whileNode.cond.ctx,
                    whileNode.cond.token,
                    "While condition has type " + condType +
                            " instead of Bool"
            );

            return TypeSymbol.OBJECT;
        }

        return blockType;
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