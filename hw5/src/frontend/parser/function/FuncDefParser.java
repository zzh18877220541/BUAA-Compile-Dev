package frontend.parser.function;

import frontend.SyntaxError.ErrorType;
import frontend.SyntaxError.SyntaxErrorHandler;
import frontend.lexer.Token;
import frontend.lexer.TokenListIterator;
import frontend.lexer.TokenType;
import frontend.parser.block.BlockParser;
import frontend.parser.block.Block;
import frontend.parser.funcparas.FuncFParams;
import frontend.parser.funcparas.FuncFParamsParser;
import frontend.symbol.Symbol;
import frontend.symbol.SymbolFunc;
import frontend.symbol.SymbolTable;
import frontend.symbol.SymbolTableManager;
import frontend.symbol.SymbolType;

import java.util.ArrayList;


public class FuncDefParser {
    private TokenListIterator tokenListIterator;

    public FuncDefParser(TokenListIterator tokenListIterator) {
        this.tokenListIterator = tokenListIterator;
    }

    public FuncDef parseFuncDef(int cur) {
        Token funcType = tokenListIterator.readNextToken();
        Token ident = tokenListIterator.readNextToken();
        Token leftParent = tokenListIterator.readNextToken();
        Token nextToken = tokenListIterator.readNextToken();
        Token rightParent = null;
        FuncFParams funcFParams = null;

        SymbolTable curTable = SymbolTableManager.getInstance().getSymbolTable(cur);
        ArrayList<Symbol> symbolParas = new ArrayList<Symbol>();
        SymbolType type = funcType.getType() == TokenType.INTTK ? SymbolType.IntFunc :
                        funcType.getType() == TokenType.CHARTK? SymbolType.CharFunc:
                        SymbolType.VoidFunc;

        FuncFParamsParser funcFParamsParser = new FuncFParamsParser(tokenListIterator);
        BlockParser blockParser = new BlockParser(tokenListIterator);

        // 既不是右括号也不是左大括号，证明有参数
        if (nextToken.getType() == TokenType.INTTK || nextToken.getType() == TokenType.CHARTK) {
            tokenListIterator.unReadTokens(1);
            funcFParams = funcFParamsParser.parseFuncFParams(symbolParas);
        } else {
            tokenListIterator.unReadTokens(1);
        }
        nextToken = tokenListIterator.readNextToken();
        // 参数读完之后，如果还不是右括号，证明缺少右括号
        if (nextToken.getType() != TokenType.RPARENT) { // )
            tokenListIterator.unReadTokens(1);
            rightParent = new Token(TokenType.RPARENT, ")", leftParent.getLine());
            SyntaxErrorHandler.getInstance().WriteSyntaxError(ErrorType.j, leftParent.getLine());
        } else {
            rightParent = nextToken;
        }
        // symbol
        SymbolFunc curFunc = new SymbolFunc(ident.getValue(), leftParent.getLine(), type, symbolParas);
        if (curTable.exist(curFunc.getName())) {
            SyntaxErrorHandler.getInstance().WriteSyntaxError(ErrorType.b, curFunc.getLineNum());
        } else {
            curTable.addSymbol(curFunc);
        }
        int parent = cur;
        Block block = blockParser.parseBlock(parent, curFunc, false);

        return new FuncDef(funcType, ident, leftParent, funcFParams, rightParent, block);
    }
}
