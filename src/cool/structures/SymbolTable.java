package cool.structures;

import java.io.File;

import cool.parser.CoolParser;
import org.antlr.v4.runtime.*;

import cool.compiler.Compiler;

public class SymbolTable {
    public static Scope globals;
    
    private static boolean semanticErrors;

    enum ILLEGAL_PARENTS {
        INT,
        SELF_TYPE,
        STRING,
        BOOL,
        IO
    }

    public static boolean isIllegalParent(String parentClassName) {
        for (ILLEGAL_PARENTS illegalParent : ILLEGAL_PARENTS.values()) {
            if (parentClassName.toUpperCase().equals(illegalParent.toString())) return true;
        }

        return false;
    }
    
    public static void defineBasicClasses() {
        globals = new DefaultScope(null);
        semanticErrors = false;
        
        // TODO Populate global scope.

        globals.add(TypeSymbol.INT);
        globals.add(TypeSymbol.BOOL);
        globals.add(TypeSymbol.STRING);
        globals.add(TypeSymbol.IO);
        globals.add(TypeSymbol.OBJECT);
        globals.add(TypeSymbol.SELF_TYPE);
    }
    
    /**
     * Displays a semantic error message.
     * 
     * @param ctx Used to determine the enclosing class context of this error,
     *            which knows the file name in which the class was defined.
     * @param info Used for line and column information.
     * @param str The error message.
     */
    public static void error(ParserRuleContext ctx, Token info, String str) {
        while (! (ctx.getParent() instanceof CoolParser.ProgramContext))
            ctx = ctx.getParent();
        
        String message = "\"" + new File(Compiler.fileNames.get(ctx)).getName()
                + "\", line " + info.getLine()
                + ":" + (info.getCharPositionInLine() + 1)
                + ", Semantic error: " + str;
        
        System.err.println(message);
        
        semanticErrors = true;
    }
    
    public static void error(String str) {
        String message = "Semantic error: " + str;
        
        System.err.println(message);
        
        semanticErrors = true;
    }
    
    public static boolean hasSemanticErrors() {
        return semanticErrors;
    }
}
