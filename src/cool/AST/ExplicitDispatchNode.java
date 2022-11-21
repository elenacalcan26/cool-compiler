package cool.AST;

import org.antlr.v4.runtime.Token;

import java.util.List;

public class ExplicitDispatchNode extends Expression {
    Expression caller;
    TypeNode callType;
    IDNode funcName;
    List<Expression> args;

    ExplicitDispatchNode(Token token,
                         Expression caller,
                         TypeNode callType,
                         IDNode funcName,
                         List<Expression> args) {
        super(token);
        this.caller = caller;
        this.callType = callType;
        this.funcName = funcName;
        this.args = args;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
