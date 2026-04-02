import java.util.Scanner;
import java.util.Random;

public class TurnBasedRPG {
	
	class A{
		private static int a=0;
		public static int get() {
			return a;
		}
		public static void set(int b) {
			a=b;
		}
	}
	class B extends A{
		
	}
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		Random random = new Random();
		
		int pLives=100;
		int mLives=random.nextInt(101);
		
		int pMana=100;
		
		int kills=0;
		
		boolean pDefend = false;
		boolean mDefend = false;
		
		
		while(true) {
			System.out.println("Player HP: "+pLives +"\nPlayer Mana: "+pMana+"\nMonster HP: "+mLives+"\n\nPlayer Turns...\n\n Press \"a\" to attack,\n Press \"b\" to defend,\n Press \"c\" to heal.\n Press \"d\" to rest.");
			
			char choice = scanner.next().charAt(0);
			
			if (choice=='a'){
				System.out.println("You attacked!");
				int pDamage=random.nextInt(101);
				if(mDefend){
					System.out.println("The monster blocked your attacked!");
				} else{
					mLives-=pDamage;
					System.out.println("The damage you deal is "+pDamage);
				}
				
				
			} else if (choice=='b'){
				pDefend=true;
				System.out.println("You defend...");
			} else if (choice=='c'){
				pMana-=50;
				if (pMana>0) {
					int pHeal=25;
					pLives+=pHeal;
					System.out.println(pHeal+ " HP recovered");
				} else {
					System.out.println("Out of mana!");
					pMana=0;
				}	
			}  else if (choice=='d') {
					pMana+=75;
					System.out.println("You gained 75MP! Total of "+pMana+" MP!");
			}
			
			
			if (mLives<1){
				System.out.print("\033[H\033[2J");
				System.out.flush();
				System.out.println("Monster died!\n[VICTORY]\n");
				kills++;
				mLives=random.nextInt(101);
				System.out.println(">>>>>>>>>>>>>>>>  KILLS: "+kills+"  <<<<<<<<<<<<<<<<<<<<<\n");
			}
			
			
			mDefend=false;
			
			System.out.println("______________________________");
			System.out.println("Monster Turns...");
			int mAction=random.nextInt(3);
			
			
			if(mAction==0){
				System.out.println("...");
				if(pDefend){
					System.out.println("You blocked it!");
				} else{
				int mDamage=random.nextInt(51);
				pLives-=mDamage;
				System.out.println("You lost "+mDamage+"HP");
				}
			} else if (mAction==1){
				mDefend=true;
				System.out.println("...");
			} else if (mAction==2){
				int mHeal=25;
				mLives+=mHeal;
				System.out.println("...");
			}
			pDefend=false;
		System.out.println("_____________________________");
		
			if (pLives<1){
				System.out.println("You died!\n[DEFEAT]\n");
				break;
			} 
		}
		scanner.close();
	}
}