package frontend.parser.expression;

import frontend.lexer.Token;

public class PrimaryExp {
    private String syntaxName = "<PrimaryExp>";
    private Token leftParent;
    private AddExp addExp;
    private Token rightParent;
    private Lval lval;
    private Token INT_CONST;
    private Token CHAR_CONST;

    public PrimaryExp(Token leftParent, AddExp addExp, Token rightParent, Lval lval, Token INT_CONST, Token CHAR_CONST) {
        this.leftParent = leftParent;
        this.addExp = addExp;
        this.rightParent = rightParent;
        this.lval = lval;
        this.INT_CONST = INT_CONST;
        this.CHAR_CONST = CHAR_CONST;
    }

    public String toSyntaxString() {
        StringBuilder sb = new StringBuilder();
        if (leftParent != null) {
            sb.append(leftParent.toSyntaxString());
        }
        if (addExp != null) {
            sb.append(addExp.toSyntaxString());
            sb.append("<Exp>\n");
        }
        if (rightParent != null) {
            sb.append(rightParent.toSyntaxString());
        }
        if (lval != null) {
            sb.append(lval.toSyntaxString());
        }
        if (INT_CONST != null) {
            sb.append(INT_CONST.toSyntaxString());
            sb.append("<Number>\n");
        }
        if (CHAR_CONST != null) {
            sb.append(CHAR_CONST.toSyntaxString());
            sb.append("<Character>\n");
        }
        sb.append(syntaxName + "\n");
        return sb.toString();
    }
}
