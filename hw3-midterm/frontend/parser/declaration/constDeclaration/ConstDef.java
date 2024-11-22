package frontend.parser.declaration.constDeclaration;

import frontend.lexer.Token;
import frontend.parser.SyntaxOutput;
import frontend.parser.expression.ConstExp;

public class ConstDef implements SyntaxOutput {
    private String syntaxName = "<ConstDef>";
    private Token ident;
    private Token leftBracket;
    private ConstExp constExp;
    private Token rightBracket;
    private Token assign;
    private ConstInitVal constInitVal;

    public ConstDef(Token ident, Token leftBracket, ConstExp constExp, Token rightBracket, Token assign, ConstInitVal constInitVal) {
        this.ident = ident;
        this.leftBracket = leftBracket;
        this.constExp = constExp;
        this.rightBracket = rightBracket;
        this.assign = assign;
        this.constInitVal = constInitVal;
    }


    @Override
    public String toSyntaxString() {
        StringBuilder sb = new StringBuilder();
        sb.append(ident.toSyntaxString());
        if (leftBracket != null) {
            sb.append(leftBracket.toSyntaxString());
        }
        if (constExp != null) {
            sb.append(constExp.toSyntaxString());
        }
        if (rightBracket != null) {
            sb.append(rightBracket.toSyntaxString());
        }
        sb.append(assign.toSyntaxString());
        sb.append(constInitVal.toSyntaxString());

        sb.append(syntaxName + "\n");
        return sb.toString();
    }
}
