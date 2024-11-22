package frontend.symbol;

public class Symbol {
    private String name;
    private int lineNum;
    private SymbolType type;

    public Symbol(String name, int lineNum, SymbolType type) {
        this.name = name;
        this.lineNum = lineNum;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public int getLineNum() {
        return lineNum;
    }

    public SymbolType getType() {
        return type;
    }

    public String toString() {
        return name + " " + type;
    }
}


