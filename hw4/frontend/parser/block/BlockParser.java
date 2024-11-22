package frontend.parser.block;

import frontend.SyntaxError.ErrorType;
import frontend.SyntaxError.SyntaxErrorHandler;
import frontend.lexer.Token;
import frontend.lexer.TokenListIterator;
import frontend.lexer.TokenType;
import frontend.symbol.Symbol;
import frontend.symbol.SymbolFunc;
import frontend.symbol.SymbolTable;
import frontend.symbol.SymbolTableManager;
import frontend.symbol.SymbolType;

import java.util.ArrayList;

public class BlockParser {
    private TokenListIterator tokenListIterator;

    public BlockParser(TokenListIterator tokenListIterator) {
        this.tokenListIterator = tokenListIterator;
    }

    public Block parseBlock(int parent, SymbolFunc baseFunction, boolean inLoop) {
        Token leftBrace = tokenListIterator.readNextToken();
        BlockItemParser blockItemParser = new BlockItemParser(tokenListIterator);
        ArrayList<BlockItem> blockItems = new ArrayList<>();

        // create new symbol table for block
        SymbolTable curTable = SymbolTableManager.getInstance().createSymbolTable(parent, baseFunction, inLoop);
        int cur = curTable.getId();
        SymbolTable parentTable = SymbolTableManager.getInstance().getSymbolTable(parent);
        SymbolFunc symbolFunc = curTable.getBaseFunction();
        SymbolFunc parentFunc = parentTable.getBaseFunction();

        // symbol add paras
        if ((symbolFunc != null && parentFunc == null) ||
                (!symbolFunc.getName().equals(parentFunc.getName()))) {
            for (Symbol para : symbolFunc.getParas()) {
                if (curTable.exist(para.getName())) {
                    SyntaxErrorHandler.getInstance().WriteSyntaxError(ErrorType.b, para.getLineNum());
                } else {
                    curTable.addSymbol(para);
                }
            }
        }

        blockItems.add(blockItemParser.parseBlockItem(cur, inLoop));
        Token nextToken = tokenListIterator.readNextToken();
        while (nextToken.getType() != TokenType.RBRACE) {
            tokenListIterator.unReadTokens(1);
            blockItems.add(blockItemParser.parseBlockItem(cur, inLoop));
            if (!tokenListIterator.hasNext()) {
                break;
            }
            nextToken = tokenListIterator.readNextToken();
        }
        // symbol: lack return ?
        if ((symbolFunc != null && parentFunc == null) ||
                (!symbolFunc.getName().equals(parentFunc.getName()))) {
            BlockItem lastBlockItem = blockItems.get(blockItems.size() - 1);
            if (!lastBlockItem.isReturn() && symbolFunc.getType() != SymbolType.VoidFunc) {
                SyntaxErrorHandler.getInstance().WriteSyntaxError(ErrorType.g, nextToken.getLine());
            }
        }

        Token rightBrace = nextToken;

        return new Block(leftBrace, blockItems, rightBrace);
    }
}
