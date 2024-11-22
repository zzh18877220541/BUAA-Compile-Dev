package frontend.lexer;

import java.util.ArrayList;

public class TokenList {
    private ArrayList<Token> tokens;

    public TokenList() {
        tokens = new ArrayList<Token>();
    }

    public void addToken(Token token) {
        tokens.add(token);
    }

    public ArrayList<Token> getTokens() {
        return tokens;
    }
}
