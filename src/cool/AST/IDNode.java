package cool.AST;

import org.antlr.v4.runtime.Token;

public class IDNode extends Expression {
    IDNode(Token token) {
        super(token);
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
