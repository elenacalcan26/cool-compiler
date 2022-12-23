package cool.AST;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public class NewNode extends Expression {
    TypeNode type;

    NewNode(Token token, TypeNode type, ParserRuleContext ctx) {
        super(token, ctx);
        this.type = type;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
