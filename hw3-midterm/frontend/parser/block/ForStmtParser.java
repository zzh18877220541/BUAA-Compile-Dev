package frontend.parser.block;

import frontend.lexer.Token;
import frontend.lexer.TokenListIterator;
import frontend.lexer.TokenType;
import frontend.parser.expression.AddExp;
import frontend.parser.expression.AddExpParser;
import frontend.parser.expression.Lval;
import frontend.parser.expression.LvalParser;

public class ForStmtParser {
    private TokenListIterator tokenListIterator;

    public ForStmtParser(TokenListIterator tokenListIterator) {
        this.tokenListIterator = tokenListIterator;
    }

    public ForStmt parseForStmt() {
        Lval lval = null;
        Token assign = null;
        AddExp exp = null;

        LvalParser lvalParser = new LvalParser(tokenListIterator);
        AddExpParser addExpParser = new AddExpParser(tokenListIterator);

        Token nextToken = tokenListIterator.readNextToken();
        if (nextToken.getType() != TokenType.IDENFR) {
            tokenListIterator.unReadTokens(1);
            return null;
        } else {
            tokenListIterator.unReadTokens(1);
            lval = lvalParser.parseLval();
            assign = tokenListIterator.readNextToken();
            exp = addExpParser.parseAddExp();
        }

        return new ForStmt(lval, assign, exp);
    }
}
