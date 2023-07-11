import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.*;

public class CountWords {
    private static final String ABSOLUTE_PARTF = "./textfolder/words.txt";

    public static Map<String, Integer> сountWord() {
        Map<String, Integer> wordArray = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ABSOLUTE_PARTF))) {
            String line = reader.readLine();
            while (line != null) {
                String[] words = line.split("\\s+");

                for (String word : words) {
                    if (!word.isEmpty()) {
                        wordArray.put(word, wordArray.getOrDefault(word, 0) + 1);
                    }
                }
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return wordArray;
    }

    public static void consolAdd() {
        Map<String, Integer> wordArray = сountWord();
        List<Map.Entry<String, Integer>> sortedList = new ArrayList<>(wordArray.entrySet());
        Collections.sort(sortedList, (a, b) -> b.getValue().compareTo(a.getValue()));
        for (Map.Entry<String, Integer> entry : sortedList) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}
