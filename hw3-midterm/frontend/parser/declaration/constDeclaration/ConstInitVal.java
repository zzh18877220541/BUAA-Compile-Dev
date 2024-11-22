package frontend.parser.declaration.constDeclaration;

import frontend.lexer.Token;
import frontend.parser.SyntaxOutput;
import frontend.parser.expression.ConstExp;

import java.util.ArrayList;

public class ConstInitVal implements SyntaxOutput {
    private String syntaxName = "<ConstInitVal>";
    private ArrayList<ConstExp> constExps;
    private Token leftBrace;
    private Token rightBrace;
    private ArrayList<Token> commas;
    private Token StringConst;

    public ConstInitVal(ArrayList<ConstExp> constExps, Token leftBrace, Token rightBrace, ArrayList<Token> commas, Token StringConst) {
        this.constExps = constExps;
        this.leftBrace = leftBrace;
        this.rightBrace = rightBrace;
        this.commas = commas;
        this.StringConst = StringConst;
    }

    @Override
    public String toSyntaxString() {
        StringBuilder sb = new StringBuilder();
        if (leftBrace != null) {
            sb.append(leftBrace.toSyntaxString());
        }
        for (int i = 0; i < constExps.size(); i++) {
            sb.append(constExps.get(i).toSyntaxString());
            if (i < commas.size()) {
                sb.append(commas.get(i).toSyntaxString());
            }
        }
        if (StringConst != null) {
            sb.append(StringConst.toSyntaxString());
        }
        if (rightBrace != null) {
            sb.append(rightBrace.toSyntaxString());
        }
        sb.append(syntaxName + "\n");
        return sb.toString();
    }
}
