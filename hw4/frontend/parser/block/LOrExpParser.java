package frontend.parser.block;

import frontend.lexer.Token;
import frontend.lexer.TokenListIterator;
import frontend.lexer.TokenType;

import java.util.ArrayList;

public class LOrExpParser {
    private TokenListIterator tokenListIterator;

    public LOrExpParser(TokenListIterator tokenListIterator) {
        this.tokenListIterator = tokenListIterator;
    }

    public LOrExp parseLOrExp(int cur) {
        LAndExpParser lAndExpParser = new LAndExpParser(tokenListIterator);
        ArrayList<LAndExp> lAndExps = new ArrayList<>();
        ArrayList<Token> ors = new ArrayList<>();

        Token nextToken = tokenListIterator.readNextToken();
        // TODO: to do to prevent for lack
//        if (nextToken.getType() != TokenType.LPARENT &&
//                nextToken.getType() != TokenType.IDENFR) {
//            tokenListIterator.unReadTokens(1);
//            return null;
//        } else {
            tokenListIterator.unReadTokens(1);
            LAndExp lAndExp = lAndExpParser.parseLAndExp(cur);
            lAndExps.add(lAndExp);
            nextToken = tokenListIterator.readNextToken();
            while (nextToken.getType() == TokenType.OR) {
                ors.add(nextToken);
                lAndExp = lAndExpParser.parseLAndExp(cur);
                lAndExps.add(lAndExp);
                nextToken = tokenListIterator.readNextToken();
            }
            tokenListIterator.unReadTokens(1);
        //}

        return new LOrExp(lAndExps, ors);
    }
}
