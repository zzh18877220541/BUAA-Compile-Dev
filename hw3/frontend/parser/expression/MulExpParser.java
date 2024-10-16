package frontend.parser.expression;

import frontend.lexer.Token;
import frontend.lexer.TokenListIterator;
import frontend.lexer.TokenType;

import java.util.ArrayList;

public class MulExpParser {
    private TokenListIterator tokenListIterator;

    public MulExpParser(TokenListIterator tokenListIterator) {
        this.tokenListIterator = tokenListIterator;
    }

    public MulExp parseMulExp() {
        ArrayList<UnaryExp> unaryExps = new ArrayList<>();
        ArrayList<Token> ops = new ArrayList<>();

        UnaryExpParser unaryExpParser = new UnaryExpParser(tokenListIterator);

        unaryExps.add(unaryExpParser.parseUnaryExp());
        Token nextToken = tokenListIterator.readNextToken();
        while (nextToken.getType() == TokenType.MULT ||
                nextToken.getType() == TokenType.DIV ||
                nextToken.getType() == TokenType.MOD) {
            ops.add(nextToken);
            unaryExps.add(unaryExpParser.parseUnaryExp());
            nextToken = tokenListIterator.readNextToken();
        }
        tokenListIterator.unReadTokens(1);
        return new MulExp(unaryExps, ops);
    }
}
