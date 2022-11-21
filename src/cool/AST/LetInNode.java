package cool.AST;

import org.antlr.v4.runtime.Token;

import java.util.List;

public class LetInNode extends Expression {
    List<LetDefNode> args;
    Expression body;

    LetInNode(Token token, List<LetDefNode> args, Expression body) {
        super(token);
        this.args = args;
        this.body = body;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
