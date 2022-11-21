package cool.AST;

import org.antlr.v4.runtime.Token;

public class NewNode extends Expression {
    TypeNode type;

    NewNode(Token token, TypeNode type) {
        super(token);
        this.type = type;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
