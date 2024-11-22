package frontend.parser.block;

import frontend.lexer.Token;
import frontend.parser.SyntaxOutput;

import java.util.ArrayList;

public class LOrExp implements SyntaxOutput {
    private String syntaxName = "<LOrExp>";
    private ArrayList<LAndExp> lAndExps;
    private ArrayList<Token> ors;

    public LOrExp(ArrayList<LAndExp> lAndExps, ArrayList<Token> ors) {
        this.lAndExps = lAndExps;
        this.ors = ors;
    }

    @Override
    public String toSyntaxString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < lAndExps.size(); i++) {
            sb.append(lAndExps.get(i).toSyntaxString());
            if (lAndExps.size() > 1 && i < lAndExps.size() - 1) {
                sb.append(syntaxName + "\n");
            }
            if (i < ors.size()) {
                sb.append(ors.get(i).toSyntaxString());
            }
        }
        sb.append(syntaxName + "\n");
        sb.append("<Cond>\n");
        return sb.toString();
    }
}
