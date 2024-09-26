public class Lexer {
    private SourceFile source;

    public Lexer(SourceFile source) {
        this.source = source;
    }

    public boolean isEndOfInput() {
        return source.isEndOfInput();
    }

    public void skipWhiteSpace() {
        while (!isEndOfInput() && Character.isWhitespace(source.readNextchar())) {
            ;
        }
    }

    public
}
