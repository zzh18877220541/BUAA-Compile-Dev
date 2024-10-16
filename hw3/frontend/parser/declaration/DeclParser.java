package frontend.parser.declaration;

import frontend.lexer.Token;
import frontend.lexer.TokenListIterator;
import frontend.lexer.TokenType;
import frontend.parser.declaration.constDeclaration.ConstDecl;
import frontend.parser.declaration.constDeclaration.ConstDeclParser;
import frontend.parser.declaration.varDeclaration.VarDecl;
import frontend.parser.declaration.varDeclaration.VarDeclParser;

public class DeclParser {
    private TokenListIterator tokenListIterator;

    public DeclParser(TokenListIterator tokenListIterator) {
        this.tokenListIterator = tokenListIterator;
    }

    public Decl parseDecl() {
        VarDeclParser varDeclParser = new VarDeclParser(tokenListIterator);
        ConstDeclParser constDeclParser = new ConstDeclParser(tokenListIterator);

        Token FirstToken = tokenListIterator.readNextToken();
        tokenListIterator.unReadTokens(1);
        if (FirstToken.getType() == TokenType.CONSTTK) {
            ConstDecl constDecl = constDeclParser.parseConstDecl();
            return new Decl(constDecl);
        } else if (FirstToken.getType() == TokenType.INTTK || FirstToken.getType() == TokenType.CHARTK) {
            VarDecl varDecl = varDeclParser.parseVarDecl();
            return new Decl(varDecl);
        } else {
            throw new Error("Unexpected token");
        }
    }
}
