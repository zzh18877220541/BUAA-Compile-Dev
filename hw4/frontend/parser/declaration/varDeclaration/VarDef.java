package frontend.parser.declaration.varDeclaration;

import frontend.lexer.Token;
import frontend.parser.SyntaxOutput;
import frontend.parser.expression.ConstExp;

public class VarDef implements SyntaxOutput {
    private String syntaxName = "<VarDef>";
    private Token ident;
    private Token leftBracket;
    private ConstExp constExp;
    private Token rightBracket;
    private Token assign;
    private InitVal initVal;

    public VarDef(Token ident, Token leftBracket, ConstExp constExp, Token rightBracket, Token assign, InitVal initVal) {
        this.ident = ident;
        this.leftBracket = leftBracket;
        this.constExp = constExp;
        this.rightBracket = rightBracket;
        this.assign = assign;
        this.initVal = initVal;
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
        if (assign != null) {
            sb.append(assign.toSyntaxString());
            sb.append(initVal.toSyntaxString());
        }

        sb.append(syntaxName + "\n");
        return sb.toString();
    }
}
