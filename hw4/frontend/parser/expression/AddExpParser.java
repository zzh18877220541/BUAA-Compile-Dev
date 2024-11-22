package frontend.parser.expression;

import frontend.lexer.Token;
import frontend.lexer.TokenListIterator;
import frontend.lexer.TokenType;

import java.util.ArrayList;

public class AddExpParser {
    TokenListIterator tokenListIterator;

    public AddExpParser(TokenListIterator tokenListIterator) {
        this.tokenListIterator = tokenListIterator;
    }

    public AddExp parseAddExp(int cur) {
        ArrayList<MulExp> mulExps = new ArrayList<MulExp>();
        ArrayList<Token> ops = new ArrayList<Token>();

        MulExpParser mulExpParser = new MulExpParser(tokenListIterator);
        MulExp firstMupExp = mulExpParser.parseMulExp(cur);
        mulExps.add(firstMupExp);

        Token now = tokenListIterator.readNextToken();
        while (now.getType() == TokenType.PLUS
                || now.getType() == TokenType.MINU) {
            ops.add(now);
            mulExps.add(mulExpParser.parseMulExp(cur));
            now = tokenListIterator.readNextToken();
        }
        tokenListIterator.unReadTokens(1);
        return new AddExp(mulExps, ops);
    }
}
