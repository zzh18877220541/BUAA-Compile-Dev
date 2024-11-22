package frontend.parser.funcparas;

import frontend.lexer.Token;
import frontend.parser.SyntaxOutput;
import frontend.parser.expression.AddExp;

import java.util.ArrayList;

public class FuncRParams implements SyntaxOutput {
    private String syntaxName = "<FuncRParams>";
    private ArrayList<AddExp> exps;
    private ArrayList<Token> commas;

    public FuncRParams(ArrayList<AddExp> exps, ArrayList<Token> commas) {
        this.exps = exps;
        this.commas = commas;
    }

    public int size() {
        return exps.size();
    }

    public AddExp getPara(int i) {
        return exps.get(i);
    }

    @Override
    public String toSyntaxString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < exps.size(); i++) {
            sb.append(exps.get(i).toSyntaxString());
            sb.append("<Exp>\n");
            if (i < commas.size()) {
                sb.append(commas.get(i).toSyntaxString());
            }
        }
        sb.append(syntaxName + "\n");
        return sb.toString();
    }
}
