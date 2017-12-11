import java.awt.Toolkit;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

public class Main_sudoku extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		//BorderPane har sina children i top, left, right, bottom, och center.
		//Läs mer på https://docs.oracle.com/javafx/2/layout/builtin_layouts.htm
		BorderPane root = new BorderPane(); 
		
		Scene scene = new Scene(root); 
		primaryStage.setTitle("MainSudoku");
		primaryStage.setScene(scene); 
		primaryStage.show(); 
		
		//Skapa en TilePane, ge den lite fina färger och sätt den i center av BorderPane
		TilePane tilepane = new TilePane(); 
		String styles =
		        "-fx-background-color: #D4F6F4;" +
		        "-fx-border-color: #000000;" ;
		tilepane.setStyle(styles);
		tilepane.setVgap(0);
		tilepane.setHgap(0);
		tilepane.setMaxWidth(452);
		tilepane.setMaxHeight(452);
		root.setCenter(tilepane);
		
		//Skapa några fina TilePanes runt vår TilePane i center som en ram(inget läggs in i dessa)
		TilePane left = new TilePane();
		String stylesLeft =
		        "-fx-background-color: #DAF7A6;";
		left.setStyle(stylesLeft);
		left.setPrefWidth(100);
		root.setLeft(left);
		
		TilePane right = new TilePane();
		String stylesRight =
		        "-fx-background-color: #DAF7A6;";
		right.setStyle(stylesRight);
		right.setPrefWidth(100);
		root.setRight(right);

		TilePane top = new TilePane();
		top.setPrefHeight(20);
		root.setTop(top);
		
		//Skapa en matris där textfields läggs in 
		TextField[][] matrix = new TextField[9][9];

	    for (int i = 0; i <9; i++) {
	    	for (int j=0; j<9; j++) {
	    		matrix[i][j] = new OneLetterTextField();
	    		matrix[i][j].setPrefWidth(50);
	    		matrix[i][j].setPrefHeight(50);
	    		matrix[i][j].setAlignment(Pos.CENTER);
	    		matrix[i][j].setVisible(true);
	    		matrix[i][j].setEditable(true);
	    		matrix[i][j].setText("");
	    		tilepane.getChildren().add(matrix[i][j]);
	    	}
	    }
		
		//SKAPA NYA KNAPPAR  
		//Skapa en HBox med tre buttons "Solve", "Clear" och "New Sudoku"
		HBox hbox = new HBox(); 
		Button solve = new Button("Solve");		
		HBox.setHgrow(solve, Priority.ALWAYS);
		solve.setMaxWidth(Double.MAX_VALUE); 
		
		Button clear = new Button("Clear");
		HBox.setHgrow(clear, Priority.ALWAYS);
		clear.setMaxWidth(Double.MAX_VALUE); 
		
		Button ny = new Button("New Sudoku");
		HBox.setHgrow(ny, Priority.ALWAYS);
		ny.setMaxWidth(Double.MAX_VALUE); 
		
		Button check = new Button("Check solution");
		HBox.setHgrow(check, Priority.ALWAYS);
		check.setMaxWidth(Double.MAX_VALUE); 
		
		hbox.getChildren().addAll(solve, clear, ny, check); 
		root.setBottom(hbox);  
		
		// Skapar en sudokulasare som ger nya sudokun
		SudokuReader sudoreader = new SudokuReader();
		
		//SKAPA ACTIONS TILL KNAPPAR  
		//Vad som händer när man trycker på solve
		solve.setOnAction(event -> {
			root.setTop(top); 
			int[][] solvedMatrix = new int[9][9]; 
			for (int i=0; i<9;i++) {
				for (int j=0; j<9; j++) {
					if (matrix[i][j].getText().equals("")) {
						solvedMatrix[i][j]=0; 
					} else {
					solvedMatrix [i][j] = Integer.parseInt(matrix[i][j].getText());
					}
				}
			}
			//Testa lösa sudokut
			Sudoku sudo = new Sudoku(solvedMatrix); 
			if(sudo.solver(0, 0)) {
				int finishedMatrix[][] = sudo.solve(); 
				for (int k=0; k<9; k++) {
					for (int m=0; m<9; m++) {
						matrix[k][m].setText(Integer.toString(finishedMatrix[k][m])); 
					}
				}
			} else { //Om det inte går att lösa så ska det komma ett felmeddelande
				Label label = new Label("Error message: NOT POSSIBLE TO SOLVE. TRY WITH NEW VALUES!");
				Toolkit.getDefaultToolkit().beep();
				root.setTop(label);
			}
			}); 
		
		//Vad som händer när man trycker på clear
		clear.setOnAction(event -> {
			root.setTop(top); 
			for (int i=0; i<9;i++) {
				for (int j=0; j<9; j++) {
					matrix[i][j].setText("");;
				}
			}
			}); 
		
		//Vad som hander nar man trycker på "New Sudoku"
		ny.setOnAction(event -> {
			int [][] newMatrix = sudoreader.newSudoku();
			for (int k=0; k<9; k++){
				for (int m=0; m<9; m++) {
					if (newMatrix[k][m] == 0){
						matrix[k][m].setText("");
					}else{
					matrix[k][m].setText(Integer.toString(newMatrix[k][m])); 
				}}
			}
			}); 
		
		//Klicka check ok en kopia av textmatris skapas
				check.setOnAction(event -> {
					root.setTop(top); 
					int[][] solvedMatrix = new int[9][9]; 
					for (int i=0; i<9;i++) {
						for (int j=0; j<9; j++) {
							if (matrix[i][j].getText().equals("")) {
								solvedMatrix[i][j]=0; 
							} else {
							solvedMatrix [i][j] = Integer.parseInt(matrix[i][j].getText());
							}
						}
					}
					// Matrisen kollas och ger felmeddelande
					for (int i=0; i<9;i++) {
						for (int j=0; j<9; j++) {
							if (solvedMatrix[i][j] == 0) {
								Label label = new Label("Your sudoku is not completed. Try again!");
								root.setTop(label);
								return;
							}
								
						}
								
					}
					Sudoku sudo = new Sudoku(solvedMatrix);
					if (sudo.solver(0, 0)) {
						Label label = new Label("Success!");
						Toolkit.getDefaultToolkit().beep();
						root.setTop(label);
					}else {
						Label label = new Label("Your sudoku contains a major error. Try again!");
						root.setTop(label);
					}
				});			
	}
		

	public static void main(String[] args) {
		Application.launch(args);
	}
}
