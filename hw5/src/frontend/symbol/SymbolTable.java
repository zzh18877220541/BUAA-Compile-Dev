package frontend.symbol;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class SymbolTable {
    private int id;
    private SymbolTable parent;
    private HashMap<String, Symbol> symbols;

    private SymbolFunc baseFunction;
    private boolean inLoop;

    public SymbolTable(int id, SymbolTable parent, SymbolFunc baseFunction, boolean inLoop) {
        this.id = id;
        this.parent = parent;
        this.symbols = new LinkedHashMap<String, Symbol>();
        this.baseFunction = baseFunction;
        this.inLoop = inLoop;
    }

    public int getId() {
        return id;
    }

    public SymbolFunc getBaseFunction() {
        return baseFunction;
    }

    public boolean isInLoop() {
        return inLoop;
    }

    public SymbolTable getParent() {
        return parent;
    }

    public void addSymbol(Symbol symbol) {
        symbols.put(symbol.getName(), symbol);
    }

    public boolean exist(String symbolName) {
        return symbols.get(symbolName) != null;
    }

    public HashMap<String, Symbol> getSymbols() {
        return symbols;
    }

    public Symbol getSymbol(String name) {
        return symbols.get(name);
    }
}
