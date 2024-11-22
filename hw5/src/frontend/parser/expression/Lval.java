package frontend.parser.expression;

import frontend.lexer.Token;
import frontend.parser.SyntaxOutput;
import frontend.symbol.Symbol;
import frontend.symbol.SymbolTable;
import frontend.symbol.SymbolTableManager;
import frontend.symbol.SymbolType;

public class Lval implements SyntaxOutput {
    private String syntaxName = "<LVal>";
    private Token ident;
    private Token leftBracket;
    private AddExp exp;
    private Token rightBracket;

    public Lval(Token ident, Token leftBracket, AddExp exp, Token rightBracket) {
        this.ident = ident;
        this.leftBracket = leftBracket;
        this.exp = exp;
        this.rightBracket = rightBracket;
    }



    public Token getIdent() {
        return ident;
    }

    public SymbolType getType(int cur) {
        Symbol symbol = SymbolTableManager.getInstance().searchSymbol(ident.getValue(),
                SymbolTableManager.getInstance().getSymbolTable(cur));
        if (symbol == null) {
            return null;
        }
        if (symbol.getType() == SymbolType.ConstInt) {
            return SymbolType.Int;
        } else if (symbol.getType() == SymbolType.ConstChar) {
            return SymbolType.Char;
        } else {
            if (leftBracket == null) {
                return symbol.getType() == SymbolType.ConstIntArray ? SymbolType.IntArray :
                       symbol.getType() == SymbolType.ConstCharArray ? SymbolType.CharArray :
                                symbol.getType();
            } else {
                return (symbol.getType() == SymbolType.IntArray || symbol.getType() == SymbolType.ConstIntArray) ? SymbolType.Int :
                        (symbol.getType() == SymbolType.CharArray || symbol.getType() == SymbolType.ConstCharArray) ?SymbolType.Char:
                                symbol.getType();
            }
        }
    }

    @Override
    public String toSyntaxString() {
        StringBuilder sb = new StringBuilder();
        sb.append(ident.toSyntaxString());
        if (leftBracket != null) {
            sb.append(leftBracket.toSyntaxString());
        }
        if (exp != null) {
            sb.append(exp.toSyntaxString());
            sb.append("<Exp>\n");
        }
        if (rightBracket != null) {
            sb.append(rightBracket.toSyntaxString());
        }
        sb.append(syntaxName + "\n");
        return sb.toString();
    }
}
