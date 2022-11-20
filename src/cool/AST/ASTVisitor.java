package cool.AST;

public interface ASTVisitor<T> {
    T visit(ProgramNode programNode);
    T visit(TypeNode type);
    T visit(ClassNode classNode);
    T visit(IDNode idNode);
    T visit(FormalNode formalNode);
    T visit(VarDefNode varDefNode);
}
