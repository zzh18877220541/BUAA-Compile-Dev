package frontend.parser.declaration.varDeclaration;

import frontend.SyntaxError.ErrorType;
import frontend.SyntaxError.SyntaxErrorHandler;
import frontend.lexer.Token;
import frontend.lexer.TokenListIterator;
import frontend.lexer.TokenType;
import frontend.parser.declaration.constDeclaration.ConstDecl;
import frontend.parser.declaration.constDeclaration.ConstDef;
import frontend.parser.declaration.constDeclaration.ConstDefParser;

import java.util.ArrayList;

public class VarDeclParser {

    TokenListIterator tokenListIterator;

    public VarDeclParser(TokenListIterator tokenListIterator) {
        this.tokenListIterator = tokenListIterator;
    }

    public VarDecl parseVarDecl() {
        Token btype;
        ArrayList<VarDef> varDefs = new ArrayList<VarDef>();
        ArrayList<Token> commas = new ArrayList<Token>();
        Token semicolon = null;

        VarDefParser varDefParser = new VarDefParser(tokenListIterator);

        Token FirstToken = tokenListIterator.readNextToken();
        if (FirstToken.getType() != TokenType.INTTK && FirstToken.getType() != TokenType.CHARTK) {
            throw new Error("INTTK or CHARTK expected in ConstDecl");
        } else {
            btype = FirstToken;
        }
        while (tokenListIterator.hasNext()) {
            Token nowToken = tokenListIterator.readNextToken();
            if (nowToken.getType() == TokenType.IDENFR) {
                tokenListIterator.unReadTokens(1);
                varDefs.add(varDefParser.parseVarDef());
            } else {
                throw new Error("IDENFR expected in VarDecl for VarDef");
            }
            Token nextToken = tokenListIterator.readNextToken();
            if (nextToken.getType() == TokenType.COMMA) {
                commas.add(nextToken);
            } else if (nextToken.getType() == TokenType.SEMICN) {
                semicolon = nextToken;
                break;
            } else {
                tokenListIterator.unReadTokens(1);
                semicolon = new Token(TokenType.SEMICN, ";", nextToken.getLine());
                SyntaxErrorHandler.getInstance().WriteSyntaxError(ErrorType.i, FirstToken.getLine());
                break;
            }
        }

        return new VarDecl(btype, varDefs, commas, semicolon);
    }
}
