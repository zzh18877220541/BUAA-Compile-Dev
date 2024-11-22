package frontend.parser.block;

import frontend.SyntaxError.ErrorType;
import frontend.SyntaxError.SyntaxErrorHandler;
import frontend.lexer.Token;
import frontend.lexer.TokenListIterator;
import frontend.lexer.TokenType;
import frontend.parser.expression.AddExp;
import frontend.parser.expression.AddExpParser;
import frontend.parser.expression.Lval;
import frontend.parser.expression.LvalParser;
import frontend.symbol.Symbol;
import frontend.symbol.SymbolTable;
import frontend.symbol.SymbolTableManager;
import frontend.symbol.SymbolType;

import java.util.concurrent.atomic.AtomicBoolean;

public class ForStmtParser {
    private TokenListIterator tokenListIterator;

    public ForStmtParser(TokenListIterator tokenListIterator) {
        this.tokenListIterator = tokenListIterator;
    }

    public ForStmt parseForStmt(int cur) {
        Lval lval = null;
        Token assign = null;
        AddExp exp = null;

        LvalParser lvalParser = new LvalParser(tokenListIterator);
        AddExpParser addExpParser = new AddExpParser(tokenListIterator);

        SymbolTable curTable = SymbolTableManager.getInstance().getSymbolTable(cur);

        Token nextToken = tokenListIterator.readNextToken();
        if (nextToken.getType() != TokenType.IDENFR) {
            tokenListIterator.unReadTokens(1);
            return null;
        } else {
            // parse
            tokenListIterator.unReadTokens(1);
            lval = lvalParser.parseLval(cur);
            assign = tokenListIterator.readNextToken();
            exp = addExpParser.parseAddExp(cur);

            // symbol
            Symbol symbol_lval = SymbolTableManager.getInstance().searchSymbol(lval.getIdent().getValue(), curTable);
            if (symbol_lval != null) {
                if (symbol_lval.getType() == SymbolType.ConstInt || symbol_lval.getType() == SymbolType.ConstChar
                        || symbol_lval.getType() == SymbolType.ConstCharArray || symbol_lval.getType() == SymbolType.ConstIntArray) {
                    SyntaxErrorHandler.getInstance().WriteSyntaxError(ErrorType.h, lval.getIdent().getLine());
                }
            }
        }

        return new ForStmt(lval, assign, exp);
    }
}
