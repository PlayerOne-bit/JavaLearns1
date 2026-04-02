import java.util.Random;
import java.util.Scanner;

public class GuessTheNumber {
		
	
  public static void main(String[] args) {
	Random random = new Random();
	Scanner scanner = new Scanner(System.in);
	System.out.println("[Instruction]\nGuess a number\nfrom 0 to 100:");
	int attempt=0;
	int rng = random.nextInt(100)+1;
	while(true) {
		System.out.println("Your Guess:");
		int guess=scanner.nextInt();
		
		if(guess>=0 && guess<=100) {
		if(guess==rng) {
			attempt++;
			System.out.println("You guessed the correct Answer!\nAttempt: "+attempt);
			break;
		} if (guess>rng) {
			attempt++;
			System.out.println("Too High! Try Again!");
		} if (guess<rng) {
			attempt++;
			System.out.println("Too Low! Try Again!");
		}
		}else {
			System.out.println("Wrong Input! Number must be 0 to 100!");
		}
		
		
	}
	scanner.close();
  }
}
		  
