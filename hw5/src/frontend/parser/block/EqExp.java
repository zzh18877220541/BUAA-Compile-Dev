package frontend.parser.block;

import frontend.lexer.Token;
import frontend.parser.SyntaxOutput;

import java.util.ArrayList;

public class EqExp implements SyntaxOutput {
    private String syntaxName = "<EqExp>";
    private ArrayList<RelExp> relExps;
    private ArrayList<Token> eqOps;

    public EqExp(ArrayList<RelExp> relExps, ArrayList<Token> eqOps) {
        this.relExps = relExps;
        this.eqOps = eqOps;
    }

    @Override
    public String toSyntaxString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < relExps.size(); i++) {
            sb.append(relExps.get(i).toSyntaxString());
            if (relExps.size() > 1 && i < relExps.size() - 1) {
                sb.append(syntaxName + "\n");
            }
            if (i < eqOps.size()) {
                sb.append(eqOps.get(i).toSyntaxString());
            }
        }
        sb.append(syntaxName + "\n");
        return sb.toString();
    }
}
