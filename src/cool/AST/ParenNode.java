package cool.AST;

import org.antlr.v4.runtime.Token;

public class ParenNode extends Expression {
    Expression expression;

    ParenNode(Token token, Expression expression) {
        super(token);
        this.expression = expression;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
