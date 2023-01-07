package cool.AST;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public class ComparisonNode extends Expression {
    public Expression leftOp;
    public Expression rightOp;

    ComparisonNode(Token token,
                   Expression leftOp,
                   Expression rightOp,
                   ParserRuleContext ctx) {
        super(token, ctx);
        this.leftOp = leftOp;
        this.rightOp = rightOp;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
