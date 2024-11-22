package frontend.parser.expression;

import frontend.lexer.Token;
import frontend.lexer.TokenType;
import frontend.parser.SyntaxOutput;
import frontend.symbol.SymbolType;

import java.util.ArrayList;

public class MulExp implements SyntaxOutput {
    private String syntaxName = "<MulExp>";
    private ArrayList<UnaryExp> unaryExps;
    private ArrayList<Token> ops;

    public MulExp(ArrayList<UnaryExp> unaryExps, ArrayList<Token> ops) {
        this.unaryExps = unaryExps;
        this.ops = ops;
    }

    public SymbolType getType(int cur) {
        return unaryExps.get(0).getType(cur);
    }

    public int calculateValue() {
        int result = unaryExps.get(0).calculateValue();
        for (int i = 1; i < unaryExps.size(); i++) {
            if (ops.get(i - 1).getType() == TokenType.MULT) {
                result *= unaryExps.get(i).calculateValue();
            } else {
                result /= unaryExps.get(i).calculateValue();
            }
        }
        return result;
    }

    @Override
    public String toSyntaxString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < unaryExps.size(); i++) {
            sb.append(unaryExps.get(i).toSyntaxString());
            if (unaryExps.size() > 1 && i < unaryExps.size() - 1) {
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
