package cool.AST;

import org.antlr.v4.runtime.Token;

import java.util.List;

public class CaseNode extends Expression {
    Expression var;
    List<CaseBranchNode> branches;

    CaseNode(Token token, Expression var, List<CaseBranchNode> branches) {
        super(token);
        this.var = var;
        this.branches = branches;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
