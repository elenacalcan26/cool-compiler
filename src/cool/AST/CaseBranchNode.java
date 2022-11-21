package cool.AST;

import org.antlr.v4.runtime.Token;

public class CaseBranchNode extends ASTNode {
    IDNode name;
    TypeNode type;
    Expression body;

    CaseBranchNode(Token token, IDNode name, TypeNode type, Expression body) {
        super(token);
        this.name = name;
        this.type = type;
        this.body = body;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
