import javax.swing.JFileChooser;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileScan {

    public static void main(String[] args) {

        File fileToScan;

        // Case 1: run with command-line argument
        if (args.length > 0) {
            fileToScan = new File(args[0]);

            if (!fileToScan.exists()) {
                System.out.println("File not found: " + args[0]);
                return;
            }
        }
        else {
            // Case 2: run with JFileChooser
            JFileChooser chooser = new JFileChooser("src");
            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                fileToScan = chooser.getSelectedFile();
            } else {
                System.out.println("No file selected.");
                return;
            }
        }

        scanFile(fileToScan);
    }

    // ------- SCAN METHOD FROM LAB 12 ---------
    public static void scanFile(File file) {
        try {
            Path path = file.toPath();
            List<String> lines = Files.readAllLines(path);

            int lineCount = 0;
            int wordCount = 0;
            int charCount = 0;

            for (String line : lines) {
                System.out.println(line);
                lineCount++;
                if (line.trim().length() > 0) {
                    wordCount += line.split("\\s+").length;
                }
                charCount += line.length();
            }

            System.out.println("\n--- File Summary ---");
            System.out.println("File name: " + file.getName());
            System.out.println("Number of lines: " + lineCount);
            System.out.println("Number of words: " + wordCount);
            System.out.println("Number of characters: " + charCount);

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}