import java.util.*;
import java.io.*;


public class SudokuReader {
	Scanner scanner = null ;
	int counter=0;
	
	public SudokuReader() {
		
		try {
			scanner = new Scanner(new File("C:\\Users\\Emil\\git\\Sudoku-Inlamningsuppgift2\\Inlamning\\src\\Sudokufile"));
			} catch(FileNotFoundException e ) {
			System.out.println("Couldn’t open file: Sudokufile");
			System.exit(1);
			}
}
	
	
	public int[][] newSudoku(){
		counter++;
		if(counter >50) {
			try {
				scanner = new Scanner(new File("C:\\Users\\Emil\\git\\Sudoku-Inlamningsuppgift2\\Inlamning\\src\\Sudokufile"));
				} catch(FileNotFoundException e ) {
				System.out.println("Couldn’t open file: Sudokufile");
				System.exit(1);
				}
			counter=0;
		}
		String line = scanner.nextLine();
		int[][] newsudo = new int[9][9];
		
		
		for (int j=0; j<9; j++) {
		line =scanner.nextLine();
		Scanner linescanner = new Scanner(line);
		for (int i=0; i<9; i++) {
			newsudo[i][j] = linescanner.nextInt();
		}

	}
		return newsudo;
	}
}

