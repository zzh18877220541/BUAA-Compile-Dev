package frontend.parser.funcparas;

import frontend.lexer.Token;
import frontend.parser.SyntaxOutput;

import java.util.ArrayList;

public class FuncFParams implements SyntaxOutput {
    private String syntaxName = "<FuncFParams>";
    private ArrayList<FuncFParam> funcFParams;
    private ArrayList<Token> commas;

    public FuncFParams(ArrayList<FuncFParam> funcFParams, ArrayList<Token> commas) {
        this.funcFParams = funcFParams;
        this.commas = commas;
    }

    @Override
    public String toSyntaxString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < funcFParams.size(); i++) {
            sb.append(funcFParams.get(i).toSyntaxString());
            if (i < commas.size()) {
                sb.append(commas.get(i).toSyntaxString());
            }
        }
        sb.append(syntaxName + "\n");
        return sb.toString();
    }
}
