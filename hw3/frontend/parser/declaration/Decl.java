package frontend.parser.declaration;

public class Decl implements DeclItem {
    private String syntaxName = "<Decl>";
    private DeclItem declItem;

    public Decl(DeclItem declItem) {
        this.declItem = declItem;
    }

    @Override
    public String toSyntaxString() {
        return this.declItem.toSyntaxString();
    }
}
