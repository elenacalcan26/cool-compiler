package cool.AST;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

import java.util.List;

public class BlockNode extends Expression {
    List<Expression> blockBody;

    BlockNode(Token token, List<Expression> blockBody, ParserRuleContext ctx) {
        super(token, ctx);
        this.blockBody = blockBody;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
