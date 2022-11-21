package cool.AST;

import org.antlr.v4.runtime.Token;

public class IntNode extends Expression {
    IntNode(Token token) {
        super(token);
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
