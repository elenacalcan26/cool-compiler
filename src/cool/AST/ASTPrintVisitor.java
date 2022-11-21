package cool.AST;

import java.util.List;

public class ASTPrintVisitor implements ASTVisitor<Void> {
    private int indent = 0;

    void printIndent(String str) {
        for (int i = 0; i < indent; i++)
            System.out.print("  ");
        System.out.println(str);
    }

    @Override
    public Void visit(ProgramNode programNode) {
        printIndent("program");
        List<ClassNode> classes = programNode.classNodeList;
        indent++;
        classes.forEach(classNode -> classNode.accept(this));
        indent--;
        return null;
    }

    @Override
    public Void visit(TypeNode type) {
        if (type.token != null)
            printIndent(type.token.getText());
        return null;
    }

    @Override
    public Void visit(ClassNode classNode) {
        printIndent(classNode.token.getText());
        List<FeatureNode> featureNodes = classNode.features;
        indent++;
        classNode.type.accept(this);
        classNode.parent.accept(this);
        featureNodes.forEach(featureNode -> featureNode.accept(this));
        indent--;
        return null;
    }

    @Override
    public Void visit(IDNode idNode) {
        if (idNode.token != null)
            printIndent(idNode.token.getText());
        return null;
    }

    @Override
    public Void visit(FormalNode formalNode) {
        printIndent("formal");
        indent++;
        formalNode.name.accept(this);
        formalNode.type.accept(this);
        indent--;
        return null;
    }

    @Override
    public Void visit(VarDefNode varDefNode) {
        printIndent("attribute");
        indent++;
        varDefNode.name.accept(this);
        varDefNode.type.accept(this);
        if (varDefNode.val != null)
            varDefNode.val.accept(this);
        indent--;
        return null;
    }

    @Override
    public Void visit(IntNode intNode) {
        printIndent(intNode.token.getText());
        return null;
    }

    @Override
    public Void visit(BoolNode boolNode) {
        printIndent(boolNode.token.getText());
        return null;
    }

    @Override
    public Void visit(AssignNode assignNode) {
        printIndent(assignNode.token.getText());
        indent++;
        assignNode.name.accept(this);
        assignNode.args.accept(this);
        indent--;
        return null;
    }

    @Override
    public Void visit(FuncDefNode funcDefNode) {
        printIndent("method");
        List<FormalNode> params = funcDefNode.funcParams;
        indent++;
        funcDefNode.funcName.accept(this);
        params.forEach(param -> param.accept(this));
        funcDefNode.funcType.accept(this);
        funcDefNode.body.accept(this);
        indent--;
        return null;
    }

    @Override
    public Void visit(StringNode stringNode) {
        printIndent(stringNode.token.getText());
        return null;
    }

    @Override
    public Void visit(MulDivNode mulDivNode) {
        printIndent(mulDivNode.token.getText());
        indent++;
        mulDivNode.leftOp.accept(this);
        mulDivNode.rightOp.accept(this);
        indent--;
        return null;
    }

    @Override
    public Void visit(PlusMinusNode plusMinusNode) {
        printIndent(plusMinusNode.token.getText());
        indent++;
        plusMinusNode.leftOp.accept(this);
        plusMinusNode.rightOp.accept(this);
        indent--;
        return null;
    }

    @Override
    public Void visit(NegateNode negateNode) {
        printIndent(negateNode.token.getText());
        indent++;
        negateNode.rightOp.accept(this);
        indent--;
        return null;
    }

    @Override
    public Void visit(ParenNode parenNode) {
        parenNode.expression.accept(this);
        return null;
    }

    @Override
    public Void visit(ComparisonNode comparisonNode) {
        printIndent(comparisonNode.token.getText());
        indent++;
        comparisonNode.leftOp.accept(this);
        comparisonNode.rightOp.accept(this);
        indent--;
        return null;
    }

    @Override
    public Void visit(NotNode notNode) {
        printIndent(notNode.token.getText());
        indent++;
        notNode.expression.accept(this);
        indent--;
        return null;
    }
}
