
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
		//Om det redan är ifyllt
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
						// Om matrix (inmatrisen) innehåller ett värde i (x,y) vill vi behålla det
				//		if (matrixcopy[x][y] == 0) {
						
			//			}else {
				//			doku[x][y] = matrixcopy[x][y];
				//		}

						
						// Här kommer vi stryka ut inskickade värden - lägga in en if(matrix.contains..)? Ja.
					}else {
						return true;
					}
				}
			}
			doku[x][y]=0;
			return false;
		}
		
	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
/*		
		if ( doku[x][y] != 0 && (legalNumber(doku[x][y],x,y) == false)) {
				return false;
		}else {
		
		int[] solutions=possibleSolutions(x,y);

		for (int i=0; i<9; i++) {
			if (solutions[i] == 1) {
				doku[x][y] = i+1;
				if(solve(x+1,y) == false) {
					doku[x][y] = 0;
					if (i==8) {
						return false;
					}
				}
			}
		}
		}

		
		
		return false;
		//return true;*/
	}
	
	
	//Hittar möjliga lösningar för rutan i koordinat (x,y)
	public int[] possibleSolutions(int x, int y) {
		int[] solutions = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		
		if (doku[x][y] == 0) {
		for (int i=1; i<10; i++) {
			if (legalNumber(i,x,y)) {
				solutions[i-1] = 1;						// 1 betyder att t.ex. solutions [6] är ett möjligt svar
			}											// dvs vi kan potentiellt sätta in en sexa. 	
		}
		}
		
		return solutions;
	}
	
	
	
	//Kollar om det gissade lösningsvärdet är tillåtet (inte finns i x- eller y-led eller i lådan)
	public boolean legalNumber(int a, int x, int y) {
		
		//Kollar i x-led om a redan finns där
		for (int bredd=0; bredd<9; bredd++) {
			if (!(bredd == x)) {
				if (doku[bredd][y] == a) {			
					return false;
				}
			}
		}
		
		//Kollar i y-led om a redan finns där
		for (int hojd=0; hojd<9; hojd++) {
			if (!(hojd==y)) {
				if (doku[x][hojd] == a) {			
					return false;
				}
			}
		}
		
		//Kollar i "lådan" efter samma tal
		// Sätter startvariablerna till övre vänstra hörnet i lådan
		int startx = x-x%3;					
		int starty = y-y%3;
		
		for (int t = startx; t<startx +3; t++) {
			for (int p = starty; p<starty+3; p++) {
				if (doku[t][p] == a && !(t == x && p==y)) {
					return false;
				}
			}
		}
		
		//Om a finns i varken x- eller y-led eller lådan är värdet tillåtet
		return true;								
		//TODO: Behöver man egentligen kolla så att man inte kollar i [x][y]? Det kommer
		//Ju egentligen bara vara en nolla vilket aldrig kommer == a.. 
	}

}
