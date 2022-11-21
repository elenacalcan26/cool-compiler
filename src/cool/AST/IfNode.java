package cool.AST;

import org.antlr.v4.runtime.Token;

public class IfNode extends Expression {
    Expression cond;
    Expression thenBranch;
    Expression elseBranch;

    IfNode(Token token,
           Expression cond,
           Expression thenBranch,
           Expression elseBranch) {
        super(token);
        this.cond = cond;
        this.thenBranch = thenBranch;
        this.elseBranch = elseBranch;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
