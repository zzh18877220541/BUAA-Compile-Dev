package frontend.parser.expression;

import frontend.SyntaxError.ErrorType;
import frontend.SyntaxError.SyntaxErrorHandler;
import frontend.lexer.Token;
import frontend.lexer.TokenListIterator;
import frontend.lexer.TokenType;

public class LvalParser {
    private TokenListIterator tokenListIterator;

    public LvalParser(TokenListIterator tokenListIterator) {
        this.tokenListIterator = tokenListIterator;
    }

    public Lval parseLval() {
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
            exp = addExpParser.parseAddExp();
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

    public Lval try_parseLval() {
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
            exp = addExpParser.parseAddExp();
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
