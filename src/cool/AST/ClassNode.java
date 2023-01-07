package cool.AST;

import cool.structures.ClassSymbol;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

import java.util.List;

public class ClassNode extends ASTNode {
    public TypeNode type;
    public TypeNode parent;
    public List<FeatureNode> features;

    public ClassSymbol symbol;

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
