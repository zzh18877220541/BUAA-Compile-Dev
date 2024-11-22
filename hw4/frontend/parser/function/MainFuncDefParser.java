package frontend.parser.function;

import frontend.SyntaxError.ErrorType;
import frontend.SyntaxError.SyntaxErrorHandler;
import frontend.lexer.Token;
import frontend.lexer.TokenListIterator;
import frontend.lexer.TokenType;
import frontend.parser.block.Block;
import frontend.parser.block.BlockParser;
import frontend.symbol.SymbolFunc;
import frontend.symbol.SymbolTable;
import frontend.symbol.SymbolTableManager;
import frontend.symbol.SymbolType;

import java.util.ArrayList;

public class MainFuncDefParser {
    private TokenListIterator tokenListIterator;

    public MainFuncDefParser(TokenListIterator tokenListIterator) {
        this.tokenListIterator = tokenListIterator;
    }

    public MainFuncDef parseMainFuncDef(int cur) {
        BlockParser blockParser = new BlockParser(tokenListIterator);

        Token intToken = tokenListIterator.readNextToken();
        if (intToken.getType() != TokenType.INTTK) {
            tokenListIterator.unReadTokens(1);
            return null;
        }
        Token mainToken = tokenListIterator.readNextToken();
        if (mainToken.getType() != TokenType.MAINTK) {
            tokenListIterator.unReadTokens(2);
            return null;
        }
        Token leftParent = tokenListIterator.readNextToken();
        if (leftParent.getType() != TokenType.LPARENT) { // (
            tokenListIterator.unReadTokens(3);
            return null;
        }
        Token rightParent = tokenListIterator.readNextToken();
        if (rightParent.getType() != TokenType.RPARENT) { // )
            tokenListIterator.unReadTokens(1);
            SyntaxErrorHandler.getInstance().WriteSyntaxError(ErrorType.j, leftParent.getLine());
            rightParent = new Token(TokenType.RPARENT, ")", intToken.getLine());
        }
        SymbolFunc mainFunc = new SymbolFunc("main", 1, SymbolType.Int, new ArrayList<>());
        SymbolTable curTable = SymbolTableManager.getInstance().getSymbolTable(cur);
        curTable.addSymbol(mainFunc);
        int parent = cur;
        Block block = blockParser.parseBlock(parent, mainFunc, false);

        return new MainFuncDef(intToken, mainToken, leftParent, rightParent, block);
    }
}
