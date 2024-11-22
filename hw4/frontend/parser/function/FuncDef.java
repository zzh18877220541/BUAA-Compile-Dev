package frontend.parser.function;

import frontend.lexer.Token;
import frontend.parser.SyntaxOutput;
import frontend.parser.funcparas.FuncFParams;
import frontend.parser.block.Block;

public class FuncDef implements SyntaxOutput {
    private String syntaxName = "<FuncDef>";
    private Token funcType;
    private Token ident;
    private Token leftParent;
    private FuncFParams funcFParams;
    private Token rightParent;
    private Block block;

    public FuncDef(Token funcType, Token ident, Token leftParent, FuncFParams funcFParams, Token rightParent, Block block) {
        this.funcType = funcType;
        this.ident = ident;
        this.leftParent = leftParent;
        this.funcFParams = funcFParams;
        this.rightParent = rightParent;
        this.block = block;
    }

    @Override
    public String toSyntaxString() {
        StringBuilder sb = new StringBuilder();
        sb.append(funcType.toSyntaxString());
        sb.append("<FuncType>\n");
        sb.append(ident.toSyntaxString());
        sb.append(leftParent.toSyntaxString());
        if (funcFParams != null) {
            sb.append(funcFParams.toSyntaxString());
        }
        sb.append(rightParent.toSyntaxString());
        sb.append(block.toSyntaxString());
        sb.append(syntaxName + "\n");
        return sb.toString();
    }
}
