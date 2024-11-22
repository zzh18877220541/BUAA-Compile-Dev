package frontend.symbol;

import java.util.ArrayList;

public class SymbolFunc extends Symbol {
    private ArrayList<Symbol> symbols;

    public SymbolFunc(String name, int id, SymbolType symbolType, ArrayList<Symbol> symbols) {
        super(name, id, symbolType);
        this.symbols = symbols;
    }

    public int parasNum() {
        return symbols.size();
    }

    public ArrayList<Symbol> getParas() {
        return symbols;
    }

    public Symbol getPara(int i) {
        return symbols.get(i);
    }
}
