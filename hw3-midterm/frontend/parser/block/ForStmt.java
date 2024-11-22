package frontend.parser.block;

import frontend.lexer.Token;
import frontend.parser.SyntaxOutput;
import frontend.parser.expression.AddExp;
import frontend.parser.expression.Lval;

public class ForStmt implements SyntaxOutput {
    private String syntaxName = "<ForStmt>";
    private Lval lval;
    private Token assign;
    private AddExp exp;

    public ForStmt(Lval lval, Token assign, AddExp exp) {
        this.lval = lval;
        this.assign = assign;
        this.exp = exp;
    }

    @Override
    public String toSyntaxString() {
        StringBuilder sb = new StringBuilder();
        sb.append(lval.toSyntaxString());
        sb.append(assign.toSyntaxString());
        sb.append(exp.toSyntaxString());
        sb.append("<Exp>\n");
        sb.append(syntaxName + "\n");
        return sb.toString();
    }

}
