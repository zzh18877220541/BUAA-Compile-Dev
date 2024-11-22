import frontend.SyntaxError.SyntaxErrorHandler;
import frontend.lexer.Lexer;
import frontend.lexer.SourceFile;
import frontend.lexer.Token;
import frontend.lexer.TokenList;
import frontend.parser.CompUnit;
import frontend.parser.CompUnitParser;

public class Compiler {
    public static void main(String[] args) {
        String inputFile = "testfile.txt";
        String outputFile = "parser.txt";
        String errorFile = "error.txt";

        // 清空 error.txt
        SyntaxErrorHandler.getInstance().ClearError_txt();
        // 清空 lexer.txt
        SyntaxErrorHandler.getInstance().ClearLexer_txt();
        // 词法分析
        SourceFile sourceFile = new SourceFile(inputFile);
        Lexer lexer = new Lexer(sourceFile);
        TokenList tokenList = new TokenList();
        lexer.TokenToList(tokenList);
        // 语法分析
        CompUnitParser compUnitParser = new CompUnitParser(tokenList);
        CompUnit compUnit = compUnitParser.parseCompUnit();
        compUnit.toSyntaxString();
        // 错误输出
        SyntaxErrorHandler.getInstance().SortError_txt();
    }
}
