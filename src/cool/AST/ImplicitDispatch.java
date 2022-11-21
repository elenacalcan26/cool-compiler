package cool.AST;

import org.antlr.v4.runtime.Token;

import java.util.List;

public class ImplicitDispatch extends Expression {
    IDNode funcName;
    List<Expression> args;

    ImplicitDispatch(Token token, IDNode funcName, List<Expression> args) {
        super(token);
        this.funcName = funcName;
        this.args = args;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }

}
