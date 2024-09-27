import frontend.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

public class Compiler {
    public static void main(String[] args) {
        String inputFile = "testfile.txt";
        String outputFile = "lexer.txt";
        String errorFile = "error.txt";

        SourceFile sourceFile = new SourceFile(inputFile);
        Lexer lexer = new Lexer(sourceFile);
        lexer.TokenLize(outputFile, errorFile);

    }
}
