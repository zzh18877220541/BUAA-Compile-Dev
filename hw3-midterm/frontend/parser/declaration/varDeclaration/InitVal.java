package frontend.parser.declaration.varDeclaration;

import frontend.lexer.Token;
import frontend.parser.SyntaxOutput;
import frontend.parser.expression.AddExp;

import java.util.ArrayList;

public class InitVal implements SyntaxOutput {
    private String syntaxName = "<InitVal>";
    private ArrayList<AddExp> Exp;
    private Token leftBrace;
    private Token rightBrace;
    private ArrayList<Token> commas;
    private Token STRING_CONST;

    public InitVal(ArrayList<AddExp> Exp, Token leftBrace, Token rightBrace, ArrayList<Token> commas, Token STRING_CONST) {
        this.Exp = Exp;
        this.leftBrace = leftBrace;
        this.rightBrace = rightBrace;
        this.commas = commas;
        this.STRING_CONST = STRING_CONST;
    }

    @Override
    public String toSyntaxString() {
        StringBuilder sb = new StringBuilder();
        if (STRING_CONST != null) {
            sb.append(STRING_CONST.toSyntaxString());
        }
        if (leftBrace != null) {
            sb.append(leftBrace.toSyntaxString());
        }
        for (int i = 0; i < Exp.size(); i++) {
            sb.append(Exp.get(i).toSyntaxString());
            sb.append("<Exp>\n");
            if (i < commas.size()) {
                sb.append(commas.get(i).toSyntaxString());
            }
        }
        if (rightBrace != null) {
            sb.append(rightBrace.toSyntaxString());
        }
        sb.append(syntaxName + "\n");
        return sb.toString();
    }
}
