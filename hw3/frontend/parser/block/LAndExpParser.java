package frontend.parser.block;

import frontend.lexer.Token;
import frontend.lexer.TokenListIterator;
import frontend.lexer.TokenType;

import java.util.ArrayList;

public class LAndExpParser {
    private TokenListIterator tokenListIterator;


    public LAndExpParser(TokenListIterator tokenListIterator) {
        this.tokenListIterator = tokenListIterator;
    }

    public LAndExp parseLAndExp() {
        EqExpParser eqExpParser = new EqExpParser(tokenListIterator);
        ArrayList<EqExp> eqExps = new ArrayList<>();
        ArrayList<Token> ands = new ArrayList<>();

        Token nextToken = tokenListIterator.readNextToken();
        // TODO: to do to prevent for lack
//        if (nextToken.getType() != TokenType.LPARENT &&
//                nextToken.getType() != TokenType.IDENFR) {
//            tokenListIterator.unReadTokens(1);
//            return null;
//        } else {
            tokenListIterator.unReadTokens(1);
            EqExp eqExp = eqExpParser.parseEqExp();
            eqExps.add(eqExp);
            nextToken = tokenListIterator.readNextToken();
            while (nextToken.getType() == TokenType.AND) {
                ands.add(nextToken);
                eqExp = eqExpParser.parseEqExp();
                eqExps.add(eqExp);
                nextToken = tokenListIterator.readNextToken();
            }
            tokenListIterator.unReadTokens(1);
        //}

        return new LAndExp(eqExps, ands);
    }
}
