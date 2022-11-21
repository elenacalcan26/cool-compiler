package cool.AST;

import org.antlr.v4.runtime.Token;

public abstract class Expression extends ASTNode {
    Expression(Token token) {
        super(token);
    }
}
