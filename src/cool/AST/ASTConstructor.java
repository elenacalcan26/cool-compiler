package cool.AST;

import cool.parser.CoolParser;
import cool.parser.CoolParserBaseVisitor;

import java.util.List;
import java.util.stream.Collectors;

public class ASTConstructor extends CoolParserBaseVisitor<ASTNode> {
    @Override
    public ASTNode visitProgram(CoolParser.ProgramContext ctx) {
        List<ClassNode> classes = ctx.class_def()
                .stream()
                .map(class_defContext -> (ClassNode)visit(class_defContext))
                .collect(Collectors.toList());

        return new ProgramNode(ctx.start, classes);
    }

    @Override
    public ASTNode visitClass_def(CoolParser.Class_defContext ctx) {
        List<FeatureNode> features = ctx.feature()
                .stream()
                .map(featureContext -> (FeatureNode)visit(featureContext))
                .collect(Collectors.toList());

        return new ClassNode(ctx.start, new TypeNode(ctx.className),
                new TypeNode(ctx.inheritedClass), features);
    }

    @Override
    public ASTNode visitVarDef(CoolParser.VarDefContext ctx) {
        var initVal = ctx.val == null? null : (Expression)visit(ctx.val);

        return new VarDefNode(ctx.start, new IDNode(ctx.name), new TypeNode(ctx.type),
                initVal);
    }

    @Override
    public ASTNode visitFormal(CoolParser.FormalContext ctx) {
        return new FormalNode(ctx.start, new IDNode(ctx.name), new TypeNode(ctx.type));
    }

    @Override
    public ASTNode visitAssign(CoolParser.AssignContext ctx) {
        return new AssignNode(ctx.op, new IDNode(ctx.name), (Expression) visit(ctx.args));
    }

    @Override
    public ASTNode visitId(CoolParser.IdContext ctx) {
        return new IDNode(ctx.ID().getSymbol());
    }

    @Override
    public ASTNode visitInt(CoolParser.IntContext ctx) {
        return new IntNode(ctx.INT().getSymbol());
    }

    @Override
    public ASTNode visitBool(CoolParser.BoolContext ctx) {
        return new BoolNode(ctx.BOOL().getSymbol());
    }

    @Override
    public ASTNode visitFuncDef(CoolParser.FuncDefContext ctx) {
        List<FormalNode> formalNodes = ctx.formals
                .stream()
                .map(formalContext -> (FormalNode)visit(formalContext))
                .collect(Collectors.toList());

        return new FuncDefNode(ctx.start, new IDNode(ctx.funcName),
                new TypeNode(ctx.funcType), formalNodes, (Expression)visit(ctx.body));
    }

    @Override
    public ASTNode visitString(CoolParser.StringContext ctx) {
        return new StringNode(ctx.start);
    }

    @Override
    public ASTNode visitPlusMinus(CoolParser.PlusMinusContext ctx) {
        return new PlusMinusNode(ctx.op, (Expression) visit(ctx.leftOp),
                (Expression)visit(ctx.rightOp));
    }

    @Override
    public ASTNode visitMulDiv(CoolParser.MulDivContext ctx) {
        return new MulDivNode(ctx.op, (Expression) visit(ctx.leftOp),
                (Expression)visit(ctx.rightOp));
    }

    @Override
    public ASTNode visitNegate(CoolParser.NegateContext ctx) {
        return new NegateNode(ctx.op, (Expression)visit(ctx.rightOp));
    }

    @Override
    public ASTNode visitParen(CoolParser.ParenContext ctx) {
        return new ParenNode(ctx.start, (Expression)visit(ctx.expression));
    }

    @Override
    public ASTNode visitComparison(CoolParser.ComparisonContext ctx) {
        return new ComparisonNode(ctx.op, (Expression) visit(ctx.leftOp),
                (Expression) visit(ctx.rightOp));
    }

    @Override
    public ASTNode visitNot(CoolParser.NotContext ctx) {
        return new NotNode(ctx.op, (Expression) visit(ctx.expression));
    }

    @Override
    public ASTNode visitNew(CoolParser.NewContext ctx) {
        return new NewNode(ctx.op, new TypeNode(ctx.type));
    }

    @Override
    public ASTNode visitIsvoid(CoolParser.IsvoidContext ctx) {
        return new IsVoidNode(ctx.op, (Expression) visit(ctx.expression));
    }
}
