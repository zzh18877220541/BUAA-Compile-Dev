package frontend.parser.block;

import frontend.lexer.Token;
import frontend.parser.SyntaxOutput;

import java.util.ArrayList;

public class LAndExp implements SyntaxOutput  {
    private String syntaxName = "<LAndExp>";
    private ArrayList<EqExp> eqExps;
    private ArrayList<Token> ands;

    public LAndExp(ArrayList<EqExp> eqExps, ArrayList<Token> ands) {
        this.eqExps = eqExps;
        this.ands = ands;
    }

    @Override
    public String toSyntaxString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < eqExps.size(); i++) {
            sb.append(eqExps.get(i).toSyntaxString());
            if (eqExps.size() > 1 && i < eqExps.size() - 1) {
                sb.append(syntaxName + "\n");
            }
            if (i < ands.size()) {
                sb.append(ands.get(i).toSyntaxString());
            }
        }
        sb.append(syntaxName + "\n");
        return sb.toString();
    }
}
