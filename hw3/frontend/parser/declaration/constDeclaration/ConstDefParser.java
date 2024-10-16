package frontend.parser.declaration.constDeclaration;

import frontend.SyntaxError.ErrorType;
import frontend.SyntaxError.SyntaxErrorHandler;
import frontend.lexer.Token;
import frontend.lexer.TokenListIterator;
import frontend.lexer.TokenType;
import frontend.parser.expression.ConstExp;
import frontend.parser.expression.ConstExpParser;

public class ConstDefParser {
    private TokenListIterator tokenListIterator;

    public ConstDefParser(TokenListIterator tokenListIterator) {
        this.tokenListIterator = tokenListIterator;
    }

    public ConstDef parseConstDef() {
        Token ident = null;
        Token leftBracket = null;
        ConstExp constExp = null;
        Token rightBracket = null;
        Token assign = null;
        ConstInitVal constInitVal = null;

        ConstInitValParser constInitValParser = new ConstInitValParser(tokenListIterator);
        ConstExpParser constExpParser = new ConstExpParser(tokenListIterator);

        Token FirstToken = tokenListIterator.readNextToken();
        if (FirstToken.getType() == TokenType.IDENFR) {
            ident = FirstToken;
            Token SecondToken = tokenListIterator.readNextToken();
            if (SecondToken.getType() == TokenType.LBRACK) { // [
                leftBracket = SecondToken;
                constExp = constExpParser.parseConstExp();
                Token ThirdToken = tokenListIterator.readNextToken();
                if (ThirdToken.getType() == TokenType.RBRACK) { // ]
                    rightBracket = ThirdToken;
                } else {
                    tokenListIterator.unReadTokens(1);
                    rightBracket = new Token(TokenType.RBRACK, "]", FirstToken.getLine());
                    SyntaxErrorHandler.getInstance().WriteSyntaxError(ErrorType.k, SecondToken.getLine());
                }
                Token FourthToken = tokenListIterator.readNextToken();
                if (FourthToken.getType() == TokenType.ASSIGN) {
                    assign = FourthToken;
                    constInitVal = constInitValParser.parseConstInitVal();
                    return new ConstDef(ident, leftBracket, constExp, rightBracket, assign, constInitVal);
                } else {
                    throw new Error("ASSIGN expected in ConstDef");
                }
            } else if (SecondToken.getType() == TokenType.ASSIGN) {
                leftBracket = null;
                constExp = null;
                rightBracket = null;
                assign = SecondToken;
                constInitVal = constInitValParser.parseConstInitVal();
                return new ConstDef(ident, leftBracket, constExp, rightBracket, assign, constInitVal);
            } else {
                throw new Error("LBRACK or ASSIGN expected in ConstDef");
            }
        } else {
            throw new Error("IDENFR expected in ConstDef");
        }

    }
}