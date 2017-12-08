
public class Sudoku {
	int[][] doku;
	int[][] matrixcopy;
	// ||not used currently|| private boolean success;

	public Sudoku(int[][] matrix) {
		doku = matrix;
		matrixcopy=matrix;
	}

	
	public boolean solve(int x, int y) {
		if (x == 9 && y == 8) {
			return true;
		}else if (x > 8 ) {
			x=0;
			y=y+1;
		}
		//Om det redan �r ifyllt
		int[] solutions=possibleSolutions(x,y);
		if (doku[x][y] != 0) {
			if (!legalNumber(doku[x][y],x,y) ) {
				return false;
			}else {
				return solve(x+1,y);
			}
		}else {
			
			for (int i=0; i<9; i++) {
				if (solutions[i] == 1) {
					doku[x][y] = i+1;
					if(!(solve(x+1,y))){
						System.out.println("asd");

						// H�r kommer vi stryka ut inskickade v�rden - l�gga in en if(matrix.contains..)? Ja.
					}else {
						return true;
					}
				}
			}
			doku[x][y]=0;
			return false;
		}
	}
	
	//Hittar m�jliga l�sningar f�r rutan i koordinat (x,y)
	public int[] possibleSolutions(int x, int y) {
		int[] solutions = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		
		if (doku[x][y] == 0) {
		for (int i=1; i<10; i++) {
			if (legalNumber(i,x,y)) {
				solutions[i-1] = 1;						// 1 betyder att t.ex. solutions [6] �r ett m�jligt svar
			}											// dvs vi kan potentiellt s�tta in en sexa. 	
		}
		}
		
		return solutions;
	}
	
	
	
	//Kollar om det gissade l�sningsv�rdet �r till�tet (inte finns i x- eller y-led eller i l�dan)
	public boolean legalNumber(int a, int x, int y) {
		
		//Kollar i x-led om a redan finns d�r
		for (int bredd=0; bredd<9; bredd++) {
			if (!(bredd == x)) {
				if (doku[bredd][y] == a) {			
					return false;
				}
			}
		}
		
		//Kollar i y-led om a redan finns d�r
		for (int hojd=0; hojd<9; hojd++) {
			if (!(hojd==y)) {
				if (doku[x][hojd] == a) {			
					return false;
				}
			}
		}
		
		//Kollar i "l�dan" efter samma tal
		// S�tter startvariablerna till �vre v�nstra h�rnet i l�dan
		int startx = x-x%3;					
		int starty = y-y%3;
		
		for (int t = startx; t<startx +3; t++) {
			for (int p = starty; p<starty+3; p++) {
				if (doku[t][p] == a && !(t == x && p==y)) {
					return false;
				}
			}
		}
		
		//Om a finns i varken x- eller y-led eller l�dan �r v�rdet till�tet
		return true;								
		//TODO: Beh�ver man egentligen kolla s� att man inte kollar i [x][y]? Det kommer
		//Ju egentligen bara vara en nolla vilket aldrig kommer == a.. 
	}

}
