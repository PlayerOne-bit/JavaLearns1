import java.util.Scanner;


public class BinaryCodeReader {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("[BINARY CODE]");
		System.out.print("Press '1' to input letter/number/symbol,\nPress '0' to input values from 0 to 127:\n");
		int choice = scanner.nextInt();
		while(true) {
			char b='0', c='0',d='0', e='0',f='0',g='0',h='0';	
			int bc=0;
			if(choice ==1) {
			System.out.print("Input a letter/number/symbol: ");
			char code = scanner.next().charAt(0);
			bc=code;
			} else
			if (choice ==0) {
				System.out.print("Input a value from 0 to 127: ");
				bc=scanner.nextInt();
				} else {
				System.out.println("Try again...");
				break;
			}
		
		int x=bc;
		if (x>=0 && x<=127) {
		if (x>=64 && x<=127) {
			b='1';
			x-=64;
		}
		if(x>=32 && x<=63) {
			c='1';
			x-=32;
		}
		if(x>=16 && x<=31) {
			d='1';
			x-=16;
		}
		if(x>=8 && x<=15) {
			e='1';
			x-=8;
		}
		if (x>=4 && x<=7) {
			f='1';
			x-=4;
		}
		if(x>=2 && x<=3) {
			g='1';
			x-=2;
		}
		if(x==1) {
			h='1';
			x-=1;
		}
		System.out.println("ASCII Code: "+bc);
		System.out.println("Binary Code: "+b+c+d+e+f+g+h+"\n");
		} else {
			System.out.println("Wrong input.\n[PROGRAM ENDS]");
			break;
			}
		}
		scanner.close();
	}
}
