import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Lab5 {

    public static void main(String[] args) {
        System.out.println("Trying to search for a not existing file:");
        printResults("not_existing_file.txt");
        System.out.println("\nWhen we have an empty file:");
        printResults("empty_file.txt");
        System.out.println("\nExample from the task:");
        printResults("input_file.txt");
        System.out.println("\nFirst chapter of Harry Potter and the Sorcerer's stone");
        printResults("ch1.txt");

    }

    private static void printResults(String fileName) {
        try {
            List<String> words = getWords(fileName);
            System.out.println("Often word is '" + commonestWord(words) + "'");
        } catch (Exception e) {
            System.out.println("EXCEPTION! " + e.getMessage());
        }
    }

    private static List<String> getWords(String fileName) throws IOException {
        if (fileName == null) {
            throw new IllegalArgumentException("File name is null");
        }

        List<String> words = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/" + fileName))) {
            String line;

            while ((line = br.readLine()) != null) {
                Collections.addAll(words, line.split(" "));
            }
        }

        return words;
    }

    private static String commonestWord(List<String> words) {
        String oftenWord = "File is empty";
        int oftenWordCount = 0;
        Map<String, Integer> wordsCountMap = new HashMap<>();

        for (String word : words) {
            Integer wordCount = wordsCountMap.get(word);

            if (wordCount == null) {
                wordCount = 0;
            }
            wordCount++;

            if (oftenWordCount < wordCount) {
                oftenWord = word;
                oftenWordCount = wordCount;
            }

            wordsCountMap.put(word, wordCount);
        }

        return oftenWord;
    }
}
