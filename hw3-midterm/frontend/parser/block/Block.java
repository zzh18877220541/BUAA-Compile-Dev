package frontend.parser.block;

import frontend.lexer.Token;
import frontend.parser.SyntaxOutput;
import frontend.parser.declaration.Decl;

import java.util.ArrayList;

public class Block implements SyntaxOutput {
    private String syntaxName = "<Block>";
    private Token leftBrace;
    private ArrayList<BlockItem> blockItems;
    private Token rightBrace;

    public Block(Token leftBrace, ArrayList <BlockItem> blockItems, Token rightBrace) {
        this.leftBrace = leftBrace;
        this.blockItems = blockItems;
        this.rightBrace = rightBrace;
    }

    @Override
    public String toSyntaxString() {
        StringBuilder sb = new StringBuilder();
        sb.append(leftBrace.toSyntaxString());
        for (BlockItem blockItem : blockItems) {
            sb.append(blockItem.toSyntaxString());
        }
        sb.append(rightBrace.toSyntaxString());
        sb.append(syntaxName + "\n");
        return sb.toString();
    }
}
