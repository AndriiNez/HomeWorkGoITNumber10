
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SortNumbers {
    private static final String ABSOLUTE_PARTF = "./textfolder/file1.txt";

    public static void printValidPhoneNumbers() {
        try (BufferedReader reader = new BufferedReader(new FileReader(ABSOLUTE_PARTF))) {
            String line = reader.readLine();
            Pattern pattern = Pattern.compile("\\(\\d{3}\\) \\d{3}-\\d{4}|\\d{3}-\\d{3}-\\d{4}");

            while (line != null) {
                Matcher matcher = pattern.matcher(line);
                if (matcher.matches()) {
                    System.out.println(line);
                }

                line = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

    }
}


