package cool.AST;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public class IsVoidNode extends Expression {
    Expression expression;

    IsVoidNode(Token token, Expression expression, ParserRuleContext ctx) {
        super(token, ctx);
        this.expression = expression;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
