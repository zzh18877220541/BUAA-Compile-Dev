package frontend.parser.block;

import frontend.lexer.Token;
import frontend.parser.SyntaxOutput;
import frontend.parser.expression.AddExp;

import java.util.ArrayList;

public class RelExp implements SyntaxOutput {
    private String syntaxName = "<RelExp>";
    private ArrayList<AddExp> addExps;
    private ArrayList<Token> relOps;

    public RelExp(ArrayList<AddExp> addExps, ArrayList<Token> relOps) {
        this.addExps = addExps;
        this.relOps = relOps;
    }

    @Override
    public String toSyntaxString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < addExps.size(); i++) {
            sb.append(addExps.get(i).toSyntaxString());
            if (addExps.size() > 1 && i < addExps.size() - 1) {
                sb.append(syntaxName + "\n");
            }
            if (i < relOps.size()) {
                sb.append(relOps.get(i).toSyntaxString());
            }
        }
        sb.append(syntaxName + "\n");
        return sb.toString();
    }
}
