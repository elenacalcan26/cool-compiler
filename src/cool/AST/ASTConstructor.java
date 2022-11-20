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
        return new VarDefNode(ctx.start, (FormalNode) visit(ctx.formalDef));
    }

    @Override
    public ASTNode visitFormal(CoolParser.FormalContext ctx) {
        return new FormalNode(ctx.start, new IDNode(ctx.name), new TypeNode(ctx.type));
    }
}
