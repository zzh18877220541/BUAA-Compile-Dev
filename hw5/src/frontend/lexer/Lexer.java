package frontend.lexer;

import frontend.SyntaxError.SyntaxErrorHandler;

import frontend.SyntaxError.ErrorType;

import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer {
    private SourceFile source;
    private final String lineComment = "//";
    private final String blockCommentStart = "/*";
    private final String blockCommentEnd = "*/";

    public Lexer(SourceFile source) {
        this.source = source;
    }

    public boolean isEndOfInput() {
        return source.isEndOfInput();
    }

    public void skipWhiteSpace() {
        while (!isEndOfInput() && (Character.isWhitespace(source.readCurrentChar()) || source.EndOfLine())) {
            source.skipNextchar();
        }
    }

    public boolean skipComment() {
        if (lineComment.equals(source.readSubStr(2))) {
            while (!isEndOfInput() && !source.EndOfLine()) {
                source.skipNextchar();
            }
            source.skipNextchar(); // 跳过换行符
            return true;
        } else if (blockCommentStart.equals(source.readSubStr(2))) {
            source.skipNextSubStr(2);
            while (!isEndOfInput() && !blockCommentEnd.equals(source.readSubStr(2))) {
                source.skipNextchar();
            }
            if (blockCommentEnd.equals(source.readSubStr(2))) {
                source.skipNextSubStr(2);
                return true;
            }
        }
        return false;
    }

    public Token nextToken() {
        skipWhiteSpace();
        while (skipComment()) {
            skipWhiteSpace();
        }
        int currentLine = source.getLineNum();
        String currentWord = source.readLeftLines();
        for (TokenType tokentype : TokenType.values()) {
            Pattern pattern = tokentype.getPattern();
            Matcher matcher = pattern.matcher(currentWord);

            if (matcher.find() && matcher.start() == 0) {
                String value = matcher.group();
                source.skipNextSubStr(value.length());
                return new Token(tokentype, value, currentLine);
            }
        }
        return null;
    }

    public void TokenToList(TokenList tokenList) {
        while(!isEndOfInput()) {
            Token token = nextToken();
            if (token == null) {
                continue;
            }
            if (token.getType().isError()) {
                SyntaxErrorHandler.getInstance().WriteSyntaxError(ErrorType.a, token.getLine());
                if (token.getType() == TokenType.ERROR_AND) {
                    token = new Token(TokenType.AND, "&&", token.getLine());
                } else {
                    token = new Token(TokenType.OR, "||", token.getLine());
                }
            }
            tokenList.addToken(token);
            // debug
            FileWriter fileWriter = null;
            try {
                fileWriter = new FileWriter("lexer.txt", true);
                fileWriter.write(token.toSyntaxString());
                fileWriter.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
