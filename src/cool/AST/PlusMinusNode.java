package cool.AST;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public class PlusMinusNode extends Expression {
    public Expression leftOp;
    public Expression rightOp;

    PlusMinusNode(Token token, Expression leftOp, Expression rightOp, ParserRuleContext ctx) {
        super(token, ctx);
        this.leftOp = leftOp;
        this.rightOp = rightOp;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
