package frontend.parser.expression;

import frontend.SyntaxError.ErrorType;
import frontend.SyntaxError.SyntaxErrorHandler;
import frontend.lexer.Token;
import frontend.lexer.TokenListIterator;
import frontend.lexer.TokenType;

public class PrimaryExpParser {
    private TokenListIterator tokenListIterator;

    public PrimaryExpParser(TokenListIterator tokenListIterator) {
        this.tokenListIterator = tokenListIterator;
    }

    public PrimaryExp parsePrimaryExp() {
        Token leftParent = null;
        AddExp addExp = null;
        Token rightParent = null;
        Lval lval = null;
        Token INT_CONST = null;
        Token HEX_CONST = null;
        Token CHAR_CONST = null;

        AddExpParser addExpParser = new AddExpParser(tokenListIterator);
        LvalParser lvalParser = new LvalParser(tokenListIterator);

        Token FirstToken = tokenListIterator.readNextToken();
        if (FirstToken.getType() == TokenType.LPARENT) {
            leftParent = FirstToken;
            addExp = addExpParser.parseAddExp();
            Token SecondToken = tokenListIterator.readNextToken();
            if (SecondToken.getType() == TokenType.RPARENT) {
                rightParent = SecondToken;
            } else {
                tokenListIterator.unReadTokens(1);
                SyntaxErrorHandler.getInstance().WriteSyntaxError(ErrorType.j, FirstToken.getLine());
            }
        } else if (FirstToken.getType() == TokenType.INTCON || FirstToken.getType() == TokenType.CHRCON
                || FirstToken.getType() == TokenType.HEXCON) {
            if (FirstToken.getType() == TokenType.INTCON) {
                INT_CONST = FirstToken;
            } else if (FirstToken.getType() == TokenType.CHRCON){
                CHAR_CONST = FirstToken;
            } else {
                HEX_CONST = FirstToken;
            }
        } else {
            tokenListIterator.unReadTokens(1);
            lval = lvalParser.parseLval();
        }

        return new PrimaryExp(leftParent, addExp, rightParent, lval, INT_CONST, CHAR_CONST, HEX_CONST);
    }
}
