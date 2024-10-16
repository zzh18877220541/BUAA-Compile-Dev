package frontend.parser.expression;

import frontend.lexer.Token;
import frontend.parser.SyntaxOutput;
import frontend.parser.funcparas.FuncRParams;

public class UnaryExp implements SyntaxOutput {
    private String syntaxName = "<UnaryExp>";
    private Token unaryOp;
    private UnaryExp unaryExp;
    private PrimaryExp primaryExp;
    private Token ident;
    private Token leftParent;
    private FuncRParams funcRParams;
    private Token rightParent;

    public UnaryExp(Token unaryOp, PrimaryExp primaryExp, UnaryExp unaryExp, Token ident, Token leftParent, FuncRParams funcRParams, Token rightParent) {
        this.unaryOp = unaryOp;
        this.unaryExp = unaryExp;
        this.primaryExp = primaryExp;
        this.ident = ident;
        this.leftParent = leftParent;
        this.funcRParams = funcRParams;
        this.rightParent = rightParent;
    }

    @Override
    public String toSyntaxString() {
        StringBuilder sb = new StringBuilder();
        if (unaryOp != null) {
            sb.append(unaryOp.toSyntaxString());
            sb.append("<UnaryOp>\n");
        }
        if (unaryExp != null) {
            sb.append(unaryExp.toSyntaxString());
        }
        if (primaryExp != null) {
            sb.append(primaryExp.toSyntaxString());
        }
        if (ident != null) {
            sb.append(ident.toSyntaxString());
        }
        if (leftParent != null) {
            sb.append(leftParent.toSyntaxString());
        }
        if (funcRParams != null) {
            sb.append(funcRParams.toSyntaxString());
        }
        if (rightParent != null) {
            sb.append(rightParent.toSyntaxString());
        }
        sb.append(syntaxName + "\n");
        return sb.toString();
    }

}
