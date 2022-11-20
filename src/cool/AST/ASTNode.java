package cool.AST;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

import java.util.*;

public abstract class ASTNode {
    Token token;

    ASTNode(Token token) {
        this.token = token;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return null;
    }
}
