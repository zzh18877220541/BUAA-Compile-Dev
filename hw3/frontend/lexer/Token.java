package frontend.lexer;

import frontend.parser.SyntaxOutput;

public class Token implements SyntaxOutput {
    private TokenType type;
    private String value;
    private int line;

    public Token(TokenType type, String value, int line) {
        this.type = type;
        this.value = value;
        this.line = line;
    }

    public TokenType getType() {
        return type;
    }

    public int getLine() {
        return line;
    }

    public String toString() {
        return type + " " + value;
    }

    @Override
    public String toSyntaxString() {
        return this.toString() + "\n";
    }
}
