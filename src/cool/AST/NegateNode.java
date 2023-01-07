package cool.AST;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public class NegateNode extends Expression {
    public Expression rightOp;

    NegateNode(Token token, Expression rightOp, ParserRuleContext ctx) {
        super(token, ctx);
        this.rightOp = rightOp;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
