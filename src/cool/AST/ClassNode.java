package cool.AST;

import org.antlr.v4.runtime.Token;

import java.lang.reflect.Type;

public class ClassNode extends ASTNode {
    TypeNode type;
    TypeNode parent;

    ClassNode(Token token, TypeNode type, TypeNode parent) {
        super(token);
        this.type = type;
        this.parent = parent;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
