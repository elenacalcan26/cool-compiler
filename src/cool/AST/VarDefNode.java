package cool.AST;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public class VarDefNode extends FeatureNode {
    public IDNode name;
    public TypeNode type;
    public Expression val;

    VarDefNode(Token token,
               IDNode name,
               TypeNode type,
               Expression val,
               ParserRuleContext ctx) {
        super(token, ctx);
        this.name = name;
        this.type = type;
        this.val = val;
    }
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
