package frontend.parser.function;

import frontend.lexer.Token;
import frontend.lexer.TokenListIterator;
import frontend.parser.SyntaxOutput;
import frontend.parser.block.Block;

public class MainFuncDef implements SyntaxOutput {
    private final String syntaxName = "<MainFuncDef>";
    private Token intToken;
    private Token mainToken;
    private Token leftParent;
    private Token rightParent;
    private Block block;

    public MainFuncDef(Token intToken, Token mainToken, Token leftParent, Token rightParent, Block block) {
        this.intToken = intToken;
        this.mainToken = mainToken;
        this.leftParent = leftParent;
        this.rightParent = rightParent;
        this.block = block;
    }

    @Override
    public String toSyntaxString() {
        return intToken.toSyntaxString() + mainToken.toSyntaxString() + leftParent.toSyntaxString() + rightParent.toSyntaxString() + block.toSyntaxString() + syntaxName + "\n";
    }
}
