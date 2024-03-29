package cool.AST;

import cool.parser.CoolParser;
import cool.parser.CoolParserBaseVisitor;

import java.util.List;
import java.util.stream.Collectors;

/* Construieste AST-ul in folosindu-se de parse tree-ul generat de antlr */
public class ASTConstructor extends CoolParserBaseVisitor<ASTNode> {
    @Override
    public ASTNode visitProgram(CoolParser.ProgramContext ctx) {
        List<ClassNode> classes = ctx.class_def()
                .stream()
                .map(class_defContext -> (ClassNode)visit(class_defContext))
                .collect(Collectors.toList());

        return new ProgramNode(ctx.start, classes, ctx);
    }

    @Override
    public ASTNode visitClass_def(CoolParser.Class_defContext ctx) {
        List<FeatureNode> features = ctx.feature()
                .stream()
                .map(featureContext -> (FeatureNode)visit(featureContext))
                .collect(Collectors.toList());

        return new ClassNode(ctx.start,
                new TypeNode(ctx.className, ctx),
                new TypeNode(ctx.inheritedClass, ctx),
                features,
                ctx);
    }

    @Override
    public ASTNode visitVarDef(CoolParser.VarDefContext ctx) {
        var initVal = ctx.val == null? null : (Expression)visit(ctx.val);

        return new VarDefNode(ctx.start,
                new IDNode(ctx.name, ctx),
                new TypeNode(ctx.type, ctx),
                initVal,
                ctx);
    }

    @Override
    public ASTNode visitFormal(CoolParser.FormalContext ctx) {
        return new FormalNode(ctx.start,
                new IDNode(ctx.name, ctx),
                new TypeNode(ctx.type, ctx),
                ctx);
    }

    @Override
    public ASTNode visitAssign(CoolParser.AssignContext ctx) {
        return new AssignNode(ctx.op,
                new IDNode(ctx.name, ctx),
                (Expression) visit(ctx.args),
                ctx);
    }

    @Override
    public ASTNode visitId(CoolParser.IdContext ctx) {
        return new IDNode(ctx.ID().getSymbol(), ctx);
    }

    @Override
    public ASTNode visitInt(CoolParser.IntContext ctx) {
        return new IntNode(ctx.INT().getSymbol(), ctx);
    }

    @Override
    public ASTNode visitBool(CoolParser.BoolContext ctx) {
        return new BoolNode(ctx.BOOL().getSymbol(), ctx);
    }

    @Override
    public ASTNode visitFuncDef(CoolParser.FuncDefContext ctx) {
        List<FormalNode> formalNodes = ctx.formals
                .stream()
                .map(formalContext -> (FormalNode)visit(formalContext))
                .collect(Collectors.toList());

        return new FuncDefNode(ctx.start,
                new IDNode(ctx.funcName, ctx),
                new TypeNode(ctx.funcType, ctx),
                formalNodes,
                (Expression)visit(ctx.body),
                ctx);
    }

    @Override
    public ASTNode visitString(CoolParser.StringContext ctx) {
        return new StringNode(ctx.start, ctx);
    }

    @Override
    public ASTNode visitPlusMinus(CoolParser.PlusMinusContext ctx) {
        return new PlusMinusNode(ctx.op,
                (Expression)visit(ctx.leftOp),
                (Expression)visit(ctx.rightOp),
                ctx);
    }

    @Override
    public ASTNode visitMulDiv(CoolParser.MulDivContext ctx) {
        return new MulDivNode(ctx.op,
                (Expression)visit(ctx.leftOp),
                (Expression)visit(ctx.rightOp),
                ctx);
    }

    @Override
    public ASTNode visitNegate(CoolParser.NegateContext ctx) {
        return new NegateNode(ctx.op, (Expression)visit(ctx.rightOp), ctx);
    }

    @Override
    public ASTNode visitParen(CoolParser.ParenContext ctx) {
        return new ParenNode(ctx.start, (Expression)visit(ctx.expression), ctx);
    }

    @Override
    public ASTNode visitComparison(CoolParser.ComparisonContext ctx) {
        return new ComparisonNode(ctx.op,
                (Expression) visit(ctx.leftOp),
                (Expression) visit(ctx.rightOp),
                ctx);
    }

    @Override
    public ASTNode visitNot(CoolParser.NotContext ctx) {
        return new NotNode(ctx.op, (Expression) visit(ctx.expression), ctx);
    }

    @Override
    public ASTNode visitNew(CoolParser.NewContext ctx) {
        return new NewNode(ctx.op,
                new TypeNode(ctx.type, ctx),
                ctx);
    }

    @Override
    public ASTNode visitIsvoid(CoolParser.IsvoidContext ctx) {
        return new IsVoidNode(ctx.op, (Expression) visit(ctx.expression), ctx);
    }

    @Override
    public ASTNode visitImplicitDispatch(CoolParser.ImplicitDispatchContext ctx) {
        List<Expression> args = ctx.args
                .stream()
                .map(exprContext -> (Expression)visit(exprContext))
                .collect(Collectors.toList());

        return new ImplicitDispatch(ctx.start,
                new IDNode(ctx.funcName, ctx),
                args,
                ctx);
    }

    @Override
    public ASTNode visitExplicitDispatch(CoolParser.ExplicitDispatchContext ctx) {
        List<Expression> args = ctx.args
                .stream()
                .map(exprContext -> (Expression) visit(exprContext))
                .toList();

        return new ExplicitDispatchNode(ctx.start,
                (Expression) visit(ctx.caller),
                new TypeNode(ctx.callType, ctx),
                new IDNode(ctx.funcName, ctx),
                args,
                ctx);
    }

    @Override
    public ASTNode visitIf(CoolParser.IfContext ctx) {
        return new IfNode(ctx.start,
                (Expression) visit(ctx.cond),
                (Expression) visit(ctx.thenBranch),
                (Expression) visit(ctx.elseBranch),
                ctx);
    }

    @Override
    public ASTNode visitWhile(CoolParser.WhileContext ctx) {
        return new WhileNode(ctx.start,
                (Expression) visit(ctx.cond),
                (Expression) visit(ctx.expression),
                ctx);
    }

    @Override
    public ASTNode visitBlock(CoolParser.BlockContext ctx) {
        List<Expression> body = ctx.blockBody
                .stream()
                .map(exprContext -> (Expression)visit(exprContext))
                .toList();

        return new BlockNode(ctx.start, body, ctx);
    }

    @Override
    public ASTNode visitLet_def(CoolParser.Let_defContext ctx) {
        var initVal = ctx.val == null? null : (Expression)visit(ctx.val);

        return new LetDefNode(ctx.start,
                new IDNode(ctx.name, ctx),
                new TypeNode(ctx.type, ctx),
                initVal,
                ctx);
    }

    @Override
    public ASTNode visitLet(CoolParser.LetContext ctx) {
        List<LetDefNode> args = ctx.args
                .stream()
                .map(let_defContext -> (LetDefNode)visit(let_defContext))
                .collect(Collectors.toList());

        return new LetInNode(ctx.start, args, (Expression) visit(ctx.body), ctx);
    }

    @Override
    public ASTNode visitCase_branch(CoolParser.Case_branchContext ctx) {
        return new CaseBranchNode(ctx.start,
                new IDNode(ctx.name, ctx),
                new TypeNode(ctx.type, ctx),
                (Expression) visit(ctx.body),
                ctx);
    }

    @Override
    public ASTNode visitCase(CoolParser.CaseContext ctx) {
        List<CaseBranchNode> branches = ctx.branches
                .stream()
                .map(case_branchContext -> (CaseBranchNode)visit(case_branchContext))
                .toList();

        return new CaseNode(ctx.start, (Expression)visit(ctx.var), branches, ctx);
    }
}
