package cool.AST;

import org.antlr.v4.runtime.Token;

public class NegateNode extends Expression {
    Expression rightOp;

    NegateNode(Token token, Expression rightOp) {
        super(token);
        this.rightOp = rightOp;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
