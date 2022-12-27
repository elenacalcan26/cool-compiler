package cool.AST;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public class IfNode extends Expression {
    public Expression cond;
    public Expression thenBranch;
    public Expression elseBranch;

    IfNode(Token token,
           Expression cond,
           Expression thenBranch,
           Expression elseBranch,
           ParserRuleContext ctx) {
        super(token, ctx);
        this.cond = cond;
        this.thenBranch = thenBranch;
        this.elseBranch = elseBranch;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
