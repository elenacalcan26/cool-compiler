package cool.AST;

import org.antlr.v4.runtime.Token;

import java.lang.reflect.Type;
import java.util.List;

public class ClassNode extends ASTNode {
    TypeNode type;
    TypeNode parent;
    List<FeatureNode> features;

    ClassNode(Token token, TypeNode type, TypeNode parent, List<FeatureNode> features) {
        super(token);
        this.type = type;
        this.parent = parent;
        this.features = features;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
