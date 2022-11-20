package cool.AST;

import org.antlr.v4.runtime.Token;

public class VarDefNode extends FeatureNode {
    FormalNode formal;

    VarDefNode(Token token, FormalNode formal) {
        super(token);
        this.formal = formal;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
