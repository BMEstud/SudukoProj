
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class SetUpGUI {

	private BorderPane borderPane;
	private TilePane tilePane;
	final int SIZE = 50;
	private int count = 0;
	final int NBR_ROW = 9;
	final int NBR_COL = 9;
	private int[][] GRID_TO_SOLVE;

	/**
	 * The method is setting up the GUI, formatting the tilepane with textfields,
	 * and puts in the values from the matrix (param)
	 *
	 * @param GRID_TO_SOLVE, A matrix which corresponds to the text in the textfield
	 *        which the users sees
	 **/
	public SetUpGUI(int[][] GRID_TO_SOLVE) {

		this.GRID_TO_SOLVE = GRID_TO_SOLVE;

		// Container-elements
		this.borderPane = new BorderPane();
		this.tilePane = new TilePane();

		int c = 0;
		int r = 0;

		for (int i = 0; i < NBR_COL * NBR_ROW; i++) {

			// Creates 9*9 textfield object and sets their size to SIZE*SIZE. The textfields
			// are added to the tilePane
			TextField tf = new TextField();
			tf.setPrefSize(SIZE, SIZE);
			tilePane.getChildren().addAll(tf);
			tf.setFont(Font.font("Verdana", FontWeight.NORMAL, 20));

			count++;

			// Setting some space between every textfield boxs
			tilePane.setPadding(new Insets(1, 1, 1, 1));
			tilePane.setHgap(2);
			tilePane.setVgap(2);

			// takes a number from the matrix, convert it to a String and add the string to
			// the textfield object
			int s = GRID_TO_SOLVE[r][c];
			String str = Integer.toString(s); // from int --> String
			tf.setText(str);

			// If the matrix consists of zeroes (example empty matrix) we replace the zeroes
			// with nothing, so (" ")
			// the user dont see any number in the suduko grid
			if (s == 0) {
				tf.setText("");
			}

			c++;

			if (c == 9) {
				c = 0;
				r++;
			}

			if (!tf.getText().equals("")) {

				tf.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
			}

			// These if-statements are creating the pink boxes..
			if (count < 4) {
				tf.setStyle("-fx-background-color:  pink;");
			}

			if (count > 6 && count < 13) {
				tf.setStyle("-fx-background-color:  pink;");
			}

			if (count > 15 && count < 22) {
				tf.setStyle("-fx-background-color:  pink;");
			}
			if (count > 24 && count < 28) {
				tf.setStyle("-fx-background-color:  pink;");
			}

			// ---------------------------------------------------

			if (count > 30 && count < 34) {
				tf.setStyle("-fx-background-color:  pink;");
			}

			if (count > 39 && count < 43) {
				tf.setStyle("-fx-background-color:  pink;");
			}

			if (count > 48 && count < 52) {
				tf.setStyle("-fx-background-color:  pink;");
			}

			// ---------------------------------------------------

			if (count > 54 && i < 57) {
				tf.setStyle("-fx-background-color:  pink;");
			}

			if (count > 60 && count < 67) {
				tf.setStyle("-fx-background-color:  pink;");
			}

			if (count > 69 && count < 76) {
				tf.setStyle("-fx-background-color:  pink;");
			}
			if (count > 78 && count < 82) {
				tf.setStyle("-fx-background-color:  pink;");
			}
			borderPane.setCenter(tilePane);

		}

	}

	/**
	 * The method resets the board, i.e matrix by replacing all numbers by 0
	 * 
	 * @param int[][], the matrix consisted of 9*9 fields
	 **/

	public void clearBoard(int[][] GRID_TO_SOLVE) {

		this.GRID_TO_SOLVE = GRID_TO_SOLVE;

		for (int row = 0; row < NBR_ROW; row++) {
			for (int col = 0; col < NBR_COL; col++) {

				GRID_TO_SOLVE[row][col] = 0;

			}
		}

	}

	/**
	 * The method scans the tilePane for inputs (textFields) and replaces every ""
	 * with zeros. It then fills the matrix GRID_TO_SOLVE with the values.
	 * 
	 * The method also calls the checkInput-method to check if the inputs are
	 * satisfied by the input constraints, i.e integers between 1-9. If the method
	 * finds any character not allowed, the user will get an information message
	 * 
	 * @param TilePane, the tilePane consisting of textFields
	 * @return An alert message box
	 */

	public void readTile(TilePane tilePane) {

		int row = 0;
		int col = 0;

		for (Node node : getTilePane().getChildren()) {

			if (checkInput(getTilePane()) != true) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Endast siffror");
				alert.setHeaderText(null);
				alert.setContentText("Endast siffror mellan 1-9 tillåtna");
				alert.showAndWait();
				return;

			}

			if (((TextField) node).getText().equals("")) {

				// the empty cells are set to 0
				((TextField) node).setText("0");
			}

			// The matrix get filled up with values from the textfields
			this.GRID_TO_SOLVE[row][col] = Integer.parseInt(((TextField) node).getText());

			col++;

			if (col == 9) {
				col = 0;
				row++;
			}

		}

	}

	/**
	 * The method checks if the inputs are satisfied by the input constraints, i.e
	 * integers between 1-9. If the method finds any character not allowed, the
	 * method will return false, else true.
	 * 
	 * @param TilePane, the tilePane consisted of 9*9 textFields
	 * @return true if the textfields only consists of integers between 1-9
	 * @return false, if any non integers between 1-9 are detected
	 */
	public boolean checkInput(TilePane tilePane) {

		// Loop through all textfields in the tilepane
		for (Node node : getTilePane().getChildren()) {

			// If the textfields conatins a letter or a number with more then 1 digit,return
			// false
			if (((TextField) node).getText().matches("[A-Za-ö]") || !((TextField) node).getText().matches("\\d{0,1}")) {

				return false;

			}

		}

		return true;

	}

	/**
	 * The method seeks for the characters either, 0 or a-ö and replaces the
	 * character by " ". The method reset the tilepane, i.e the textfields
	 * 
	 */
	public void resetTile() {

		for (Node node : getTilePane().getChildren()) {

			if (((TextField) node).getText().equals("0") || ((TextField) node).getText().matches("[A-Za-ö]")) {

				// the cells that contains no number are set to 0
				((TextField) node).setText("");

			}

		}

	}

	/**
	 * the method returns the bordepane
	 *
	 * @return borderpane
	 **/
	public BorderPane getBorderPane() {
		return this.borderPane;
	}

	/**
	 * the method returns the tilepane
	 *
	 * @return tilepane
	 **/
	public TilePane getTilePane() {
		return this.tilePane;
	}

	/**
	 * the method returns the matrix
	 *
	 * @return GRID_TO_SOLVE
	 **/
	public int[][] getBoard() {

		return this.GRID_TO_SOLVE;
	}

}
