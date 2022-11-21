package cool.AST;

import org.antlr.v4.runtime.Token;

public class VarDefNode extends FeatureNode {
    IDNode name;
    TypeNode type;
    Expression val;

    VarDefNode(Token token, IDNode name, TypeNode type, Expression val) {
        super(token);
        this.name = name;
        this.type = type;
        this.val = val;
    }
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
