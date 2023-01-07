package cool.AST;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

import java.util.List;

public class CaseNode extends Expression {
    public Expression var;
    public List<CaseBranchNode> branches;

    CaseNode(Token token,
             Expression var,
             List<CaseBranchNode> branches,
             ParserRuleContext ctx) {
        super(token, ctx);
        this.var = var;
        this.branches = branches;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
