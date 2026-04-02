
public class Chess {
	String board[][]={
			{"♜","♞","♝","♛","♚","♝","♞","♜"},
			{"♟","♟","♟","♟","♟","♟","♟","♟"},
			  {" "," "," "," "," "," "," "," "},
			  {" "," "," "," "," "," "," "," "},
			  {" "," "," "," "," "," "," "," "},
			  {" "," "," "," "," "," "," "," "},
			{"♙","♙","♙","♙","♙","♙","♙","♙"},
			{"♖", "♘", "♗", "♕", "♔", "♗", "♘", "♖"},
			};
	
	void Print() {
		System.out.println("\n  \tA\tB\tC\tD\tE\tF\tG\tH\n"
				+ " +----------------------------------------------------------------------+");
		for(int row=0; row<8; row++) {
			System.out.print((-1*(row-8))+"|\t");
			for(int col=0; col<8; col++) {
				System.out.print(board[row][col]+"\t");
			}
			System.out.println("|"+(-1*(row-8)));
		}
		System.out.println(" +----------------------------------------------------------------------+"
				+ "\n  \tA\tB\tC\tD\tE\tF\tG\tH\n");
	}
	
	
	
	String white="♕♔♗♘♖♙", black="♛♚♝♞♜♟";
	
	
	
	
	int posX=0, posY=0;
	void posXY(String choice) {
		String letter = "ABCDEFGH";
		String number = "87654321";
		for(int i=0; i<8; i++) {
			if(choice.charAt(1)==number.charAt(i)) {
				posY=i;
				System.out.println("posY: "+i);
				for(int j=0; j<8; j++) {
					if(choice.charAt(0)==letter.charAt(j)) {
						posX=j;
						System.out.println("posX: "+j);
					}
				}
			}
		}
	}
	
	int moveX=0, moveY=0;
	void moveXY(String choice) {
		String letter = "ABCDEFGH";
		String number = "87654321";
		for(int i=0; i<8; i++) {
			if(choice.charAt(1)==number.charAt(i)) {
				moveY=i;
				System.out.println("moveY: "+i);
				for(int j=0; j<8; j++) {
					if(choice.charAt(0)==letter.charAt(j)) {
						moveX=j;
						System.out.println("moveX: "+j);
					}
				}
			}
		}
		
	}
	
	boolean checkOccupied(String pieces) {
		if(!" ".equals(pieces)) {
			board[moveX][moveY]=pieces;
			board[posX][posY]=" ";
			return false;
		}else {
			return true;
		}
	}
	
	void moveWPawn() {
		if (checkOccupied("♙")) {
		}
	}
	
	public static void main(String[] args) {
		Chess play = new Chess();
		play.Print();
		
		play.posXY("C2");
		play.moveXY("C4");
		play.moveWPawn();
		play.Print();
	}
}
