import java.util.Scanner;

public class BalanceCashierChange {

	public static void main(String[] args) {
		Scanner n = new Scanner(System.in);
		
		double balance,product,costperproduct, totalcost=0, change;
		
		System.out.print("Balance: ");
		balance = n.nextInt();
		
		System.out.print("How many products? ");
		product=n.nextInt();
		
		System.out.println("Give each product a cost:");
		for (int i=0; i<product; i++) {
			costperproduct=n.nextInt();
			totalcost += costperproduct;
		}
		System.out.println("Total Cost: "+totalcost);
		change = balance - totalcost;
		
		if (change>0) {
			System.out.println("Change: "+change);
			balance = change ;
			System.out.println("TotalBalance: "+balance);
		} else {
			System.out.println("Insufficient Balance: "+balance);
		}
		n.close();
	}

}
