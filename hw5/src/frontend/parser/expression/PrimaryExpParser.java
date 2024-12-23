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

    public PrimaryExp parsePrimaryExp(int cur) {
        Token leftParent = null;
        AddExp addExp = null;
        Token rightParent = null;
        Lval lval = null;
        Token INT_CONST = null;
        Token CHAR_CONST = null;

        AddExpParser addExpParser = new AddExpParser(tokenListIterator);
        LvalParser lvalParser = new LvalParser(tokenListIterator);

        Token FirstToken = tokenListIterator.readNextToken();
        if (FirstToken.getType() == TokenType.LPARENT) {
            leftParent = FirstToken;
            addExp = addExpParser.parseAddExp(cur);
            Token SecondToken = tokenListIterator.readNextToken();
            if (SecondToken.getType() == TokenType.RPARENT) {
                rightParent = SecondToken;
            } else {
                tokenListIterator.unReadTokens(1);
                SyntaxErrorHandler.getInstance().WriteSyntaxError(ErrorType.j, FirstToken.getLine());
            }
        } else if (FirstToken.getType() == TokenType.INTCON || FirstToken.getType() == TokenType.CHRCON) {
            if (FirstToken.getType() == TokenType.INTCON) {
                INT_CONST = FirstToken;
            } else {
                CHAR_CONST = FirstToken;
            }
        } else {
            tokenListIterator.unReadTokens(1);
            lval = lvalParser.parseLval(cur);
        }

        return new PrimaryExp(leftParent, addExp, rightParent, lval, INT_CONST, CHAR_CONST);
    }
}
