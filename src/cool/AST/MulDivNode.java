package cool.AST;

import org.antlr.v4.runtime.Token;

public class MulDivNode extends Expression {
    Expression leftOp;
    Expression rightOp;

    MulDivNode(Token token, Expression leftOp, Expression rightOp) {
        super(token);
        this.leftOp = leftOp;
        this.rightOp = rightOp;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}