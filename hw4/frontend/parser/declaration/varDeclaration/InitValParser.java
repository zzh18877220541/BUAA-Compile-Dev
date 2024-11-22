package frontend.parser.declaration.varDeclaration;

import frontend.lexer.Token;
import frontend.lexer.TokenListIterator;
import frontend.lexer.TokenType;
import frontend.parser.expression.AddExp;
import frontend.parser.expression.AddExpParser;

import java.util.ArrayList;

public class InitValParser {
    private TokenListIterator tokenListIterator;

    public InitValParser(TokenListIterator tokenListIterator) {
        this.tokenListIterator = tokenListIterator;
    }

    public InitVal parseInitVal(int cur) {
        ArrayList<AddExp> exps = new ArrayList<>();
        ArrayList<Token> commas = new ArrayList<>();
        Token leftBrace = null;
        Token rightBrace = null;
        Token STRING_CONST = null;

        AddExpParser addExpParser = new AddExpParser(tokenListIterator);

        Token FirstToken = tokenListIterator.readNextToken();
        if (FirstToken.getType() == TokenType.STRCON) {
            STRING_CONST = FirstToken;
        } else if (FirstToken.getType() == TokenType.LBRACE) { // {
            leftBrace = FirstToken;
            Token nextToken = tokenListIterator.readNextToken();
            if (nextToken.getType() == TokenType.RBRACE) { // }
                rightBrace = nextToken;
                return new InitVal(exps, leftBrace, rightBrace, commas, STRING_CONST);
            } else {
                tokenListIterator.unReadTokens(1);
            }
            AddExp FisrtExp = addExpParser.parseAddExp(cur);
            exps.add(FisrtExp);
            nextToken = tokenListIterator.readNextToken();
            while (nextToken.getType() == TokenType.COMMA) {
                commas.add(nextToken);
                AddExp NextExp = addExpParser.parseAddExp(cur);
                exps.add(NextExp);
                nextToken = tokenListIterator.readNextToken();
            }
            if (nextToken.getType() == TokenType.RBRACE) { // }
                rightBrace = nextToken;
            } else {
                tokenListIterator.unReadTokens(1);
                throw new Error("RBRACE expected in InitVal");
            }
        } else {
            tokenListIterator.unReadTokens(1);
            AddExp FisrtExp = addExpParser.parseAddExp(cur);
            exps.add(FisrtExp);
        }

        return new InitVal(exps, leftBrace, rightBrace, commas, STRING_CONST);
    }
}
