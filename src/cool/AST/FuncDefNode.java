package cool.AST;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

import java.util.List;

public class FuncDefNode extends FeatureNode {
    IDNode funcName;
    TypeNode funcType;
    List<FormalNode> funcParams;
    Expression body;

    FuncDefNode(Token token,
                IDNode funcName,
                TypeNode funcType,
                List<FormalNode> funcParams,
                Expression body,
                ParserRuleContext ctx) {
        super(token, ctx);
        this.funcName = funcName;
        this.funcType = funcType;
        this.funcParams = funcParams;
        this.body = body;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
