package cool.AST;

import org.antlr.v4.runtime.Token;

public class FormalNode extends ASTNode {
    IDNode name;
    TypeNode type;

    FormalNode(Token token, IDNode name, TypeNode type) {
        super(token);
        this.name = name;
        this.type = type;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
