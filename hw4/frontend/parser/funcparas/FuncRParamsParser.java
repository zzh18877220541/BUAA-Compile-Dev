package frontend.parser.funcparas;

import frontend.lexer.Token;
import frontend.lexer.TokenListIterator;
import frontend.lexer.TokenType;
import frontend.parser.expression.AddExp;
import frontend.parser.expression.AddExpParser;

import java.util.ArrayList;

public class FuncRParamsParser {
    private TokenListIterator tokenListIterator;

    public FuncRParamsParser(TokenListIterator tokenListIterator) {
        this.tokenListIterator = tokenListIterator;
    }

    public FuncRParams parseFuncRParams(int cur) {
        ArrayList<AddExp> exps = new ArrayList<>();
        ArrayList<Token> commas = new ArrayList<>();

        AddExpParser addExpParser = new AddExpParser(tokenListIterator);

        exps.add(addExpParser.parseAddExp(cur));
        Token FirstToken = tokenListIterator.readNextToken();
        while (FirstToken.getType() == TokenType.COMMA) {
            commas.add(FirstToken);
            AddExp NextExp = addExpParser.parseAddExp(cur);
            exps.add(NextExp);
            FirstToken = tokenListIterator.readNextToken();
        }
        tokenListIterator.unReadTokens(1);
        return new FuncRParams(exps, commas);
    }
}
