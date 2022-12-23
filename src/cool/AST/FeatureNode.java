package cool.AST;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public abstract class FeatureNode extends ASTNode {
    FeatureNode(Token token, ParserRuleContext ctx) {
        super(token, ctx);
    }
}
