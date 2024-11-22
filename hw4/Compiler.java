import frontend.SyntaxError.SyntaxErrorHandler;
import frontend.lexer.Lexer;
import frontend.lexer.SourceFile;
import frontend.lexer.TokenList;
import frontend.parser.CompUnit;
import frontend.parser.CompUnitParser;
import frontend.symbol.SymbolTableManager;
// 14 15 16 22 23 24 26
public class Compiler {
    public static void main(String[] args) {
        String inputFile = "testfile.txt";
        String outputFile = "parser.txt";
        String errorFile = "error.txt";

        // 清空 error.txt
        SyntaxErrorHandler.getInstance().ClearError_txt();
        // 词法分析
        SourceFile sourceFile = new SourceFile(inputFile);
        Lexer lexer = new Lexer(sourceFile);
        TokenList tokenList = new TokenList();
        lexer.TokenToList(tokenList);
        // 语法分析
        CompUnitParser compUnitParser = new CompUnitParser(tokenList);
        CompUnit compUnit = compUnitParser.parseCompUnit();
        compUnit.toSyntaxString();
        // 语义分析
        SymbolTableManager sym = SymbolTableManager.getInstance();
        sym.printSymbolTable();
        // 错误输出
        SyntaxErrorHandler.getInstance().SortError_txt();
    }
}
