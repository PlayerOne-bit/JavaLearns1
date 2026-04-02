import java.util.Random;
import java.util.Scanner;

public class RandomFollowCodeGame {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Random random = new Random();
		char[] BigLetter= {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
		char[] SmallLetter = {'a','b','c','d','e','f','g','h','i','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
		char[] Numbers = {'0','1','2','3','4','5','6','7','8','9'};
		char[] Symbols = {'!','@','#','$','%','^','&','*','(',')','.',',','<','>',':','\'','\"',';','|','\'','{','}','[',']','`','~','+','-','/','?','_','='};
		int score=0;
		int lives=3;
		while(true) {
		StringBuilder codeBuilder = new StringBuilder();
		for (int i=0; i<10; i++) {
			int x= random.nextInt(BigLetter.length);
			int y=random.nextInt(Numbers.length);
			int z=random.nextInt(Symbols.length);
			char[] store= {BigLetter[x],SmallLetter[x],Numbers[y],Symbols[z]};
			int a=random.nextInt(store.length);
			
			codeBuilder.append(store[a]);
		}
		String code = codeBuilder.toString();
		System.out.println("Follow the code below:\n"+code);
		
		System.out.println("Your Input:");
		String input=scanner.next();
		
		if (input.equals(code)) {
			score++;
			System.out.println("Good job!\nScore: "+score+" Lives: "+lives+"\n");
		} else {
			lives--;
			System.out.println("You failed!\nScore: "+score+" Lives: "+lives+"\n");
			if(lives<1) {
				System.out.println("[GAME ENDS]");
				break;
			}
		}
		}
		scanner.close();
	}
}
