package frontend.parser.declaration.varDeclaration;

import frontend.lexer.Token;
import frontend.parser.declaration.DeclItem;
import frontend.parser.declaration.constDeclaration.ConstDef;

import java.util.ArrayList;

public class VarDecl implements DeclItem {
    private String SyntaxName = "<VarDecl>";
    private Token btype;
    private ArrayList<VarDef> varDefs;
    private ArrayList<Token> commas;
    private Token semicolon;

    public VarDecl(Token btype, ArrayList<VarDef> varDefs, ArrayList<Token> commas, Token semicolon) {
        this.btype = btype;
        this.varDefs = varDefs;
        this.commas = commas;
        this.semicolon = semicolon;
    }

    @Override
    public String toSyntaxString() {
        StringBuilder sb = new StringBuilder();
        sb.append(btype.toSyntaxString());
        for (int i = 0; i < varDefs.size(); i++) {
            sb.append(varDefs.get(i).toSyntaxString());
            if (i < commas.size()) {
                sb.append(commas.get(i).toSyntaxString());
            }
        }
        sb.append(semicolon.toSyntaxString());
        sb.append(SyntaxName + "\n");
        return sb.toString();
    }
}
