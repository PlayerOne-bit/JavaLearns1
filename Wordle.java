import java.util.Random;
import java.util.Scanner;

public class Wordle {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Random rand = new Random();
		String name[] = {"point","chief","class","nigga","table","paint","pride","fried","cooks","nigger","pneumonoultramicroscopicsilicovolcanoconiosis"};
		int x = rand.nextInt(name.length);
		
		String read=name[x];
		read.toUpperCase();
		int attempt=10;
		int numAttempt=1;
		
		String usedChar="";
		
		
		System.out.println("[WORDLE]\nInstruction:\nGuess the word the clues are distributed by symbols like\n\"()\" means incorrect letter;\n\"[]\" means incorrect place but correct letter;\nif there are no symbols it's in the right position\nand you guessed a correct letter!\nMaximum Attempts is "+attempt);
		
		while(true) {
			System.out.print("--------------------------------\nInput: ");
			String input = scan.next();
			input.toUpperCase();
			String word="";
			if (input.length()==read.length()) {
				for(int i=0; i<input.length();i++) {
					boolean find=false;
					if(input.charAt(i)==read.charAt(i)) {
						word+=read.charAt(i);
						find=true;
					}else {
						for(int j=0;j<read.length();j++) {
							if(read.charAt(j)==input.charAt(i)) {
								if(!word.contains(String.valueOf(input.charAt(i)))) {
									word+="["+read.charAt(j)+"]";
								}
								find=true;
							}
							
						}
						
					}if(!find) {
						word+="<"+input.charAt(i)+">";
						if(usedChar.contains(String.valueOf(input.charAt(i)))){
							continue;
						}else {
							usedChar+=input.charAt(i);
							
						}
						find=false;
					}
				}
			}else {
				System.out.printf("Try to input only %d characters...",read.length());
			}
			
			System.out.println(word.toUpperCase()+"\t\tUsed Letters: "+usedChar.toUpperCase());
			
			if(word.equalsIgnoreCase(read)) {
				System.out.println("Nice One! You guessed the correct word it's "+read.toUpperCase()+"\nAttempts: "+numAttempt);
				break;
			} else {
				++numAttempt;
				--attempt;
				System.out.println("\nToo Bad!\nRemaining Attempts: "+attempt);
				if(attempt<=0) {
					System.out.println("Out of Attempts You Lost!\nThe correct answer is "+read.toUpperCase());
					break;
				}
			}	
		}
		scan.close();
	}
}