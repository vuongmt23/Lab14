import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileScan {
    public static void main(String[] args) {
        File file = null;

        if (args.length > 0) {
            // If a command-line argument is present, use it
            file = new File("src/" + args[0]);
            if (!file.exists()) {
                System.out.println("File not found: " + args[0]);
                return;
            }
        } else {
            // If no argument, launch JFileChooser
            JFileChooser fileChooser = new JFileChooser("src");
            int result = fileChooser.showOpenDialog(null);

            if (result == JFileChooser.APPROVE_OPTION) {
                file = fileChooser.getSelectedFile();
            } else {
                System.out.println("No file selected. Exiting.");
                return;
            }
        }

        System.out.println("Processing file: " + file.getName());
        try {
            Path filePath = file.toPath();
            List<String> lines = Files.readAllLines(filePath);

            int lineCount = lines.size();
            int wordCount = 0;
            int charCount = 0;

            for (String line : lines) {
                System.out.println(line);
                wordCount += line.split("\\s+").length;
                charCount += line.length();
            }

            System.out.println("\nSummary Report:");
            System.out.println("File Name: " + file.getName());
            System.out.println("Number of lines: " + lineCount);
            System.out.println("Number of words: " + wordCount);
            System.out.println("Number of characters: " + charCount);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}