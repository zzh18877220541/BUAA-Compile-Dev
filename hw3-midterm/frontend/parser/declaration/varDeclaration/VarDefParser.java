package frontend.parser.declaration.varDeclaration;

import frontend.SyntaxError.ErrorType;
import frontend.SyntaxError.SyntaxErrorHandler;
import frontend.lexer.Token;
import frontend.lexer.TokenListIterator;
import frontend.lexer.TokenType;
import frontend.parser.expression.ConstExp;
import frontend.parser.expression.ConstExpParser;

public class VarDefParser {
    private TokenListIterator tokenListIterator;

    public VarDefParser(TokenListIterator tokenListIterator) {
        this.tokenListIterator = tokenListIterator;
    }

    public VarDef parseVarDef() {
        Token ident = tokenListIterator.readNextToken();
        Token leftBracket = null;
        ConstExp constExp = null;
        Token rightBracket = null;
        Token assign = null;
        InitVal initVal = null;

        InitValParser initValParser = new InitValParser(tokenListIterator);
        ConstExpParser constExpParser = new ConstExpParser(tokenListIterator);

        Token FirstToken = tokenListIterator.readNextToken();
        if (FirstToken.getType() == TokenType.LBRACK) { // [
            leftBracket = FirstToken;
            constExp = constExpParser.parseConstExp();
            Token SecondToken = tokenListIterator.readNextToken();
            if (SecondToken.getType() == TokenType.RBRACK) { // ]
                rightBracket = SecondToken;
            } else {
                tokenListIterator.unReadTokens(1);
                rightBracket = new Token(TokenType.RBRACK, "]", FirstToken.getLine());
                SyntaxErrorHandler.getInstance().WriteSyntaxError(ErrorType.k, FirstToken.getLine());
            }
        } else {
            tokenListIterator.unReadTokens(1);
        }
        Token ThirdToken = tokenListIterator.readNextToken();
        if (ThirdToken.getType() == TokenType.ASSIGN) {
            assign = ThirdToken;
            initVal = initValParser.parseInitVal();
        } else {
            tokenListIterator.unReadTokens(1);
        }
        return new VarDef(ident, leftBracket, constExp, rightBracket, assign, initVal);
    }
}
