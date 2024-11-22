package frontend.parser.expression;

import frontend.lexer.Token;
import frontend.lexer.TokenType;
import frontend.parser.SyntaxOutput;
import frontend.symbol.SymbolType;

import java.util.ArrayList;

public class AddExp implements SyntaxOutput {
    private String syntaxName = "<AddExp>";
    private ArrayList<MulExp> mulExps;
    private ArrayList<Token> ops;

    public AddExp(ArrayList<MulExp> mulExps, ArrayList<Token> ops) {
        this.mulExps = mulExps;
        this.ops = ops;
    }

    public SymbolType getType(int cur) {
        return mulExps.get(0).getType(cur);
    }

    public int calculateValue() {
        int result = mulExps.get(0).calculateValue();
        for (int i = 1; i < mulExps.size(); i++) {
            if (ops.get(i - 1).getType() == TokenType.PLUS) {
                result += mulExps.get(i).calculateValue();
            } else {
                result -= mulExps.get(i).calculateValue();
            }
        }
        return result;
    }

    @Override
    public String toSyntaxString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < mulExps.size(); i++) {
            sb.append(mulExps.get(i).toSyntaxString());
            if (mulExps.size() > 1 && i < mulExps.size() - 1) {
                sb.append(syntaxName + "\n");
            }
            if (i < ops.size()) {
                sb.append(ops.get(i).toSyntaxString());
            }
        }
        sb.append(syntaxName + "\n");
        return sb.toString();
    }
}
