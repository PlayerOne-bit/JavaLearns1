import java.util.Scanner;
public class PasswordConvert {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String password;
		
		System.out.println("Create Password:");
		password = scanner.nextLine();

		System.out.println("Password:");
		for(int i=0; i<password.length() ; i++) {
			System.out.print('*');
		}
		System.out.println("\n\nShow your password? [Y/N]");
		char choice = scanner.next().charAt(0);
		if (choice == 'y' || choice == 'Y') {
			System.out.print(password);
		} else if (choice == 'n' || choice == 'N'){
			System.out.print("Ok, keep your secrets then...");
		} else {
			System.out.print("Failed to identify.");
		}
		scanner.close();
	}
}
