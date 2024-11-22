//package frontend;
//
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class FileCompare {
//    public static void main(String[] args) {
//        String file1 = "std.txt";
//        String file2 = "parser.txt";
//
//        try {
//            List<Integer> differingLines = compareFiles(file1, file2);
//            if (differingLines.isEmpty()) {
//                System.out.println("true");
//            } else {
//                for (int line : differingLines) {
//                    System.out.println("Difference at line: " + line);
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static List<Integer> compareFiles(String file1, String file2) throws IOException {
//        List<Integer> differingLines = new ArrayList<>();
//        try (BufferedReader reader1 = new BufferedReader(new FileReader(file1));
//             BufferedReader reader2 = new BufferedReader(new FileReader(file2))) {
//
//            String line1, line2;
//            int lineNumber = 1;
//
//            while ((line1 = reader1.readLine()) != null & (line2 = reader2.readLine()) != null) {
//                if (!line1.equals(line2)) {
//                    differingLines.add(lineNumber);
//                }
//                lineNumber++;
//            }
//
//            // Check if one file has more lines than the other
//            while (reader1.readLine() != null) {
//                differingLines.add(lineNumber++);
//            }
//            while (reader2.readLine() != null) {
//                differingLines.add(lineNumber++);
//            }
//        }
//        return differingLines;
//    }
//}