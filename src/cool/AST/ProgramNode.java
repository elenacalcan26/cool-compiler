package cool.AST;

import org.antlr.v4.runtime.Token;

import java.util.List;

public class ProgramNode extends ASTNode {
    List<ClassNode> classNodeList;

    ProgramNode(Token token, List<ClassNode> classNodeList) {
        super(token);
        this.classNodeList = classNodeList;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
