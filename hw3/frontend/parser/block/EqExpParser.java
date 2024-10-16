package frontend.parser.block;

import frontend.lexer.Token;
import frontend.lexer.TokenListIterator;
import frontend.lexer.TokenType;
import frontend.parser.expression.AddExp;
import frontend.parser.expression.AddExpParser;

import java.util.ArrayList;

public class EqExpParser {
    private TokenListIterator tokenListIterator;

    public EqExpParser(TokenListIterator tokenListIterator) {
        this.tokenListIterator = tokenListIterator;
    }

    public EqExp parseEqExp() {
        RelExpParser relExpParser = new RelExpParser(tokenListIterator);
        ArrayList<RelExp> relExps = new ArrayList<>();
        ArrayList<Token> eqOps = new ArrayList<>();

        Token nextToken = tokenListIterator.readNextToken();
        // TODO: to do to prevent for lack
//        if (nextToken.getType() != TokenType.LPARENT &&
//                nextToken.getType() != TokenType.IDENFR) {
//            tokenListIterator.unReadTokens(1);
//            return null;
//        } else {
            tokenListIterator.unReadTokens(1);
            RelExp relExp = relExpParser.parseRelExp();
            relExps.add(relExp);
            nextToken = tokenListIterator.readNextToken();
            while (nextToken.getType() == TokenType.EQL ||
                    nextToken.getType() == TokenType.NEQ) {
                eqOps.add(nextToken);
                relExp = relExpParser.parseRelExp();
                relExps.add(relExp);
                nextToken = tokenListIterator.readNextToken();
            }
            tokenListIterator.unReadTokens(1);
        //}

        return new EqExp(relExps, eqOps);
    }
}
