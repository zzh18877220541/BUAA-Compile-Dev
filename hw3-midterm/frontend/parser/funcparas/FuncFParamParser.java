package frontend.parser.funcparas;

import frontend.SyntaxError.ErrorType;
import frontend.SyntaxError.SyntaxErrorHandler;
import frontend.lexer.Token;
import frontend.lexer.TokenListIterator;
import frontend.lexer.TokenType;

public class FuncFParamParser {
    private TokenListIterator tokenListIterator;

    public FuncFParamParser(TokenListIterator tokenListIterator) {
        this.tokenListIterator = tokenListIterator;
    }

    public FuncFParam parseFuncFParam() {
        Token bType = tokenListIterator.readNextToken();
        Token ident = tokenListIterator.readNextToken();
        Token leftBracket = null;
        Token rightBracket = null;
        Token nextToken = tokenListIterator.readNextToken();

        if (nextToken.getType() == TokenType.LBRACK) { // [
            leftBracket = nextToken;
            nextToken = tokenListIterator.readNextToken();
            if (nextToken.getType() != TokenType.RBRACK) { // ]
                tokenListIterator.unReadTokens(1);
                SyntaxErrorHandler.getInstance().WriteSyntaxError(ErrorType.k, leftBracket.getLine());
            } else {
                rightBracket = nextToken;
            }
        } else {
            tokenListIterator.unReadTokens(1);
        }

        return new FuncFParam(bType, ident, leftBracket, rightBracket);
    }
}
