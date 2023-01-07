package cool.AST;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public class AssignNode extends Expression {
    public IDNode name;
    public Expression args;

    AssignNode(Token token, IDNode name, Expression args, ParserRuleContext ctx) {
        super(token, ctx);
        this.name = name;
        this.args = args;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
