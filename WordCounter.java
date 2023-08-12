import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class WordCounter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a text or provide a file path to count words: ");
        String userInput = scanner.nextLine();
        
        String text = "";
        try {
            text = readTextFromFile(userInput);
        } catch (IOException e) {
            text = userInput;
        }
        
        text = text.toLowerCase().replaceAll("[\\p{Punct}]", "");
        String[] words = text.split("\\s+");
        
        int wordCount = words.length;
        System.out.println("Total words: " + wordCount);
        
        // Optional: Count frequency of each word and display
        Map<String, Integer> wordFrequency = countWordFrequency(words);
        System.out.println("Word frequency:");
        for (Map.Entry<String, Integer> entry : wordFrequency.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        
        scanner.close();
    }
    
    public static String readTextFromFile(String filePath) throws IOException {
        StringBuilder text = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                text.append(line).append("\n");
            }
        }
        return text.toString();
    }
    
    public static Map<String, Integer> countWordFrequency(String[] words) {
        Map<String, Integer> wordFrequency = new HashMap<>();
        for (String word : words) {
            wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
        }
        return wordFrequency;
    }
}
