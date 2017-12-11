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
		
		//Skapa 9x9 TextFields i en matris och lägg till dessa till som children till vår TilePane som är i mitten av 
		//vår BorderPane (den enda som vi egentligen använder). 
		//Matrisen är nice för där kan vi sedan loopa matrix[i][j].getText() för att hämta siffrorna som skrivs in.
		//Vi får alltså en koppling till varje TextField. Om alla TextFields bara skapas i loopen utan att läggas in 
		//i matrisen så får vi 81 TextFields med samma namn --> alltså svårt att anropa de olika när vi ska hämta siffor. 
		//Problemet här är dock att våra 81 TextFields inte kommer se ut som en 9x9 matris när vi kör programmet, de kommer
		//bara läggas efter varandra så mycket det går beroende på hur stort fönstret är. Hitta gärna något kommando som 
		//kan begränsa varje rad till 9 TextFields så att det ser ut som ett sudoku. 
		TextField[][] matrix = new TextField[9][9];

	    for (int i = 0; i <9; i++) {
	    	for (int j=0; j<9; j++) {
	    		matrix[i][j] = new OneLetterTextField();
	    		matrix[i][j].setPrefWidth(50);
	    		matrix[i][j].setPrefHeight(50);
	    		matrix[i][j].setAlignment(Pos.CENTER);
	    		matrix[i][j].setVisible(true);
	    		matrix[i][j].setEditable(true);
	    		matrix[i][j].setText("0");
	    		tilepane.getChildren().add(matrix[i][j]);
	    	}
	    }
		
		//Skapa en HBox med två buttons "Solve" och "Clear"
		HBox hbox = new HBox(); 
		Button solve = new Button("Solve");		
		HBox.setHgrow(solve, Priority.ALWAYS);
		solve.setMaxWidth(Double.MAX_VALUE); 
		
		Button clear = new Button("Clear");
		HBox.setHgrow(clear, Priority.ALWAYS);
		clear.setMaxWidth(Double.MAX_VALUE); 
		
		hbox.getChildren().addAll(solve, clear); 
		root.setBottom(hbox);  
		

		//Vad som händer när man trycker på solve
		solve.setOnAction(event -> {
			root.setTop(top); 
			int[][] solvedMatrix = new int[9][9]; 
			for (int i=0; i<9;i++) {
				for (int j=0; j<9; j++) {
					solvedMatrix [i][j] = Integer.parseInt(matrix[i][j].getText());
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
					matrix[i][j].setText("0");;
				}
			}
			}); 
	}
		

	public static void main(String[] args) {
		Application.launch(args);
	}
}
