package cool.AST;

import java.util.List;

public class ASTPrintVisitor implements ASTVisitor<Void> {
    private int indent = 0;

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
        indent++;
        classNode.type.accept(this);
        classNode.parent.accept(this);
        indent--;
        return null;
    }

    void printIndent(String str) {
        for (int i = 0; i < indent; i++)
            System.out.print("  ");
        System.out.println(str);
    }
}
