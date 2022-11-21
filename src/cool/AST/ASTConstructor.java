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
        return new AssignNode(ctx.start, new IDNode(ctx.name), (Expression) visit(ctx.args));
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
}
