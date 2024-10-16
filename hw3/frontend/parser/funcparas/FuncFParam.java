package frontend.parser.funcparas;

import frontend.lexer.Token;
import frontend.parser.SyntaxOutput;

public class FuncFParam implements SyntaxOutput {
    private String syntaxName = "<FuncFParam>";
    private Token bType;
    private Token ident;
    private Token leftBracket;
    private Token rightBracket;

    public FuncFParam(Token bType, Token ident, Token leftBracket, Token rightBracket) {
        this.bType = bType;
        this.ident = ident;
        this.leftBracket = leftBracket;
        this.rightBracket = rightBracket;
    }

    @Override
    public String toSyntaxString() {
        StringBuilder sb = new StringBuilder();
        sb.append(bType.toSyntaxString());
        sb.append(ident.toSyntaxString());
        if (leftBracket != null) {
            sb.append(leftBracket.toSyntaxString());
        }
        if (rightBracket != null) {
            sb.append(rightBracket.toSyntaxString());
        }
        sb.append(syntaxName + "\n");
        return sb.toString();
    }
}
