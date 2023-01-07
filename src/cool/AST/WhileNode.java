package cool.AST;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public class WhileNode extends Expression {
    public Expression cond;
    public Expression expression;

    WhileNode(Token token,
              Expression cond,
              Expression expression,
              ParserRuleContext ctx) {
        super(token, ctx);
        this.cond = cond;
        this.expression = expression;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
