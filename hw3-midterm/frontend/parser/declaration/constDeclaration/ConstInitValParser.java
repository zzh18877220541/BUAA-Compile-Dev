package frontend.parser.declaration.constDeclaration;

import frontend.SyntaxError.ErrorType;
import frontend.SyntaxError.SyntaxErrorHandler;
import frontend.lexer.Token;
import frontend.lexer.TokenListIterator;
import frontend.lexer.TokenType;
import frontend.parser.expression.ConstExp;
import frontend.parser.expression.ConstExpParser;

import java.util.ArrayList;

public class ConstInitValParser {
    private TokenListIterator tokenListIterator;

    public ConstInitValParser(TokenListIterator tokenListIterator) {
        this.tokenListIterator = tokenListIterator;
    }

    public ConstInitVal parseConstInitVal() {
        ArrayList<ConstExp> constExps = new ArrayList<>();
        ArrayList<Token> commas = new ArrayList<>();
        Token leftBrace = null;
        Token rightBrace = null;
        Token StringConst = null;

        ConstExpParser constExpParser = new ConstExpParser(tokenListIterator);

        Token FirstToken = tokenListIterator.readNextToken();
        if (FirstToken.getType() == TokenType.LBRACE) {
            leftBrace = FirstToken;
            Token now = tokenListIterator.readNextToken();
            if (now.getType() == TokenType.RBRACE) {
                rightBrace = now;
                return new ConstInitVal(constExps, leftBrace, rightBrace, commas, StringConst);
            } else {
                tokenListIterator.unReadTokens(1);
            }
            ConstExp constExp = constExpParser.parseConstExp();
            constExps.add(constExp);
            now = tokenListIterator.readNextToken();
            while (now.getType() == TokenType.COMMA) {
                commas.add(now);
                constExp = constExpParser.parseConstExp();
                constExps.add(constExp);
                now = tokenListIterator.readNextToken();
            }
            if (now.getType() == TokenType.RBRACE) { // }
                rightBrace = now;
            } else {
                throw new Error("RBRACE expected in ConstInitVal");
            }
        } else if (FirstToken.getType() == TokenType.STRCON) {
            StringConst = FirstToken;
        } else {
            tokenListIterator.unReadTokens(1);
            constExps.add(constExpParser.parseConstExp());
        }
        return new ConstInitVal(constExps, leftBrace, rightBrace, commas, StringConst);
    }
}
