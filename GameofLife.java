import java.util.Random;
public class GameofLife {
	private int generation=50;
	private int size=100;
	
	private boolean[][] grid=new boolean[size][size];
	
	
	
	public static void main(String[] args) {
		GameofLife let = new GameofLife();
		Random rd = new Random();
		for(int row=0; row<let.size; row++) {
			for(int col=0; col<let.size; col++) {
				let.grid[row][col]=rd.nextBoolean();
			}
		}
		
		for(int gen=0; gen<let.generation;gen++) {
			let.displayGrid();
			let.nextGeneration();
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	void displayGrid() {
		System.out.println("\033[H\033[2J");
        System.out.flush();
		System.out.println("\n");
		for(int row=0; row<size; row++) {
			for(int col=0; col<size; col++) {
				String cell=(grid[row][col])?"# ":"  ";
				System.out.print(cell);
			}
			System.out.println();
		}
	}
	
	
	void nextGeneration() {
		for(int col=0; col<size; col++) {
			for(int row=0; row<size; row++) {
				int cellLives=countCellLives(row,col);
				grid[row][col]=((grid[row][col] && (cellLives==2 || cellLives==3)) || (!grid[row][col] && (cellLives==3)));
				
			}
		}
	}
	
	int countCellLives(int row, int col) {
		int count=0;
		for(int surroundingRows =-1; surroundingRows<=1; surroundingRows++) {
			for(int surroundingColumns=-1; surroundingColumns<=1; surroundingColumns++) {
				if(surroundingColumns==0 && surroundingRows==0)continue;
				int neighboringRows = row + surroundingRows;
				int neighboringColumns = col + surroundingColumns;
				if ((neighboringRows>=0 && neighboringRows<size) && (neighboringColumns>=0 && neighboringColumns<size) && grid[neighboringRows][neighboringColumns] ){
					count++;
				}
				
			}
		}
		return count;
	}
	
}
