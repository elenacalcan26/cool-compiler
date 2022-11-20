package cool.AST;

public interface ASTVisitor<T> {
    T visit(ProgramNode programNode);
    T visit(TypeNode type);
    T visit(ClassNode classNode);
}
