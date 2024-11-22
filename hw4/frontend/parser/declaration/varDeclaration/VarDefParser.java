package frontend.parser.declaration.varDeclaration;

import frontend.SyntaxError.ErrorType;
import frontend.SyntaxError.SyntaxErrorHandler;
import frontend.lexer.Token;
import frontend.lexer.TokenListIterator;
import frontend.lexer.TokenType;
import frontend.parser.expression.ConstExp;
import frontend.parser.expression.ConstExpParser;
import frontend.symbol.Symbol;
import frontend.symbol.SymbolTable;
import frontend.symbol.SymbolTableManager;
import frontend.symbol.SymbolType;

public class VarDefParser {
    private TokenListIterator tokenListIterator;

    public VarDefParser(TokenListIterator tokenListIterator) {
        this.tokenListIterator = tokenListIterator;
    }

    public VarDef parseVarDef(int cur, SymbolType type) {
        Token ident = tokenListIterator.readNextToken();
        Token leftBracket = null;
        ConstExp constExp = null;
        Token rightBracket = null;
        Token assign = null;
        InitVal initVal = null;

        InitValParser initValParser = new InitValParser(tokenListIterator);
        ConstExpParser constExpParser = new ConstExpParser(tokenListIterator);

        SymbolType varType = type;
        SymbolTable curTable = SymbolTableManager.getInstance().getSymbolTable(cur);

        Token FirstToken = tokenListIterator.readNextToken();
        if (FirstToken.getType() == TokenType.LBRACK) { // [
            varType = type == SymbolType.Int ? SymbolType.IntArray : SymbolType.CharArray;
            leftBracket = FirstToken;
            constExp = constExpParser.parseConstExp(cur);
            Token SecondToken = tokenListIterator.readNextToken();
            if (SecondToken.getType() == TokenType.RBRACK) { // ]
                rightBracket = SecondToken;
            } else {
                tokenListIterator.unReadTokens(1);
                rightBracket = new Token(TokenType.RBRACK, "]", FirstToken.getLine());
                SyntaxErrorHandler.getInstance().WriteSyntaxError(ErrorType.k, FirstToken.getLine());
            }
        } else {
            tokenListIterator.unReadTokens(1);
        }
        Token ThirdToken = tokenListIterator.readNextToken();
        if (ThirdToken.getType() == TokenType.ASSIGN) {
            assign = ThirdToken;
            initVal = initValParser.parseInitVal(cur);
        } else {
            tokenListIterator.unReadTokens(1);
        }
        // symbol
        if (curTable != null) {
            String name = ident.getValue();
            Symbol symbol = new Symbol(name, ident.getLine(), varType);
            if (curTable.exist(name)) {
                SyntaxErrorHandler.getInstance().WriteSyntaxError(ErrorType.b, ident.getLine());
            } else {
                curTable.addSymbol(symbol);
            }
        } else {
            throw new Error("SymbolTable not found in VarDecl for VarDef");
        }
        return new VarDef(ident, leftBracket, constExp, rightBracket, assign, initVal);
    }
}
