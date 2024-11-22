package frontend.parser.declaration.constDeclaration;

import frontend.SyntaxError.ErrorType;
import frontend.SyntaxError.SyntaxErrorHandler;
import frontend.lexer.Token;
import frontend.lexer.TokenListIterator;
import frontend.lexer.TokenType;

import java.util.ArrayList;

public class ConstDeclParser {
    TokenListIterator tokenListIterator;

    public ConstDeclParser(TokenListIterator tokenListIterator) {
        this.tokenListIterator = tokenListIterator;
    }

    public ConstDecl parseConstDecl() {
        Token constToken;
        Token btype;
        ArrayList<ConstDef> constDefs = new ArrayList<ConstDef>();
        ArrayList<Token> commas = new ArrayList<Token>();
        Token semicolon = null;

        ConstDefParser constDefParser = new ConstDefParser(tokenListIterator);

        Token FirstToken = tokenListIterator.readNextToken();
        if (FirstToken.getType() != TokenType.CONSTTK) {
            throw new Error("CONSTTK expected in ConstDecl");
        } else {
            constToken = FirstToken;
        }
        Token SecondToken = tokenListIterator.readNextToken();
        if (SecondToken.getType() != TokenType.INTTK && SecondToken.getType() != TokenType.CHARTK) {
            throw new Error("INTTK or CHARTK expected in ConstDecl");
        } else {
            btype = SecondToken;
        }
        while (tokenListIterator.hasNext()) {
            Token nowToken = tokenListIterator.readNextToken();
            if (nowToken.getType() == TokenType.IDENFR) {
                tokenListIterator.unReadTokens(1);
                constDefs.add(constDefParser.parseConstDef());
            } else {
                throw new Error("IDENFR expected in ConstDecl for ConstDef");
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
                SyntaxErrorHandler.getInstance().WriteSyntaxError(ErrorType.i, SecondToken.getLine());
                break;
            }
        }

        return new ConstDecl(constToken, btype, constDefs, commas, semicolon);
    }
}
