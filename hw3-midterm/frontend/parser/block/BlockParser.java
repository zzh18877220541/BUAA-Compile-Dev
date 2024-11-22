package frontend.parser.block;

import frontend.lexer.Token;
import frontend.lexer.TokenListIterator;
import frontend.lexer.TokenType;

import java.util.ArrayList;

public class BlockParser {
    private TokenListIterator tokenListIterator;

    public BlockParser(TokenListIterator tokenListIterator) {
        this.tokenListIterator = tokenListIterator;
    }

    public Block parseBlock() {
        Token leftBrace = tokenListIterator.readNextToken();
        BlockItemParser blockItemParser = new BlockItemParser(tokenListIterator);
        ArrayList<BlockItem> blockItems = new ArrayList<>();

        blockItems.add(blockItemParser.parseBlockItem());
        Token nextToken = tokenListIterator.readNextToken();
        while (nextToken.getType() != TokenType.RBRACE) {
            tokenListIterator.unReadTokens(1);
            blockItems.add(blockItemParser.parseBlockItem());
            if (!tokenListIterator.hasNext()) {
                break;
            }
            nextToken = tokenListIterator.readNextToken();
        }
        Token rightBrace = nextToken;

        return new Block(leftBrace, blockItems, rightBrace);
    }
}
