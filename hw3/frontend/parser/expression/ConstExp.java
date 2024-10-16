package frontend.parser.expression;

import frontend.parser.SyntaxOutput;

public class ConstExp implements SyntaxOutput {
    private String syntaxName = "<ConstExp>";
    private AddExp addExp;

    public ConstExp(AddExp addExp) {
        this.addExp = addExp;
    }

    @Override
    public String toSyntaxString() {
        StringBuilder sb = new StringBuilder();
        sb.append(addExp.toSyntaxString());
        sb.append(syntaxName + "\n");
        return sb.toString();
    }
}
