package frontend.parser.funcparas;

import frontend.lexer.Token;
import frontend.lexer.TokenListIterator;
import frontend.lexer.TokenType;

import java.util.ArrayList;

public class FuncFParamsParser {
    private TokenListIterator tokenListIterator;

    public FuncFParamsParser(TokenListIterator tokenListIterator) {
        this.tokenListIterator = tokenListIterator;
    }

    public FuncFParams parseFuncFParams() {
        ArrayList<FuncFParam> funcFParams = new ArrayList<>();
        ArrayList<Token> commas = new ArrayList<>();

        FuncFParamParser funcFParamParser = new FuncFParamParser(tokenListIterator);

        funcFParams.add(funcFParamParser.parseFuncFParam());
        Token nextToken = tokenListIterator.readNextToken();
        while (nextToken.getType() == TokenType.COMMA) {
            commas.add(nextToken);
            FuncFParam nextFuncFParam = funcFParamParser.parseFuncFParam();
            funcFParams.add(nextFuncFParam);
            nextToken = tokenListIterator.readNextToken();
        }
        tokenListIterator.unReadTokens(1);

        return new FuncFParams(funcFParams, commas);
    }
}
