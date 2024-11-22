package frontend.parser;

import frontend.lexer.Token;
import frontend.lexer.TokenList;
import frontend.lexer.TokenListIterator;
import frontend.lexer.TokenType;
import frontend.parser.declaration.Decl;
import frontend.parser.declaration.DeclParser;
import frontend.parser.function.FuncDef;
import frontend.parser.function.FuncDefParser;
import frontend.parser.function.MainFuncDef;
import frontend.parser.function.MainFuncDefParser;
import frontend.symbol.SymbolTableManager;

import java.util.ArrayList;

public class CompUnitParser {
    private TokenList tokenList;
    private TokenListIterator tokenIterator;

    private ArrayList<Decl> decls;
    private ArrayList<FuncDef> funcDefs;
    private MainFuncDef mainFuncDef;

    public CompUnitParser(TokenList tokenList) {
        this.tokenList = tokenList;
        tokenIterator = new TokenListIterator(tokenList);
        this.decls = new ArrayList<Decl>();
        this.funcDefs = new ArrayList<FuncDef>();
        this.mainFuncDef = null;
    }

    public CompUnit parseCompUnit() {
        SymbolTableManager.getInstance().createSymbolTable(0, null, false);

        parseDecls();
        parseFuncDefs();
        parseMainFuncDef();

        return new CompUnit(this.decls, this.funcDefs, this.mainFuncDef);
    }

    private void parseDecls() {
        DeclParser declParser = new DeclParser(this.tokenIterator);

        Token FirstToken = tokenIterator.readNextToken();
        Token SecondToken = tokenIterator.readNextToken();
        while (this.tokenIterator.hasNext()) {
            Token ThirdToken = tokenIterator.readNextToken();
            // 首先判断存不存在全局变量声明
            if (ThirdToken.getType() == TokenType.LPARENT) {
                tokenIterator.unReadTokens(3);
                return; // 直接读到函数，不存在全局变量声明
            } else {
                tokenIterator.unReadTokens(3);
                Decl decl = declParser.parseDecl(1);
                this.decls.add(decl);
            }
            FirstToken = tokenIterator.readNextToken();
            SecondToken = tokenIterator.readNextToken();
        }
    }

    private void parseFuncDefs() {
        FuncDefParser funcDefParser = new FuncDefParser(this.tokenIterator);

        Token FirstToken = tokenIterator.readNextToken();
        Token SecondToken = tokenIterator.readNextToken();
        while (this.tokenIterator.hasNext()) {
            if (SecondToken.getType() == TokenType.MAINTK) {
                tokenIterator.unReadTokens(2);
                return; // 直接读到main函数，不存在函数定义
            } else {
                tokenIterator.unReadTokens(2);
                FuncDef funcDef = funcDefParser.parseFuncDef(1);
                this.funcDefs.add(funcDef);
            }
            FirstToken = tokenIterator.readNextToken();
            SecondToken = tokenIterator.readNextToken();
        }
    }

    private void parseMainFuncDef() {
        MainFuncDefParser mainFuncDefParser = new MainFuncDefParser(this.tokenIterator);
        this.mainFuncDef = mainFuncDefParser.parseMainFuncDef(1);
    }
}
