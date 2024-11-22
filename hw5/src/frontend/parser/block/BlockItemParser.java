package frontend.parser.block;

import frontend.lexer.Token;
import frontend.lexer.TokenListIterator;
import frontend.parser.declaration.Decl;
import frontend.lexer.TokenType;
import frontend.parser.declaration.DeclParser;


public class BlockItemParser {
    private TokenListIterator tokenListIterator;

    public BlockItemParser(TokenListIterator tokenListIterator) {
        this.tokenListIterator = tokenListIterator;
    }

    public BlockItem parseBlockItem(int cur, boolean inLoop) {
        Decl decl = null;
        Stmt stmt = null;

        DeclParser declParser = new DeclParser(tokenListIterator);
        StmtParser stmtParser = new StmtParser(tokenListIterator);

        Token FirstToken = tokenListIterator.readNextToken();
        if (FirstToken.getType() == TokenType.INTTK ||
                FirstToken.getType() == TokenType.CHARTK ||
                FirstToken.getType() == TokenType.CONSTTK) {
            tokenListIterator.unReadTokens(1);
            decl = declParser.parseDecl(cur);
        } else if (FirstToken.getType() == TokenType.RBRACE) {
            tokenListIterator.unReadTokens(1);
        } else {
            tokenListIterator.unReadTokens(1);
            stmt = stmtParser.parseStmt(cur, inLoop);
        }


        return new BlockItem(decl, stmt);
    }
}
