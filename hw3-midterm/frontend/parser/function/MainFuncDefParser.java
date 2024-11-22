package frontend.parser.function;

import frontend.SyntaxError.ErrorType;
import frontend.SyntaxError.SyntaxErrorHandler;
import frontend.lexer.Token;
import frontend.lexer.TokenListIterator;
import frontend.lexer.TokenType;
import frontend.parser.block.Block;
import frontend.parser.block.BlockParser;

public class MainFuncDefParser {
    private TokenListIterator tokenListIterator;

    public MainFuncDefParser(TokenListIterator tokenListIterator) {
        this.tokenListIterator = tokenListIterator;
    }

    public MainFuncDef parseMainFuncDef() {
        Token intToken = tokenListIterator.readNextToken();
        if (intToken.getType() != TokenType.INTTK) {
            tokenListIterator.unReadTokens(1);
            return null;
        }
        Token mainToken = tokenListIterator.readNextToken();
        if (mainToken.getType() != TokenType.MAINTK) {
            tokenListIterator.unReadTokens(2);
            return null;
        }
        Token leftParent = tokenListIterator.readNextToken();
        if (leftParent.getType() != TokenType.LPARENT) { // (
            tokenListIterator.unReadTokens(3);
            return null;
        }
        Token rightParent = tokenListIterator.readNextToken();
        if (rightParent.getType() != TokenType.RPARENT) { // )
            tokenListIterator.unReadTokens(1);
            SyntaxErrorHandler.getInstance().WriteSyntaxError(ErrorType.j, leftParent.getLine());
            rightParent = new Token(TokenType.RPARENT, ")", intToken.getLine());
        }
        BlockParser blockParser = new BlockParser(tokenListIterator);
        Block block = blockParser.parseBlock();

        return new MainFuncDef(intToken, mainToken, leftParent, rightParent, block);
    }
}
