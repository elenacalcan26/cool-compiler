package cool.AST;

import org.antlr.v4.runtime.Token;

public class BoolNode extends ASTNode {
    BoolNode(Token token) {
        super(token);
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
