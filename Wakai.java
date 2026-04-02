import java.util.HashMap;
import java.util.Scanner;
import java.util.Random;
public class Wakai {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Random random = new Random();
		String word=scanner.next();
		System.out.printf("%d Points",scrabblePoints(word, random));
		scanner.close();
	}
		    public static int scrabblePoints(String word, Random random) {
		        HashMap<Character, Integer> points = new HashMap<>();
		        
		        int x=random.nextInt(20)+1;
		        points.put('A', x);
		        points.put('B', x);
		        points.put('C', x);
		        points.put('D', x);
		        points.put('E', x);
		        points.put('F', x);
		        points.put('G', x);
		        points.put('H', x);
		        points.put('I', x);
		        points.put('J', x);
		        points.put('K', x);
		        points.put('L', x);
		        points.put('M', x);
		        points.put('N', x);
		        points.put('O', x);
		        points.put('P', x);
		        points.put('Q', x);
		        points.put('R', x);
		        points.put('S', x);
		        points.put('T', x);
		        points.put('U', x);
		        points.put('V', x);
		        points.put('W', x);
		        points.put('X', x);
		        points.put('Y', x);
		        points.put('Z', x);
		        points.put(' ', x);

		        int totalPoints = 0;
		        for (char letter : word.toUpperCase().toCharArray()) { 
		            totalPoints += points.getOrDefault(letter, 0);
		        }
		        return totalPoints;
		        
		    }
}

