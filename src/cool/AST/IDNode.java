package cool.AST;

import cool.structures.IdSymbol;
import cool.structures.Scope;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public class IDNode extends Expression {
    private IdSymbol symbol;
    private Scope scope;

    IDNode(Token token, ParserRuleContext ctx) {
        super(token, ctx);
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public IdSymbol getSymbol() {
        return symbol;
    }

    public Scope getScope() {
        return scope;
    }

    public void setSymbol(IdSymbol symbol) {
        this.symbol = symbol;
    }

    public void setScope(Scope scope) {
        this.scope = scope;
    }
}
