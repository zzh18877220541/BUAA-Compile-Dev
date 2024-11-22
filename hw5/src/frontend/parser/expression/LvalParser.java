package frontend.parser.expression;

import frontend.SyntaxError.ErrorType;
import frontend.SyntaxError.SyntaxErrorHandler;
import frontend.lexer.Token;
import frontend.lexer.TokenListIterator;
import frontend.lexer.TokenType;
import frontend.symbol.SymbolTable;
import frontend.symbol.SymbolTableManager;

public class LvalParser {
    private TokenListIterator tokenListIterator;

    public LvalParser(TokenListIterator tokenListIterator) {
        this.tokenListIterator = tokenListIterator;
    }

    public Lval parseLval(int cur) {
        Token ident = null;
        Token leftBracket = null;
        AddExp exp = null;
        Token rightBracket = null;

        AddExpParser addExpParser = new AddExpParser(tokenListIterator);

        SymbolTable curTable = SymbolTableManager.getInstance().getSymbolTable(cur);

        ident = tokenListIterator.readNextToken();
        if (ident.getType() != TokenType.IDENFR) {
            tokenListIterator.unReadTokens(1);
            return null;
        }
        // symbol
        String name = ident.getValue();
        if (SymbolTableManager.getInstance().searchSymbol(name, curTable) == null) {
            SyntaxErrorHandler.getInstance().WriteSyntaxError(ErrorType.c, ident.getLine());
        }
        // parse
        Token FirstToken = tokenListIterator.readNextToken();
        if (FirstToken.getType() == TokenType.LBRACK) { // [
            leftBracket = FirstToken;
            exp = addExpParser.parseAddExp(cur);
            Token SecondToken = tokenListIterator.readNextToken();
            if (SecondToken.getType() == TokenType.RBRACK) { // ]
                rightBracket = SecondToken;
            } else {
                tokenListIterator.unReadTokens(1);
                SyntaxErrorHandler.getInstance().WriteSyntaxError(ErrorType.k, FirstToken.getLine());
            }
        } else {
            tokenListIterator.unReadTokens(1);
        }
        return new Lval(ident, leftBracket, exp, rightBracket);
    }

    public Lval try_parseLval(int cur) {
        Token ident = null;
        Token leftBracket = null;
        AddExp exp = null;
        Token rightBracket = null;

        AddExpParser addExpParser = new AddExpParser(tokenListIterator);

        ident = tokenListIterator.readNextToken();
        if (ident.getType() != TokenType.IDENFR) {
            tokenListIterator.unReadTokens(1);
            return null;
        }
        Token FirstToken = tokenListIterator.readNextToken();
        if (FirstToken.getType() == TokenType.LBRACK) { // [
            leftBracket = FirstToken;
            exp = addExpParser.parseAddExp(cur);
            Token SecondToken = tokenListIterator.readNextToken();
            if (SecondToken.getType() == TokenType.RBRACK) { // ]
                rightBracket = SecondToken;
            } else {
                tokenListIterator.unReadTokens(1);
            }
        } else {
            tokenListIterator.unReadTokens(1);
        }
        return new Lval(ident, leftBracket, exp, rightBracket);
    }
}
