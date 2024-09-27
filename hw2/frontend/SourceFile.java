package frontend;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class SourceFile {
    private String inputFile;
    private ArrayList<String> lines;
    private int lineNum;
    private int columnNum;
    private int length;

    public SourceFile(String input) {
        this.inputFile = input;
        this.columnNum = 0;
        this.lineNum = 0;
        try {
            readlines();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void readlines() throws FileNotFoundException {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile));
            lines = new ArrayList<>();
            String line;
            while (true) {
                line = bufferedReader.readLine();
                if (line != null) {
                    line += "\n";
                }
                if (line == null) {
                    break;
                }
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public char readCurrentChar() {
        int len = lines.get(lineNum).length();
        if (isEndOfInput()) {
            return '\0';
        } else if (columnNum >= len) {
            return '\n';
        } else {
            return lines.get(lineNum).charAt(columnNum);
        }

    }

    public String readLeftLines() {
        StringBuilder sb = new StringBuilder();
        int oriline = lineNum;
        int oricol = columnNum;
        while (!isEndOfInput()) {
            sb.append(readCurrentChar());
            skipNextchar();
        }
        lineNum = oriline;
        columnNum = oricol;
        return sb.toString();
    }

    public boolean EndOfLine() {
        if (isEndOfInput()) {
            return true;
        } else {
            return readCurrentChar() == '\n';
        }
    }

    public void skipNextchar() {
        if (isEndOfInput()) {
            return;
        }
        if (columnNum + 1 >= lines.get(lineNum).length()) {
            columnNum = 0;
            lineNum++;
        } else {
            columnNum++;
        }
    }

    public String readSubStr(int len) {
        // 检查长度是否超出范围
        if (isEndOfInput()) {
            return "";
        } else if (columnNum + len >= lines.get(lineNum).length()) {
            return lines.get(lineNum).substring(columnNum);
        } else {
            return lines.get(lineNum).substring(columnNum, columnNum + len);
        }
    }

    public void skipNextSubStr(int len) {
        int steps = len;
        while (!this.isEndOfInput() && steps > 0) {
            if (columnNum + steps >= lines.get(lineNum).length()) {
                steps -= lines.get(lineNum).length() - columnNum;
                columnNum = 0;
                lineNum++;
            } else {
                columnNum += steps;
                steps = 0;
            }
        }
    }

    public boolean isEndOfInput() {
        if (lineNum >= lines.size()) {
            return true;
        } else if (lineNum == lines.size() - 1 && columnNum >= lines.get(lineNum).length()) {
            return true;
        } else {
            return false;
        }
    }

    public int getLineNum() {
        return lineNum + 1;
    }

}