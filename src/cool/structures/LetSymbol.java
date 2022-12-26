package cool.structures;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class LetSymbol implements Scope {
    public Scope parent;
    public  HashMap<String, Symbol> symbols;

    LetSymbol(Scope parent) {
        this.parent = parent;
        symbols = new LinkedHashMap<>();
    }

    @Override
    public boolean add(Symbol sym) {
        if (symbols.containsKey(sym.getName())) {
            return false;
        }

        symbols.put(sym.getName(), sym);

        return true;
    }

    @Override
    public Symbol lookup(String str) {
        Symbol symbol = symbols.get(str);

        if (symbol != null) {
            return symbol;
        }

        if (parent != null) {
            return parent.lookup(str);
        }

        return null;
    }

    @Override
    public Scope getParent() {
        return parent;
    }
}
