import java.util.ArrayList;
import java.util.Scanner;

public class ToDoList {
	
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		ToDoList Do = new ToDoList();
		int choice=0;
		while(choice!=5) {
			choice=0;
			System.out.print("""
					[Welcome to Wakai's To Do List]
					Pick a choice you want to do:
					1. Show List
					2. Add List
					3. Remove List
					4. Mark/Unmrk List
					5. Exit Program
					
					Your Choice:  """);
			choice = scn.nextInt();
			switch(choice) {
				case 1:
					Do.Show();
					break;
				case 2:
					Do.Add(scn);
					break;
				case 3:
					Do.Remove(scn);
					break;
				case 4:
					Do.Mark(scn);
					break;
				case 5:
					System.out.println("Program Ends!");
					break;
				default:
					System.out.println("Wrong input!");
			}
		}
		
	}
	
	ArrayList<String> list = new ArrayList<>();
	
	void Show() {
		
		for(int i=0; i<list.size(); i++) {
			System.out.println(i+1+". "+list.get(i));
		}
		System.out.println();
		return;
	}
	
	Scanner Add(Scanner scn){
		Scanner in = new Scanner(System.in);
		System.out.print("How much do you want to add?(Press '0' to cancel): ");
		int add = scn.nextInt();
		
		if(add!=0) {
			System.out.println("Write the things you want to add:");
			for(int i=0; i<add; i++) {
				System.out.print("Input: ");
				String addList = in.nextLine();
				list.add(addList);
				System.out.println("Successfully added!\n");
			}
		}else {
			System.out.println("Cancelled!");
		}
		return in;
	}
	
	void Remove(Scanner scn) {
		System.out.println("Pick on the list you want to remove:");
		Show();
		while(true) {
			System.out.print("Choice:(Press '0' to cancel): ");
			int choice=scn.nextInt();
			if(choice>0&&choice<=list.size()) {
				list.remove(choice-1);
				System.out.println("Successfully Removed!");
				break;
			}else if(choice==0){
				System.out.println("Cancelled!");
				return;
			}else {
				System.out.println("Wrong Input!");
			}
		}
		String again=null;
		while(again!="n" || again!="N") {
			System.out.print("Continue(Y/N)? ");
			again = scn.next();
			
			if(again.equalsIgnoreCase("Y")) {
				Remove(scn);
				break;
			}else {
				System.out.println("Wrong input!");
			}
		}
		System.out.println();
	}
	
	void Mark(Scanner scn) {
		System.out.println("Pick on the list you want to mark/unmark:");
		Show();
		while(true) {
			System.out.println("Choice: ");
			int choice=scn.nextInt();
			if(choice>0&&choice<=list.size()) {
				if(!list.get(choice-1).contains(" [X]")) {
					list.set(choice-1,list.get(choice-1)+" [X]");
					System.out.println("Successfully Marked!");
					break;
				}else {
					list.set(choice-1, list.get(choice-1).replace(" [X]", ""));
					System.out.println("Succesfullt Unmarked!");
					break;
				}
			}else if(choice==0){
				System.out.println("Cancelled!");
				return;
			}else {
				System.out.println("Wrong Input!");
			}
		}
	}
}
