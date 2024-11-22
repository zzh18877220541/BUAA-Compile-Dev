package frontend.parser.declaration.constDeclaration;

import frontend.lexer.Token;

import frontend.parser.declaration.DeclItem;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ConstDecl implements DeclItem {
    private String SyntaxName = "<ConstDecl>";
    private Token constToken;
    private Token btype;
    private ArrayList<ConstDef> constDefs;
    private ArrayList<Token> commas;
    private Token semicolon;

    public ConstDecl(Token constToken, Token btype, ArrayList<ConstDef> constDefs, ArrayList<Token> commas, Token semicolon) {
        this.constToken = constToken;
        this.btype = btype;
        this.constDefs = constDefs;
        this.commas = commas;
        this.semicolon = semicolon;
    }

    public ArrayList<ConstDef> getConstDefs() {
        return constDefs;
    }

    public Token getBtype() {
        return btype;
    }

    @Override
    public String toSyntaxString() {
        StringBuilder sb = new StringBuilder();
        sb.append(constToken.toSyntaxString());
        sb.append(btype.toSyntaxString());
        for (int i = 0; i < constDefs.size(); i++) {
            sb.append(constDefs.get(i).toSyntaxString());
            if (i < commas.size()) {
                sb.append(commas.get(i).toSyntaxString());
            }
        }
        sb.append(semicolon.toSyntaxString());
        sb.append(SyntaxName + "\n");
        return sb.toString();
    }
}
