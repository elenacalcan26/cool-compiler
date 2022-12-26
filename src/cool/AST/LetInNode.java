package cool.AST;

import cool.structures.LetSymbol;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

import java.util.List;

public class LetInNode extends Expression {
    public List<LetDefNode> args;
    public Expression body;
    public LetSymbol symbol;

    LetInNode(Token token, List<LetDefNode> args, Expression body, ParserRuleContext ctx) {
        super(token, ctx);
        this.args = args;
        this.body = body;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
