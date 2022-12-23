package cool.AST;

import cool.structures.TypeSymbol;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

import java.lang.reflect.Type;
import java.util.List;

public class ClassNode extends ASTNode {
    public TypeNode type;
    TypeNode parent;
    public List<FeatureNode> features;

    public TypeSymbol symbol;

    ClassNode(Token token, TypeNode type, TypeNode parent, List<FeatureNode> features, ParserRuleContext ctx) {
        super(token, ctx);
        this.type = type;
        this.parent = parent;
        this.features = features;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
