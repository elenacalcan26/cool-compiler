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
        if (type.token != null)  printIndent(type.token.getText());
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
        printIndent(idNode.token.getText());
        return null;
    }

    @Override
    public Void visit(FormalNode formalNode) {
//        printIndent(formalNode.token.getText());
        indent++;
        formalNode.name.accept(this);
        formalNode.type.accept(this);
        indent--;
        return null;
    }

    @Override
    public Void visit(VarDefNode varDefNode) {
        printIndent("attribute");
//        indent++;
        varDefNode.formal.accept(this);
//        indent--;
        return null;
    }
}
