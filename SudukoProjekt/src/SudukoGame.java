
import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class SudukoGame extends Application {

	private int NBR_COL = 9;
	private int NBR_ROW = 9;
	final int SIZE = 50; // textfield size, final i.e the size is set only once
	private SetUpGUI grid;
	private Stage stage;
	private Solver solver;

	private int[][] GRID_TO_SOLVE = { { 7, 0, 0, 1, 0, 0, 0, 0, 5 }, { 0, 0, 5, 0, 9, 0, 2, 0, 1 },
			{ 8, 0, 0, 0, 4, 0, 0, 0, 0 }, { 0, 0, 0, 0, 8, 0, 0, 0, 0 }, { 0, 0, 0, 7, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 2, 6, 0, 0, 9 }, { 2, 0, 0, 3, 0, 0, 0, 0, 6 }, { 0, 0, 0, 2, 0, 0, 9, 0, 0 },
			{ 0, 0, 1, 9, 0, 4, 5, 7, 0 }, };

	private int[][] EMPTY_GRID = { { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, };

	@Override
	public void start(Stage stage) throws Exception {

		this.stage = stage;

		grid = new SetUpGUI(GRID_TO_SOLVE);

		HBox hb = new HBox();
		Button clear = new Button("Clear");
		Button solve = new Button("Solve");
		hb.setSpacing(20);
		hb.getChildren().addAll(clear, solve);

		grid.getBorderPane().setCenter(grid.getTilePane());
		grid.getBorderPane().setBottom(hb);

		solve.setOnAction(event -> {

			if (!grid.checkInput(grid.getTilePane())) {

				// if we have a letter, the GRID_TO_SOLVE will not change
				grid.readTile(grid.getTilePane());
				grid.resetTile(); // we reset the tilepane, any letters in the textfields becomes a ""
				grid = new SetUpGUI(grid.getBoard());
				grid.getBorderPane().setCenter(grid.getTilePane());
				grid.getBorderPane().setBottom(hb);
				setStage2(grid.getBorderPane()).show();
				return;

			}

			// The inputed values in the textfields are read off and put into the matrix
			// GRID_TO_SOLVE in the class SetUpGUI. GRID_TO_SOLVE gets updated
			grid.readTile(grid.getTilePane());

			// To pass the grid between the two classes.. The read off matrix is now passed
			// on to the Solver-class
			solver = new Solver(grid.getBoard());

			// to erase all "0" and letters, zeros and letters are replaced with " ", just
			// the GUI, what we see.
			grid.resetTile();

			// When we use the solve method,the matrix EMPTY_GRID will change..
			if (solver.checkIfSolved() == false) {

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Sudukot går ej att lösa");
				alert.setHeaderText(null);
				alert.setContentText("Det saknas lösning");
				alert.showAndWait();

				grid = new SetUpGUI(grid.getBoard());
				grid.getBorderPane().setCenter(grid.getTilePane());
				grid.getBorderPane().setBottom(hb);
				setStage2(grid.getBorderPane()).show();

			} else { // if the suduko can be solved

				grid = new SetUpGUI(grid.getBoard());
				grid.getBorderPane().setCenter(grid.getTilePane());
				grid.getBorderPane().setBottom(hb);
				setStage2(grid.getBorderPane()).show();

			}

		});

		clear.setOnAction(event ->

		{

			// When we use "clear" we will empty the board, i.e empty the textfields
			grid.clearBoard(grid.getBoard());

			grid = new SetUpGUI(grid.getBoard());

			grid.getBorderPane().setCenter(grid.getTilePane());
			grid.getBorderPane().setBottom(hb);

			setStage2(grid.getBorderPane()).show();
		});

		setStage(grid.getBorderPane()).show();

	}

	private Stage setStage(BorderPane borderPane) {

		Scene scene = new Scene(borderPane, (50 * NBR_COL + 10), SIZE * NBR_ROW + 50);
		stage.setTitle("Suduko");
		stage.setScene(scene);

		stage.setResizable(false);

		return stage;
	}

	private Stage setStage2(BorderPane borderPane) {

		Scene scene = new Scene(borderPane, (50 * NBR_COL + 22), SIZE * NBR_ROW + 62);
		stage.setTitle("Suduko");
		stage.setScene(scene);

		stage.setResizable(false);// To not be able to edit the size of the suduko

		return stage;
	}
	
	

	public static void main(String[] args) {

		// The main method is only used to start the javaFx

		Application.launch(args);

	}
}
