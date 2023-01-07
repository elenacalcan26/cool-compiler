package cool.AST;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

import java.util.List;

public class ProgramNode extends ASTNode {
    public List<ClassNode> classNodeList;

    ProgramNode(Token token, List<ClassNode> classNodeList, ParserRuleContext ctx) {
        super(token, ctx);
        this.classNodeList = classNodeList;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
