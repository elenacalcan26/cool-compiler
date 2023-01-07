package cool.AST;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public class FormalNode extends ASTNode {
    public IDNode name;
    public TypeNode type;

    FormalNode(Token token, IDNode name, TypeNode type, ParserRuleContext ctx) {
        super(token, ctx);
        this.name = name;
        this.type = type;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
