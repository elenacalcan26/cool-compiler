package cool.structures;

public class TypeSymbol extends Symbol {
    public TypeSymbol(String name) {
        super(name);
    }

    // Symboluri aferente tipurilor, definite global
    public static final TypeSymbol INT = new TypeSymbol("Int");
    public static final TypeSymbol BOOL = new TypeSymbol("Bool");
    public static final TypeSymbol STRING = new TypeSymbol("String");
    public static final TypeSymbol OBJECT = new TypeSymbol("Object");
    public static final TypeSymbol IO = new TypeSymbol("IO");
    public static final TypeSymbol SELF_TYPE = new TypeSymbol("SELF_TYPE");

    public static boolean isBasicDataType(TypeSymbol sym) {
        return sym.name.equals(INT.name) ||
                sym.name.equals(BOOL.name) || sym.name.equals(STRING.name);
    }

}
