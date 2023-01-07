package cool.AST;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

import java.util.*;

public abstract class ASTNode {
    public Token token;
    public ParserRuleContext ctx;

    ASTNode(Token token, ParserRuleContext ctx) {
        this.token = token;
        this.ctx = ctx;

    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return null;
    }
}
