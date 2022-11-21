package cool.AST;

import org.antlr.v4.runtime.Token;

public class AssignNode extends Expression {
    IDNode name;
    Expression args;

    AssignNode(Token token, IDNode name, Expression args) {
        super(token);
        this.name = name;
        this.args = args;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
