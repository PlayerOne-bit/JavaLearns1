import java.util.Scanner;

public class WordReaderConverter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a string:");
        String input = scanner.nextLine();
        
        String[] words = input.trim().split("\\s+");
        int wordCount = words.length;
        
        System.out.println("The number of words in the string is: " + wordCount);
        scanner.close();
    }
}
