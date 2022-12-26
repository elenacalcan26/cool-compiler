package cool.AST;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public class CaseBranchNode extends ASTNode {
    public IDNode name;
    public TypeNode type;
    Expression body;

    CaseBranchNode(Token token,
                   IDNode name,
                   TypeNode type,
                   Expression body,
                   ParserRuleContext ctx) {
        super(token, ctx);
        this.name = name;
        this.type = type;
        this.body = body;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
