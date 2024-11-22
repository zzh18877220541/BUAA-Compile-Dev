package frontend.parser.funcparas;

import frontend.lexer.Token;
import frontend.lexer.TokenListIterator;
import frontend.lexer.TokenType;
import frontend.symbol.Symbol;
import frontend.symbol.SymbolType;

import java.util.ArrayList;

public class FuncFParamsParser {
    private TokenListIterator tokenListIterator;

    public FuncFParamsParser(TokenListIterator tokenListIterator) {
        this.tokenListIterator = tokenListIterator;
    }

    public FuncFParams parseFuncFParams(ArrayList<Symbol> symbols) {
        ArrayList<FuncFParam> funcFParams = new ArrayList<>();
        ArrayList<Token> commas = new ArrayList<>();

        SymbolType type = null;

        FuncFParamParser funcFParamParser = new FuncFParamParser(tokenListIterator);

        FuncFParam nextFuncFParam = funcFParamParser.parseFuncFParam();
        funcFParams.add(nextFuncFParam);
        symbols.add(nextFuncFParam.toSymbol());
        Token nextToken = tokenListIterator.readNextToken();
        while (nextToken.getType() == TokenType.COMMA) {
            commas.add(nextToken);
            nextFuncFParam = funcFParamParser.parseFuncFParam();
            funcFParams.add(nextFuncFParam);
            symbols.add(nextFuncFParam.toSymbol());
            nextToken = tokenListIterator.readNextToken();
        }
        tokenListIterator.unReadTokens(1);

        return new FuncFParams(funcFParams, commas);
    }
}
