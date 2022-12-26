package cool.structures;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class ClassSymbol extends Symbol implements Scope {
    public Scope parent;
    public HashMap<String, Symbol> symbols;

    public ClassSymbol parentClass;

    public ClassSymbol(String name, Scope parent) {
        super(name);
        this.parent = parent;
        symbols = new LinkedHashMap<>();
    }

    @Override
    public boolean add(Symbol s) {
        if (symbols.containsKey(s.getName())) {
            return false;
        }

        symbols.put(s.getName(), s);

        return true;
    }

    @Override
    public Symbol lookup(String s) {
        Symbol symbol = symbols.get(s);

        if (symbol != null) {
            return symbol;
        }

        if (parent != null) {
            return parent.lookup(s);
        }

        return null;
    }

    @Override
    public Scope getParent() {
        return parent;
    }
}
