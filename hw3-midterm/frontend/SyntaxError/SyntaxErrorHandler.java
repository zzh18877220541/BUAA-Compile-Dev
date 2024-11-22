package frontend.SyntaxError;

import frontend.SyntaxError.ErrorType;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SyntaxErrorHandler {
    private static SyntaxErrorHandler instance;
    private String ErrorFileName = "error.txt";

    public static SyntaxErrorHandler getInstance() {
        if (instance == null) {
            instance = new SyntaxErrorHandler();
        }
        return instance;
    }

    public void ClearError_txt() {
        try {
            FileWriter fileWriter = new FileWriter(ErrorFileName);
            fileWriter.write("");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ClearLexer_txt() {
        try {
            FileWriter fileWriter = new FileWriter("lexer.txt");
            fileWriter.write("");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void WriteSyntaxError(ErrorType errorType, int line) {
        try {
            FileWriter fileWriter = new FileWriter(ErrorFileName, true);
            fileWriter.write(line + " " + errorType + "\n");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void SortError_txt() {
        ArrayList<String> lines = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("error.txt"));
            String line = reader.readLine();
            while (line != null) {
                lines.add(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Collections.sort(lines, new Comparator<String>() {
            public int compare(String o1, String o2) {
                int lineNum1 = Integer.parseInt(o1.split(" ")[0]);
                int lineNum2 = Integer.parseInt(o2.split(" ")[0]);
                return Integer.compare(lineNum1, lineNum2);
            }
        });

        try {
            FileWriter writer = new FileWriter("error.txt", false);
            for (String str : lines) {
                writer.write(str + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
