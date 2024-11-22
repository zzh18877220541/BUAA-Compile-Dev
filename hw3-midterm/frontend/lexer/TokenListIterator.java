package frontend.lexer;

import java.util.ListIterator;

public class TokenListIterator {
    private TokenList tokenList;
    private ListIterator<Token> iterator;
    private int currentIndex = 0;

    public TokenListIterator(TokenList tokenList) {
        this.tokenList = tokenList;
        iterator = tokenList.getTokens().listIterator();
    }

    public boolean hasNext() {
        return iterator.hasNext();
    }

    public Token readNextToken() {
        currentIndex++;
        return iterator.next();
    }

    public void unReadTokens(int n) {
        currentIndex -= n;
        while (n > 0) {
            if (iterator.hasPrevious()) {
                iterator.previous();
            } else {
                break;
            }
            n--;
        }
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

}
