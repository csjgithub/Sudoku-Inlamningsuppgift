import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
		top.setPrefHeight(50);
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
	    		matrix[i][j] = new TextField();
	    		matrix[i][j].setPrefWidth(50);
	    		matrix[i][j].setPrefHeight(50);
	    		matrix[i][j].setAlignment(Pos.CENTER);
	    		tilepane.getChildren().add(matrix[i][j]);
	    	}
	    }
		
		//Skapa en HBox med två buttons "Solve" och "Clear"
		HBox hbox = new HBox(); 
		Button btn1 = new Button("Solve");		
		HBox.setHgrow(btn1, Priority.ALWAYS);
		btn1.setMaxWidth(Double.MAX_VALUE); 
		
		Button btn2 = new Button("Clear");
		HBox.setHgrow(btn2, Priority.ALWAYS);
		btn2.setMaxWidth(Double.MAX_VALUE); 
		
		hbox.getChildren().addAll(btn1, btn2); 
		root.setBottom(hbox);  
		
		//Gammal kod från BookReaderController som kan hjälpa oss med actions som sker när buttons aktiveras. 
		
		//D7
//		btn1.setOnAction(event -> {
//			System.out.println("hej");
//			}); 
		
//		//Ordnar listan i bokstavsordning
//		btn1.setOnAction(event -> {
//			words.sort((e1,e2) -> e1.getKey().compareTo(e2.getKey()));
//			}); 

		
//		//Scrollar till det sökta ordet om det finns i listan 
//		btn3.setOnAction(event-> {
//			String searchWord = find.getText(); 
//			for (int i=0; i<words.size(); i++) {
//				if (words.get(i).getKey().equals(searchWord)) {
//					listView.scrollTo(i);
//				}
//			}
//		});
//		btn3.setDefaultButton(true);
		
	}
		

	public static void main(String[] args) {
		Application.launch(args);
	}
}
