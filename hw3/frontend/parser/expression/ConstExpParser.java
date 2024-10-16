package frontend.parser.expression;

import frontend.lexer.TokenListIterator;

public class ConstExpParser {
    private TokenListIterator tokenListIterator;

    public ConstExpParser(TokenListIterator tokenListIterator) {
        this.tokenListIterator = tokenListIterator;
    }

    public ConstExp parseConstExp() {
        AddExpParser addExpParser = new AddExpParser(tokenListIterator);
        AddExp addExp = addExpParser.parseAddExp();
        return new ConstExp(addExp);
    }

}
