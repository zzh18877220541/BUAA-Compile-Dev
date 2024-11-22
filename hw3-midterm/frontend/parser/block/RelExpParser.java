package frontend.parser.block;

import frontend.lexer.Token;
import frontend.lexer.TokenListIterator;
import frontend.lexer.TokenType;
import frontend.parser.expression.AddExp;
import frontend.parser.expression.AddExpParser;

import java.util.ArrayList;

public class RelExpParser {
    private TokenListIterator tokenListIterator;

    public RelExpParser(TokenListIterator tokenListIterator) {
        this.tokenListIterator = tokenListIterator;
    }

    public RelExp parseRelExp() {
        AddExpParser addExpParser = new AddExpParser(tokenListIterator);
        ArrayList<AddExp> addExps = new ArrayList<>();
        ArrayList<Token> relOps = new ArrayList<>();

        Token nextToken = tokenListIterator.readNextToken();
        // TODO: to do to prevent for lack
//        if (nextToken.getType() != TokenType.LPARENT &&
//                nextToken.getType() != TokenType.IDENFR) {
//            tokenListIterator.unReadTokens(1);
//            return null;
//        } else {
            tokenListIterator.unReadTokens(1);
            AddExp addExp = addExpParser.parseAddExp();
            addExps.add(addExp);
            nextToken = tokenListIterator.readNextToken();
            while (nextToken.getType() == TokenType.LSS ||
                    nextToken.getType() == TokenType.LEQ ||
                    nextToken.getType() == TokenType.GRE ||
                    nextToken.getType() == TokenType.GEQ) {
                relOps.add(nextToken);
                addExp = addExpParser.parseAddExp();
                addExps.add(addExp);
                nextToken = tokenListIterator.readNextToken();
            }
            tokenListIterator.unReadTokens(1);
        //}

        return new RelExp(addExps, relOps);
    }
}
