package frontend.parser.expression;

import frontend.SyntaxError.ErrorType;
import frontend.SyntaxError.SyntaxErrorHandler;
import frontend.lexer.Token;
import frontend.lexer.TokenListIterator;
import frontend.lexer.TokenType;
import frontend.parser.funcparas.FuncRParams;
import frontend.parser.funcparas.FuncRParamsParser;
import frontend.symbol.SymbolFunc;
import frontend.symbol.SymbolTable;
import frontend.symbol.SymbolTableManager;
import frontend.symbol.SymbolType;

public class UnaryExpParser {
    private TokenListIterator tokenListIterator;

    public UnaryExpParser(TokenListIterator tokenListIterator) {
        this.tokenListIterator = tokenListIterator;
    }

    public boolean judgeProperty(SymbolType sr, SymbolType std) {
        if (std == SymbolType.Int || std == SymbolType.Char) {
            if (sr == SymbolType.CharArray || sr == SymbolType.IntArray) {
                return false;
            }
        } else if (std == SymbolType.CharArray) {
            if (sr != SymbolType.CharArray) {
                return false;
            }
        } else {
            if (sr != SymbolType.IntArray) {
                return false;
            }
        }
        return true;
    }

    public UnaryExp parseUnaryExp(int cur) {
        Token unaryOp = null;
        PrimaryExp primaryExp = null;
        UnaryExp unaryExp = null;
        Token ident = null;
        Token leftParent = null;
        FuncRParams funcRParams = null;
        Token rightParent = null;

        FuncRParamsParser funcRParamsParser = new FuncRParamsParser(tokenListIterator);
        PrimaryExpParser primaryExpParser = new PrimaryExpParser(tokenListIterator);

        SymbolTable curTable = SymbolTableManager.getInstance().getSymbolTable(cur);

        Token FirstToken = tokenListIterator.readNextToken();
        if (FirstToken.getType() == TokenType.MINU ||
            FirstToken.getType() == TokenType.NOT ||
            FirstToken.getType() == TokenType.PLUS) {
            unaryOp = FirstToken;
            FirstToken = tokenListIterator.readNextToken();
        }
        if (unaryOp != null) {
            tokenListIterator.unReadTokens(1);
            unaryExp = parseUnaryExp(cur);
            return new UnaryExp(unaryOp, primaryExp, unaryExp, ident, leftParent, funcRParams, rightParent);
        }

        if (FirstToken.getType() == TokenType.IDENFR) {
            Token SecondToken = tokenListIterator.readNextToken();
            if (SecondToken.getType() == TokenType.LPARENT) { // (
                // symbol
                String name = FirstToken.getValue();
                if (SymbolTableManager.getInstance().searchSymbol(name, curTable) == null) {
                    SyntaxErrorHandler.getInstance().WriteSyntaxError(ErrorType.c, FirstToken.getLine());
                }
                // parse
                ident = FirstToken;
                leftParent = SecondToken;
                Token ThirdToken = tokenListIterator.readNextToken();
                if (ThirdToken.getType() == TokenType.IDENFR ||
                    ThirdToken.getType() == TokenType.LPARENT ||
                    ThirdToken.getType() == TokenType.INTCON ||
                    ThirdToken.getType() == TokenType.CHRCON ||
                    ThirdToken.getType() == TokenType.PLUS ||
                    ThirdToken.getType() == TokenType.MINU) {
                    tokenListIterator.unReadTokens(1);
                    funcRParams = funcRParamsParser.parseFuncRParams(cur);
                } else {
                    tokenListIterator.unReadTokens(1);
                }
                ThirdToken = tokenListIterator.readNextToken();
                if (ThirdToken.getType() == TokenType.RPARENT) { // )
                    rightParent = ThirdToken;
                } else {
                    tokenListIterator.unReadTokens(1);
                    SyntaxErrorHandler.getInstance().WriteSyntaxError(ErrorType.j, SecondToken.getLine());
                }
                // symbol
                if (funcRParams != null) {
                    int paraSize = funcRParams == null ? 0 : funcRParams.size();
                    SymbolFunc symbolFunc = SymbolTableManager.getInstance().searchFunc(ident.getValue());
                    int funcParaSize = symbolFunc.parasNum();
                    if (paraSize != funcParaSize) {
                        SyntaxErrorHandler.getInstance().WriteSyntaxError(ErrorType.d, ident.getLine());
                    } else {
                        for (int i = 0; i < paraSize; i++) {
                            SymbolType RparaType = funcRParams.getPara(i).getType(cur);
                            if (RparaType == null) {
                                SyntaxErrorHandler.getInstance().WriteSyntaxError(ErrorType.c, ident.getLine());
                                break;
                            }
                            if (!judgeProperty(RparaType,symbolFunc.getPara(i).getType())) {
                                SyntaxErrorHandler.getInstance().WriteSyntaxError(ErrorType.e, ident.getLine());
                                break;
                            }
                        }
                    }
                }
            } else { // 如果没有括号，那么是PrimaryExp
                tokenListIterator.unReadTokens(2);
                primaryExp = primaryExpParser.parsePrimaryExp(cur);
            }
        } else {
            tokenListIterator.unReadTokens(1);
            primaryExp = primaryExpParser.parsePrimaryExp(cur);
        }
        return new UnaryExp(unaryOp, primaryExp, unaryExp, ident, leftParent, funcRParams, rightParent);
    }
}
