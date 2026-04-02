import java.util.Scanner;

public class PythagoreanTheorem {

	public static void main(String[] args) {
		Scanner nigga = new Scanner(System.in);
		double a,b,c;
		String Choice;
		char choice;
			
		System.out.print("[PYTHAGOREAN THEOREM]\nInstruction:\nfind a, b, c\nPick a letter:\n");
		
		while(true) {
		
		Choice=nigga.next();
		choice=Choice.charAt(0);
		
		if(choice =='a') {
			System.out.print("Enter value of b: ");
			b=nigga.nextDouble();
			System.out.print("Enter value of c: ");
			c=nigga.nextDouble();
			b*=b;
			c*=c;
			a=c-b;
			a=Math.sqrt(a);
			System.out.println("Value of a: "+a+"\n\n");		
		}
		
		else if(choice =='b') {
			System.out.print("Enter value of a: ");
			a=nigga.nextDouble();
			System.out.print("Enter value of c: ");
			c=nigga.nextDouble();
			a*=a;
			c*=c;
			b=c-a;
			b=Math.sqrt(b);
			System.out.println("Value of b: "+b+"\n\n");		
		}
		
		else if(choice =='c') {
			System.out.print("Enter value of a: ");
			a=nigga.nextDouble();
			System.out.print("Enter value of b: ");
			b=nigga.nextDouble();
			a*=a;
			b*=b;
			c=a+b;
			c=Math.sqrt(c);
			System.out.println("Value of b: "+c+"\n\n");		
		}
		
		else {
			System.out.print("Invalid input. program ends!");
			break;
			}
		
		System.out.print("Continue? Press Y or y: ");
		Choice=nigga.next();
		choice=Choice.charAt(0);
		
		if (choice=='y' || choice=='Y') {
			System.out.println("Find a, b, c\nPick a letter:");
		} else {
			System.out.print("Invalid input. program ends!");
			break;
		}
		}
		nigga.close();
	}
}
