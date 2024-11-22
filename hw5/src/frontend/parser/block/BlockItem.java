package frontend.parser.block;

import frontend.parser.SyntaxOutput;
import frontend.parser.declaration.Decl;

public class BlockItem implements SyntaxOutput {
    private String syntaxName = "<BlockItem>";
    private Decl decl;
    private Stmt stmt;

    public BlockItem(Decl decl, Stmt stmt) {
        this.decl = decl;
        this.stmt = stmt;
    }

    public boolean isReturn() {
        if (stmt == null) {
            return false;
        }
        return stmt.isReturn();
    }

    @Override
    public String toSyntaxString() {
        StringBuilder sb = new StringBuilder();
        if (decl != null) {
            sb.append(decl.toSyntaxString());
        }
        if (stmt != null) {
            sb.append(stmt.toSyntaxString());
        }
        return sb.toString();
    }

}
