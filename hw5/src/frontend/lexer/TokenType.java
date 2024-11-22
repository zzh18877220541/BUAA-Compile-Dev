package frontend.lexer;

import java.util.regex.Pattern;

public enum TokenType {
    // 保留字
    MAINTK(true, "main"),
    CONSTTK(true, "const"),
    INTTK(true, "int"),
    CHARTK(true, "char"),
    BREAKTK(true, "break"),
    CONTINUETK(true, "continue"),
    IFTK(true, "if"),
    ELSETK(true, "else"),
    FORTK(true, "for"),
    GETINTTK(true, "getint"),
    GETCHARTK(true, "getchar"),
    PRINTFTK(true, "printf"),
    RETURNTK(true, "return"),
    VOIDTK(true, "void"),

    // 标识符和常量
    IDENFR(false, "[a-zA-Z_][a-zA-Z0-9_]*"),
    INTCON(false, "[0-9]+"), // 要不要考虑范围
    CHRCON(false, "'([^'\\\\]|\\\\.)'"), // 能否支持 ‘\'' 这种情况？
    STRCON(false, "\"([^\"]*|\\.)\""),


    PLUS(false, "\\+"),
    MINU(false, "-"),
    MULT(false, "\\*"),
    DIV(false, "/"),
    LEQ(false, "<="),
    LSS(false, "<"),
    GEQ(false, ">="),
    GRE(false, ">"),
    EQL(false, "=="),
    NEQ(false, "!="),
    NOT(false, "!"),
    AND(false, "&&"),
    OR(false, "\\|\\|"),
    ASSIGN(false, "="),
    SEMICN(false, ";"),
    COMMA(false, ","),
    LPARENT(false, "\\("),
    RPARENT(false, "\\)"),
    MOD(false, "%"),
    LBRACK(false, "\\["),
    RBRACK(false, "\\]"),
    LBRACE(false, "\\{"),
    RBRACE(false, "\\}"),

    // 错误词法
    ERROR_AND(false, "&"),
    ERROR_OR(false, "\\|");

    private boolean isReserved;
    private String patternStr;
    private Pattern pattern;
    private boolean isError;

    TokenType(boolean isReserved, String patternStr) {
        this.isReserved = isReserved;
        this.patternStr = patternStr;
        if (this.isReserved) {
            this.pattern = Pattern.compile("^" + patternStr + "(?![a-zA-Z0-9_])");
        } else {
            this.pattern = Pattern.compile("^" + patternStr);
        }
        // 判断是否为错误词法
        if (patternStr.equals("&") || patternStr.equals("\\|")) {
            this.isError = true;
        } else {
            this.isError = false;
        }
    }


    public Pattern getPattern() {
        return pattern;
    }

    public boolean isError() {
        return isError;
    }

}
