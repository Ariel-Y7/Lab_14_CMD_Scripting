import javax.swing.JFileChooser;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        JFileChooser chooser = new JFileChooser("src"); // open in src folder
        int result = chooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            Path file = chooser.getSelectedFile().toPath();
            System.out.println("File chosen: " + file);

            int lineCount = 0;
            int wordCount = 0;
            int charCount = 0;

            try {
                List<String> lines = Files.readAllLines(file); // read all lines

                for (String line : lines) {
                    System.out.println(line); // echo line
                    lineCount++;
                    wordCount += line.split("\\s+").length; // count words
                    charCount += line.length(); // count characters
                }

                System.out.println("\n--- File Summary ---");
                System.out.println("File Name: " + file.getFileName());
                System.out.println("Number of lines: " + lineCount);
                System.out.println("Number of words: " + wordCount);
                System.out.println("Number of characters: " + charCount);

            } catch (IOException e) {
                System.out.println("Error reading the file: " + e.getMessage());
            }
        } else {
            System.out.println("No file selected.");
        }
    }
}