package frontend.parser.block;

import frontend.SyntaxError.ErrorType;
import frontend.SyntaxError.SyntaxErrorHandler;
import frontend.lexer.Token;
import frontend.lexer.TokenListIterator;
import frontend.lexer.TokenType;
import frontend.parser.expression.AddExp;
import frontend.parser.expression.AddExpParser;
import frontend.parser.expression.Lval;
import frontend.parser.expression.LvalParser;
import frontend.symbol.Symbol;
import frontend.symbol.SymbolFunc;
import frontend.symbol.SymbolTable;
import frontend.symbol.SymbolTableManager;
import frontend.symbol.SymbolType;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StmtParser {
    private TokenListIterator tokenListIterator;

    public StmtParser(TokenListIterator tokenListIterator) {
        this.tokenListIterator = tokenListIterator;
    }

    public boolean isErrorPrintf(ArrayList<AddExp> addExps, Token stringConst) {
        String regex = "%[cd]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(stringConst.getValue());
        int count = 0;
        while (matcher.find()) {
            count++;
        }
        if (count != addExps.size()) {
            return true;
        }
        return false;
    }

    public Stmt parseStmt(int cur, boolean inLoop) {
        Lval lval = null;
        Token assign = null;
        AddExp addExp = null;
        Token getintToken = null;
        Token leftParent_getint = null;
        Token rightParent_getint = null;
        Token getcharToken = null;
        Token leftParent_getchar = null;
        Token rightParent_getchar = null;
        Token semicolon_Lval = null;

        AddExp exp = null;
        Token semicolon_singleExp = null;

        Block block = null;

        Token ifToken = null;
        Token leftParent = null;
        LOrExp LOrExp = null;
        Token rightParent = null;
        Stmt stmt_if = null;
        Token elseToken = null;
        Stmt stmt_else = null;

        Token breakToken = null;
        Token semicolon_break = null;

        Token continueToken = null;
        Token semicolon_continue = null;

        Token forToken = null;
        Token leftParent_for = null;
        ForStmt forStmt1 = null;
        Token semicolon_for1 = null;
        LOrExp forLOrExp = null;
        Token semicolon_for2 = null;
        ForStmt forStmt2 = null;
        Token rightParent_for = null;
        Stmt stmt_for = null;

        Token returnToken = null;
        AddExp addExp_return = null;
        Token semicolon_return = null;

        Token printfToken = null;
        Token leftParent_printf = null;
        Token stringConst = null;
        ArrayList<Token> comma_printf = new ArrayList<>();
        ArrayList<AddExp> addExps_printf = new ArrayList<>();
        Token rightParent_printf = null;
        Token semicolon_printf = null;

        LOrExpParser lOrExpParser = new LOrExpParser(tokenListIterator);
        AddExpParser addExpParser = new AddExpParser(tokenListIterator);
        ForStmtParser forStmtParser = new ForStmtParser(tokenListIterator);
        BlockParser blockParser = new BlockParser(tokenListIterator);
        LvalParser lvalParser = new LvalParser(tokenListIterator);

        SymbolTable curTable = SymbolTableManager.getInstance().getSymbolTable(cur);
        SymbolFunc baseFunctionunc = curTable.getBaseFunction();

        Token FirstToken = tokenListIterator.readNextToken();

        /*
        if
         */
        if (FirstToken.getType() == TokenType.IFTK) {
            ifToken = FirstToken;
            leftParent = tokenListIterator.readNextToken();
            LOrExp = lOrExpParser.parseLOrExp(cur);
            rightParent = tokenListIterator.readNextToken();
            if (rightParent.getType() != TokenType.RPARENT) {
                tokenListIterator.unReadTokens(1);
                rightParent = new Token(TokenType.RPARENT, ")", ifToken.getLine());
                SyntaxErrorHandler.getInstance().WriteSyntaxError(ErrorType.j, leftParent.getLine());
            }
            stmt_if = parseStmt(cur, inLoop);
            Token NextToken = tokenListIterator.readNextToken();
            if (NextToken.getType() == TokenType.ELSETK) {
                elseToken = NextToken;
                stmt_else = parseStmt(cur, inLoop);
            } else {
                tokenListIterator.unReadTokens(1);
            }
            return new Stmt(ifToken, leftParent, LOrExp, rightParent, stmt_if, elseToken, stmt_else);
        }
        /*
        for
         */
        if (FirstToken.getType() == TokenType.FORTK) {
            forToken = FirstToken;
            leftParent_for = tokenListIterator.readNextToken();
            Token nextToken = tokenListIterator.readNextToken();
            if (nextToken.getType() != TokenType.SEMICN) {
                tokenListIterator.unReadTokens(1);
                forStmt1 = forStmtParser.parseForStmt(cur);
            } else {
                tokenListIterator.unReadTokens(1);
            }
            semicolon_for1 = tokenListIterator.readNextToken();
            nextToken = tokenListIterator.readNextToken();
            if (nextToken.getType() != TokenType.SEMICN) {
                tokenListIterator.unReadTokens(1);
                forLOrExp = lOrExpParser.parseLOrExp(cur);
            } else {
                tokenListIterator.unReadTokens(1);
            }
            semicolon_for2 = tokenListIterator.readNextToken();
            nextToken = tokenListIterator.readNextToken();
            if (nextToken.getType() != TokenType.RPARENT) {
                tokenListIterator.unReadTokens(1);
                forStmt2 = forStmtParser.parseForStmt(cur);
            } else {
                tokenListIterator.unReadTokens(1);
            }
            rightParent_for = tokenListIterator.readNextToken();
            stmt_for = parseStmt(cur, true);
            return new Stmt(forToken, leftParent_for, forStmt1, semicolon_for1, forLOrExp, semicolon_for2, forStmt2, rightParent_for, stmt_for);
        }
        /*
        return
         */
        if (FirstToken.getType() == TokenType.RETURNTK) {
            // parse
            returnToken = FirstToken;
            int index0 = tokenListIterator.getCurrentIndex();
            Token nextToken = tokenListIterator.readNextToken();
            if (nextToken.getType() == TokenType.IDENFR
                    || nextToken.getType() == TokenType.CHRCON
                    || nextToken.getType() == TokenType.INTCON
                    || nextToken.getType() == TokenType.PLUS
                    || nextToken.getType() == TokenType.MINU
                    || nextToken.getType() == TokenType.NOT
                    || nextToken.getType() == TokenType.LPARENT) {
                tokenListIterator.unReadTokens(1);
                addExp_return = addExpParser.parseAddExp(cur);
                nextToken = tokenListIterator.readNextToken();
                if (nextToken.getType() == TokenType.ASSIGN) {
                    int index1 = tokenListIterator.getCurrentIndex();
                    tokenListIterator.unReadTokens(index1 - index0);
                    addExp_return = null;
                } else {
                    tokenListIterator.unReadTokens(1);
                }
            } else {
                tokenListIterator.unReadTokens(1);
            }
            nextToken = tokenListIterator.readNextToken();
            if (nextToken.getType() == TokenType.SEMICN) {
                semicolon_return = new Token(TokenType.SEMICN, ";", returnToken.getLine());
            } else {
                tokenListIterator.unReadTokens(1);
                semicolon_return = new Token(TokenType.SEMICN, ";", returnToken.getLine());
                SyntaxErrorHandler.getInstance().WriteSyntaxError(ErrorType.i, returnToken.getLine());
            }
            if (baseFunctionunc.getType() == SymbolType.VoidFunc && addExp_return != null) {
                SyntaxErrorHandler.getInstance().WriteSyntaxError(ErrorType.f, FirstToken.getLine());
            }
            return new Stmt(returnToken, addExp_return, semicolon_return);
        }
        /*
        break
         */
        if (FirstToken.getType() == TokenType.BREAKTK) {
            // symbol
            if (!inLoop) {
                SyntaxErrorHandler.getInstance().WriteSyntaxError(ErrorType.m, FirstToken.getLine());
            }
            // parse
            breakToken = FirstToken;
            Token nextToken = tokenListIterator.readNextToken();
            if (nextToken.getType() == TokenType.SEMICN) {
                semicolon_break = nextToken;
            } else {
                tokenListIterator.unReadTokens(1);
                semicolon_break = new Token(TokenType.SEMICN, ";", breakToken.getLine());
                SyntaxErrorHandler.getInstance().WriteSyntaxError(ErrorType.i, breakToken.getLine());
            }
            return new Stmt(breakToken, semicolon_break, null, null);
        }
        /*
        continue
         */
        if (FirstToken.getType() == TokenType.CONTINUETK) {
            // symbol
            if (!inLoop) {
                SyntaxErrorHandler.getInstance().WriteSyntaxError(ErrorType.m, FirstToken.getLine());
            }
            // parse
            continueToken = FirstToken;
            Token nextToken = tokenListIterator.readNextToken();
            if (nextToken.getType() == TokenType.SEMICN) {
                semicolon_continue = nextToken;
            } else {
                tokenListIterator.unReadTokens(1);
                semicolon_continue = new Token(TokenType.SEMICN, ";", continueToken.getLine());
                SyntaxErrorHandler.getInstance().WriteSyntaxError(ErrorType.i, continueToken.getLine());
            }
            return new Stmt(null, null, continueToken, semicolon_continue);
        }
        /*
        printf
         */
        if (FirstToken.getType() == TokenType.PRINTFTK) {
            printfToken = FirstToken;
            leftParent_printf = tokenListIterator.readNextToken();
            stringConst = tokenListIterator.readNextToken();
            Token nextToken = tokenListIterator.readNextToken();
            while (nextToken.getType() == TokenType.COMMA) {
                comma_printf.add(nextToken);
                addExps_printf.add(addExpParser.parseAddExp(cur));
                nextToken = tokenListIterator.readNextToken();
            }
            tokenListIterator.unReadTokens(1);
            nextToken = tokenListIterator.readNextToken();
            if (nextToken.getType() != TokenType.RPARENT) { // )
                tokenListIterator.unReadTokens(1);
                rightParent_printf = new Token(TokenType.RPARENT, ")", printfToken.getLine());
                SyntaxErrorHandler.getInstance().WriteSyntaxError(ErrorType.j, leftParent_printf.getLine());
            } else {
                rightParent_printf = nextToken;
            }
            nextToken = tokenListIterator.readNextToken();
            if (nextToken.getType() != TokenType.SEMICN) {
                tokenListIterator.unReadTokens(1);
                semicolon_printf = new Token(TokenType.SEMICN, ";", printfToken.getLine());
                SyntaxErrorHandler.getInstance().WriteSyntaxError(ErrorType.i, leftParent_printf.getLine());
            } else {
                semicolon_printf = nextToken;
            }
            // symbol
            if (isErrorPrintf(addExps_printf, stringConst)) {
                SyntaxErrorHandler.getInstance().WriteSyntaxError(ErrorType.l, printfToken.getLine());
            }
            return new Stmt(printfToken, leftParent_printf, stringConst, comma_printf, addExps_printf, rightParent_printf, semicolon_printf);
        }
        /*
        block
         */
        if (FirstToken.getType() == TokenType.LBRACE) {
            tokenListIterator.unReadTokens(1);
            SymbolFunc symbolFunc = SymbolTableManager.getInstance().getSymbolTable(cur).getBaseFunction();
            int parent = cur;
            block = blockParser.parseBlock(parent, symbolFunc, inLoop);
            return new Stmt(block);
        }
        /*
        ;
         */
        if (FirstToken.getType() == TokenType.SEMICN) {
            return new Stmt(FirstToken);
        }

        /*
        lval
         */
        // a = 1; a[1] = 1; a = getint(); a = getchar(); a = b + c;
        tokenListIterator.unReadTokens(1);
        int index0 = tokenListIterator.getCurrentIndex();
        lvalParser.try_parseLval(cur);
        Token nextToken = tokenListIterator.readNextToken();
        int index1 = tokenListIterator.getCurrentIndex();
        int n = index1 - index0;
        if (nextToken.getType() == TokenType.ASSIGN) {
            tokenListIterator.unReadTokens(n);
            lval = lvalParser.parseLval(cur);
            // symbol
            Symbol symbol_lval = SymbolTableManager.getInstance().searchSymbol(lval.getIdent().getValue(), curTable);
            if (symbol_lval != null) {
                if (symbol_lval.getType() == SymbolType.ConstInt || symbol_lval.getType() == SymbolType.ConstChar
                || symbol_lval.getType() == SymbolType.ConstCharArray || symbol_lval.getType() == SymbolType.ConstIntArray) {
                    SyntaxErrorHandler.getInstance().WriteSyntaxError(ErrorType.h, lval.getIdent().getLine());
                }
            }
            // parse
            nextToken = tokenListIterator.readNextToken();
            assign = nextToken;
            nextToken = tokenListIterator.readNextToken();
            if (nextToken.getType() == TokenType.GETINTTK) {
                getintToken = nextToken;
                leftParent_getint = tokenListIterator.readNextToken();
                rightParent_getint = tokenListIterator.readNextToken();
                if (rightParent_getint.getType() != TokenType.RPARENT) {
                    tokenListIterator.unReadTokens(1);
                    rightParent_getint = new Token(TokenType.RPARENT, ")", getintToken.getLine());
                    SyntaxErrorHandler.getInstance().WriteSyntaxError(ErrorType.j, leftParent_getint.getLine());
                }
            } else if (nextToken.getType() == TokenType.GETCHARTK) {
                getcharToken = nextToken;
                leftParent_getchar = tokenListIterator.readNextToken();
                rightParent_getchar = tokenListIterator.readNextToken();
                if (rightParent_getchar.getType() != TokenType.RPARENT) {
                    tokenListIterator.unReadTokens(1);
                    rightParent_getchar = new Token(TokenType.RPARENT, ")", getcharToken.getLine());
                    SyntaxErrorHandler.getInstance().WriteSyntaxError(ErrorType.j, leftParent_getchar.getLine());
                }
            } else {
                tokenListIterator.unReadTokens(1);
                addExp = addExpParser.parseAddExp(cur);
            }
            nextToken = tokenListIterator.readNextToken();
            if (nextToken.getType() != TokenType.SEMICN) {
                tokenListIterator.unReadTokens(1);
                semicolon_Lval = new Token(TokenType.SEMICN, ";", assign.getLine());
                SyntaxErrorHandler.getInstance().WriteSyntaxError(ErrorType.i, assign.getLine());
            } else {
                semicolon_Lval = nextToken;
            }
            return new Stmt(lval, assign, addExp, semicolon_Lval, getintToken, leftParent_getint, rightParent_getint, getcharToken, leftParent_getchar, rightParent_getchar);
        } else {
            tokenListIterator.unReadTokens(n);
            exp = addExpParser.parseAddExp(cur);
            nextToken = tokenListIterator.readNextToken();
            if (nextToken.getType() != TokenType.SEMICN) {
                tokenListIterator.unReadTokens(1);
                semicolon_singleExp = new Token(TokenType.SEMICN, ";", FirstToken.getLine());
                SyntaxErrorHandler.getInstance().WriteSyntaxError(ErrorType.i, FirstToken.getLine());
            } else {
                semicolon_singleExp = nextToken;
            }
            return new Stmt(exp, semicolon_singleExp);
        }
    }
}