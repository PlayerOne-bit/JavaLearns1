import java.util.Random;
import java.util.Scanner;

public class RockPaperScissor {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Random random = new Random();
		
		String PChoice;
		int Pscore=0, Cscore=0;
		
		System.out.println("[ROCK PAPER SCISSOR GAME]\nInstruction:\ntype  the words.");
		
		while(true) {
			
			
			System.out.println("Pick a choice (rock, paper, scissor):");
		int x = random.nextInt(3);
		
		String[] ComChoice= {"Rock","Paper","Scissor"};
		
		PChoice = scanner.next();
		
		if("rock".equalsIgnoreCase(PChoice)) {
			if(x==0) {
				System.out.println("It's a tie!\nI picked: "+ComChoice[x]);
			}
			if(x==1) {
				Cscore++;
				System.out.println("Computer Score: "+Cscore+" Player Score: "+Pscore+"\nYou lost!\nI picked: "+ComChoice[x]);
				if (Cscore==3) {
					System.out.println("Game ends.\n[DEFEAT]");
					break;
				}
			}
			if(x==2) {
				Pscore++;
				System.out.println("Computer Score: "+Cscore+" Player Score: "+Pscore+"\nYou won!\nI picked: "+ComChoice[x]);
				if (Pscore==3) {
					System.out.println("Game ends.\n[VICTORY]");
					break;
				}
			}
			
		}
		if("paper".equalsIgnoreCase(PChoice)) {
			if(x==1) {
				System.out.println("It's a tie!\nI picked: "+ComChoice[x]);
			}
			if(x==2) {
				Cscore++;
				System.out.println("Computer Score: "+Cscore+" Player Score: "+Pscore+"\nYou lost!\nI picked: "+ComChoice[x]);
				if (Cscore==3) {
					System.out.println("Game ends.\n[DEFEAT]");
					break;
				}
			}
			if(x==0) {
				Pscore++;
				System.out.println("Computer Score: "+Cscore+" Player Score: "+Pscore+"\nYou won!\nI picked: "+ComChoice[x]);
				if (Pscore==3) {
					System.out.println("Game ends.\n[VICTORY]");
					break;
				}
			}
			
		}
		if("scissor".equalsIgnoreCase(PChoice)) {
			if(x==2) {
				System.out.println("It's a tie!\nI picked: "+ComChoice[x]);
			}
			if(x==0) {
				Cscore++;
				System.out.println("Computer Score: "+Cscore+" Player Score: "+Pscore+"\nYou lost!\nI picked: "+ComChoice[x]);
				if (Cscore==3) {
					System.out.println("Game ends.\n[DEFEAT]");
					break;
				}
			}
			if(x==1) {
				Pscore++;
				System.out.println("Computer Score: "+Cscore+" Player Score: "+Pscore+"\nYou won!\nI picked: "+ComChoice[x]);
				if (Pscore==3) {
					System.out.println("Game ends.\n[VICTORY]");
					break;
				}
			}
			
		}
		}
		scanner.close();
	}
}