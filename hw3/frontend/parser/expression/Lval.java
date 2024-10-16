package frontend.parser.expression;

import frontend.lexer.Token;
import frontend.parser.SyntaxOutput;

public class Lval implements SyntaxOutput {
    private String syntaxName = "<LVal>";
    private Token ident;
    private Token leftBracket;
    private AddExp exp;
    private Token rightBracket;

    public Lval(Token ident, Token leftBracket, AddExp exp, Token rightBracket) {
        this.ident = ident;
        this.leftBracket = leftBracket;
        this.exp = exp;
        this.rightBracket = rightBracket;
    }

    @Override
    public String toSyntaxString() {
        StringBuilder sb = new StringBuilder();
        sb.append(ident.toSyntaxString());
        if (leftBracket != null) {
            sb.append(leftBracket.toSyntaxString());
        }
        if (exp != null) {
            sb.append(exp.toSyntaxString());
            sb.append("<Exp>\n");
        }
        if (rightBracket != null) {
            sb.append(rightBracket.toSyntaxString());
        }
        sb.append(syntaxName + "\n");
        return sb.toString();
    }
}
