import java.util.Scanner;
import java.util.Random;

public class TicTacToe {
	int goal=3;
	char board[][]= {{' ',' ',' '},
						{' ',' ',' '},
						{' ',' ',' '}};
	
	void PrintBoard() {
		System.out.println("==================");
		for(int col=0; col<3; col++) {
			for(int row=0; row<3; row++) {
				System.out.print(board[col][row]+" ");
			}
			System.out.println();
		}
	}

	
	void playerTurn(Scanner sc, char x) {
		while(true) {
			System.out.print("Your Choice: ");
			int choice = sc.nextInt();
				if(choice==1 && board[2][0]==' ') {board[2][0]=x; break;}
				else if(choice==2 && board[2][1]==' ') {board[2][1]=x; break;} 
				else if(choice==3 && board[2][2]==' ') {board[2][2]=x; break;}
				else if(choice==4 && board[1][0]==' ') {board[1][0]=x; break;}
				else if(choice==5 && board[1][1]==' ') {board[1][1]=x; break;}
				else if(choice==6 && board[1][2]==' ') {board[1][2]=x; break;}
				else if(choice==7 && board[0][0]==' ') {board[0][0]=x; break;}
				else if(choice==8 && board[0][1]==' ') {board[0][1]=x; break;}
				else if(choice==9 && board[0][2]==' ') {board[0][2]=x; break;}
				else {System.out.println("Wrong input!");}
		}
	}
	
	void computerTurn(Random rd, char o) {
		while(true) {
			int choice = rd.nextInt(9)+1;
				if(choice==1 && board[2][0]==' ') {board[2][0]=o; break;}
				else if(choice==2 && board[2][1]==' ') {board[2][1]=o; break;} 
				else if(choice==3 && board[2][2]==' ') {board[2][2]=o; break;}
				else if(choice==4 && board[1][0]==' ') {board[1][0]=o; break;}
				else if(choice==5 && board[1][1]==' ') {board[1][1]=o; break;}
				else if(choice==6 && board[1][2]==' ') {board[1][2]=o; break;}
				else if(choice==7 && board[0][0]==' ') {board[0][0]=o; break;}
				else if(choice==8 && board[0][1]==' ') {board[0][1]=o; break;}
				else if(choice==9 && board[0][2]==' ') {board[0][2]=o; break;}
		}
	}
	
	boolean Win() {
		for(int i=0; i<3; i++) {
			if(board[i][0]!=' ' && board[i][0]==board[i][1] && board[i][1]==board[i][2]) {
				return true;
			}else if(board[0][i]!=' ' && board[0][i]==board[1][i] && board[1][i]==board[2][i]) {
				return true;
			}
		}
		if(board[0][0]!=' ' && board[0][0]==board[1][1] && board[1][1]==board[2][2]) {
			return true;
		}
		if(board[0][2]!=' ' && board[0][2]==board[1][1] && board[1][1]==board[2][0]) {
			return true;
		}
		
		
		return false;
	}
	
	boolean Tie(){
		for(int col=0; col<3; col++) {
			for(int row=0; row<3; row++) {
				if(board[col][row]==' ') {return false;}
			}
		}
		return true;
	}
	
	void showScore(int p, int c) {
		System.out.printf("""
				Player Score: %d
				Computer Score: %d
				""",p,c);
	}
	
	boolean winScore(int p, int c,int a) {
		
		if(p>=goal) {
			System.out.printf("""
					=======================
					[Congratulations!] 
					You are the first
					to reach a score
					of %d,
					You beat the computer!
					Attempts: %d
					""",goal,a);
			return true;
		}else if(c>=goal) {
			System.out.printf("""
					=======================
					[You lost!]
					The computer was
					the first
					to reach a score
					of %d,
					You lost to a computer!
					Attempts: %d
					""",goal,a);
			return true;
		}
		return false;
	}
	
	void clear() {
		for(int col=0;col<3;col++) {
			for(int row=0; row<3; row++) {
				board[col][row]=' ';
			}
		}
	}
	
	public static void main(String[] args) {
		TicTacToe game = new TicTacToe();
		
		Scanner sc = new Scanner(System.in);
		Random rd = new Random();
		
		char player = 'X';
		char computer = 'O';
		
		int pScore=0,
			cScore=0,
			attempts=0;
		
		System.out.println("""
				7 8 9
				4 5 6
				1 2 3
				""");
		boolean start = true;
		boolean playerTurn=true;
		
		while(start) {
			if(playerTurn) {
				game.playerTurn(sc, player);
				playerTurn = false;
				if(game.Win()) {
					game.PrintBoard();
					System.out.println("You won!");
					pScore++;
					game.showScore(pScore, cScore);
					
					game.clear();
				}
			}else {
				game.computerTurn(rd, computer);
				playerTurn = true;
				if(game.Win()) {
					game.PrintBoard();
					System.out.println("You lost!");
					cScore++;
					game.showScore(pScore, cScore);
					
					game.clear();
				}
				game.PrintBoard();
				attempts++;
			}
			
			if(game.Tie()) {
				System.out.println("It's a Tie!");
				game.clear();
			}
			
			if(game.winScore(pScore, cScore, attempts)) {
				start=false;
				break;
			}
		}
	}
}