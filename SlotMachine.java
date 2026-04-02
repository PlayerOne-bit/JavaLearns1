import java.util.Scanner;
import java.util.Random;

public class SlotMachine {
	
	int randomizer(int x) {
		Random rng = new Random();
		int random=rng.nextInt(x);
		return random;
	}
	
	
	public static void main(String[] args) {
		SlotMachine check = new SlotMachine();
		Scanner in = new Scanner(System.in);
		String slot[]= {"🍒","🍋","🍉","🍊","🍇","🔔","7️⃣","⭐","💎","🍀","🃏"};
		double multiplier[] = {5,2,3,1.5,3,2,10,5,4,0};
		System.out.printf("""
				Welcome to Wakai's Slot Machine!
				Rules:
				1. Different icons will give no points
				3. Having all same icons will win jackpot
				Icons:
				🍒 x5, 🍋 x2, 🍉 x3, 🍊 x1.5, 🍇 x3,
				🔔 x2, 7️⃣ x10, ⭐ x5, 💎 x5, 🍀 x4,
				 🃏 x0
				""");
		System.out.println("===================================");
		System.out.print("Give Total Balance(Higher than 5 pesos): Php.");
		int balance = in.nextInt();
		
		int gains=0;
		int loss=0;
		
		
		while(balance>=5 && balance<=2147483647) {
			boolean manual=false;
			System.out.println("===================================");
			System.out.print("Set a Bet higher than your balance[Php."+balance+"]\n(Limit: 5 pesos to 100 pesos): Php.");
			int bet = in.nextInt();
			System.out.print("Give an auto roll count(Input 0 to input manually): ");
			int auto=in.nextInt();
			System.out.println("===================================");
			
			String c[]= {" ", " ", " "};
			int x = 1;
			String n="";
			if(auto==0) {
				auto=1;
				manual=true;
			}
			
			
			for(int z=0; z<auto; z++) {
				if(bet>=5 && bet<=100 && balance>=bet) {
					for(int tries=0; tries<3; tries++) {
						
						int randomized=0;
						randomized = check.randomizer(slot.length);
						switch(x) {
							case 1: n="st";break;
							case 2: n="nd";break;
							case 3: n="rd";break;
						}
						char confirm=' ';
						if(manual) {
							while(confirm!='Y') {
								System.out.print("Press Y to Get the "+x+n+" Slot: ");
								 confirm= in.next().charAt(0);
								 
								if(confirm=='Y' || confirm=='y') {
									c[tries]=slot[randomized];
									confirm='Y';
								}else {
									System.out.println("Try again!");
								}
								System.out.print("You got: ");
								for(int i=0; i<3; i++) {
									System.out.print("["+c[i]+"]");
								}
								System.out.println();
							}
						}else {
							c[tries]=slot[randomized];
						}
							if(tries==2) {
								System.out.println("===================================");
								System.out.print("You got: ");
								for(int i=0; i<3; i++) {
									System.out.print("["+c[i]+"]");
								}
								System.out.println();
								if(c[0]==c[1] && c[1]==c[2]) {
									int save=balance;
									balance+= bet*multiplier[randomized];
									gains+= save;
									if(gains<0) {
										gains*=-1;
									}
									System.out.println("You got em all, you win: +Php."+save);
									System.out.println("Multiplier: x"+multiplier[randomized]);
									System.out.println("Your Balance: Php. "+balance);
								}else {
									balance-=bet;
									loss+=bet;
									System.out.println("I'm sorry you lost: -Php."+bet);
									System.out.println("Your Balance: Php. "+ balance);
								}
							}
							x++;
					}
				}else {
					System.out.print("Insufficent balance! Php.");
					System.out.println(balance);
				}
				
			}
		}
		System.out.println("===================================");
		if(balance<5) {
			System.out.println("Congrats! You are broke!");
		}else if(balance>=2147483647) {
			System.out.println("What the fuck you are too rich!");
		}
		System.out.println("Gains: Php."+gains);
		System.out.println("Loss: Php."+loss);
		
		
		
		
		in.close();
	}
}
