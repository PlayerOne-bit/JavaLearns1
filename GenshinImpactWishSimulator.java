import java.util.*;
public class GenshinImpactWishSimulator {
	
	private int cash;
	
	void setCash(Scanner sc) {
		System.out.print("Set your cash: Php. ");
		int cash = sc.nextInt();
		this.cash = cash;
	}
	
	
	public static void main(String [] args) {
		Scanner sc = new Scanner(System.in);
		Random rd = new Random();
		
		System.out.println("Welcome to Genshin Impact Simulator!");
		
		
		System.out.println("Do you want to pay with cash? (Y/N)");
		String yn = sc.next();
		if(yn.equalsIgnoreCase("Y")) {
			
			 
		}else if (yn.equalsIgnoreCase("N")) {
			
		}
			
		
	}
}
