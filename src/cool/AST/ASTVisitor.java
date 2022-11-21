package cool.AST;

public interface ASTVisitor<T> {
    T visit(ProgramNode programNode);
    T visit(TypeNode type);
    T visit(ClassNode classNode);
    T visit(IDNode idNode);
    T visit(FormalNode formalNode);
    T visit(VarDefNode varDefNode);
    T visit(IntNode intNode);
    T visit(BoolNode boolNode);
    T visit(AssignNode assignNode);
    T visit(FuncDefNode funcDefNode);
    T visit(StringNode stringNode);
    T visit(MulDivNode mulDivNode);
    T visit(PlusMinusNode plusMinusNode);
    T visit(NegateNode negateNode);
    T visit(ParenNode parenNode);
}