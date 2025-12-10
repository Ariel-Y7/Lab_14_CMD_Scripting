import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class DataSaver {
    private static Scanner sc = new Scanner(System.in);

    public static void DataSaver (String[] args) {
        ArrayList<String> records = new ArrayList<>();
        int idCounter = 1;

        boolean moreData = true;

        while (moreData) {
            String firstName = SafeInput.getRegExString(sc, "Enter first name:", ".+");
            String lastName = SafeInput.getRegExString(sc, "Enter last name:", ".+");
            String idNumber = String.format("%06d", idCounter++);
            String email = SafeInput.getRegExString(sc, "Enter email:", ".+@.+\\..+");
            int yearOfBirth = SafeInput.getRangedInt(sc, "Enter year of birth (1900-2025):", 1900, 2025);

            String csvRecord = firstName + "," + lastName + "," + idNumber + "," + email + "," + yearOfBirth;
            records.add(csvRecord);

            moreData = SafeInput.getYNConfirm(sc, "Add another record?");
        }

        String fileName = SafeInput.getRegExString(sc, "Enter filename to save (add .csv):", ".+\\.csv");
        Path filePath = Path.of("src/" + fileName);

        try {
            Files.write(filePath, records); // write all lines to file
            System.out.println("Data saved to " + filePath);
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }
}