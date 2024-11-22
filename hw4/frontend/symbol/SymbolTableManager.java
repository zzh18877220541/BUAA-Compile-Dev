package frontend.symbol;

import java.io.FileWriter;
import java.util.ArrayList;

public class SymbolTableManager {
    private int cur;
    private ArrayList<SymbolTable> tables;

    private SymbolTableManager() {
        this.cur = 0;
        this.tables = new ArrayList<SymbolTable>();
    }

    private static SymbolTableManager instance = new SymbolTableManager();

    public static SymbolTableManager getInstance() {
        return instance;
    }

    // 创建新的符号表, 这里的cur意指为索引
    public SymbolTable createSymbolTable(int parent, SymbolFunc baseFunction, boolean inLoop) {
        SymbolTable table;
        if (parent != 0) {
            table = new SymbolTable(tables.size() + 1, getSymbolTable(parent), baseFunction, inLoop);
        } else {
            table = new SymbolTable(tables.size() + 1, null, baseFunction, inLoop);
        }
        tables.add(table);
        return table;
    }

    public SymbolTable getSymbolTable(int cur) {
        return tables.get(cur - 1);
    }

    public Symbol searchSymbol(String symbolName, SymbolTable table) {
        SymbolTable curTable = table;
        boolean exist;

        do {
            exist = curTable.exist(symbolName);
            if (exist) {
                return curTable.getSymbol(symbolName);
            } else {
                curTable = curTable.getParent();
            }
        } while(curTable != null);

        return null;
    }

    public SymbolFunc searchFunc(String funcName) {
        SymbolTable rootTable = tables.get(0);
        for (Symbol symbol : rootTable.getSymbols().values()) {
            if (symbol instanceof SymbolFunc) {
                SymbolFunc func = (SymbolFunc) symbol;
                if (func.getName().equals(funcName)) {
                    return func;
                }
            }
        }
        return null;
    }

    public void printSymbolTable() {
        try {
            FileWriter fileWriter = new FileWriter("symbol.txt");
            for (SymbolTable table : tables) {
                for (Symbol symbol : table.getSymbols().values()) {
                    if (symbol.getName().equals("main")) {
                        continue;
                    }
                    fileWriter.write(table.getId() + " " + symbol.toString() + "\n");
                }
            }
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
