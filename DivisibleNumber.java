import java.util.Scanner;
public class DivisibleNumber {
	private void BEGIN() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Set a MAX RANGE starting from 2 to [?]: ");
		int system=sc.nextInt();
	
		if(system<=1) {
			System.out.println("Input numbers greater than 1!");
			sc.close();
			return;
		}
		
		System.out.print("How many numbers will you check if divisible? ");
		int amount = sc.nextInt();
		int array[] = new int[amount];
		
		
		System.out.println("Give "+amount+" of number/s to check:");
		
		boolean div[][] = new boolean[amount][system];
		for(int i=0; i<amount; i++) {
				System.out.print(i+1+". ");
				array[i]=sc.nextInt();
				
				for(int j=0; j<system; j++) {
					div[i][j]=(array[i]%(j+1)==0)?true:false;
						
				}
		}
		System.out.println();
		for(int i=2; i<=system;i++) {
			if(i>9) {
				System.out.print(" "+i+" ");
			}else {
				System.out.print("  "+i+" ");
			}
		}
		System.out.println();
		System.out.print("+");
		System.out.print("---+".repeat(system-1));
		System.out.println();
		for(int row=0; row<array.length;row++) {
			System.out.print("|");
			for(int col=0; col<system-1;col++) {
				System.out.print(div[row][col]?" X |":"   |");
			}
			System.out.println(" "+array[row]);
			System.out.print("+");
			System.out.print("---+".repeat(system-1));
			System.out.println();
		}
		sc.close();
		
	}
	
	public static void main(String[] args) {
		DivisibleNumber d = new DivisibleNumber();
		try {
			d.BEGIN();
		}catch(Exception e) {
			System.out.println("Error occured program ends!");
		}finally {
			System.out.println("Program exited...");
		}
	}
}
