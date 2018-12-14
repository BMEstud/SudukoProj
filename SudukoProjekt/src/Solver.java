
public class Solver {

	// final int SIZE = 50;

	final int NBR_ROW = 9;
	final int NBR_COL = 9;
	private static final int EMPTY = 0;
	private int[][] GRID_TO_SOLVE;

	/**
	 * the method checks if a given number is in a specific row
	 * 
	 * @param row    Of type int which corresponds to the row number in which a
	 *               specific number (second param) will be searched for
	 * @param number Of type int. The number the method will seek for
	 * @return true If the number can be fond
	 * @return false If the number can not be found
	 **/

	//

	public Solver(int[][] GRID) {
		this.GRID_TO_SOLVE = GRID;
		solve(0,0);

	}

	private boolean isInRow(int row, int number) {

		for (int i = 0; i < NBR_ROW; i++) {

			if (GRID_TO_SOLVE[row][i] == number) {
				return true;

			}

		}

		return false;
	}

	/**
	 * the method checks if a given number is in a specific column
	 * 
	 * @param col    Of type int which corresponds to the row number in which a
	 *               specific number (second param) will be searched for
	 * @param number Of type int. The number the method will seek for
	 * @return true If the number can be found
	 * @return false If the number can not be found
	 **/
	private boolean isInCol(int col, int number) {

		for (int i = 0; i < NBR_COL; i++) {

			if (GRID_TO_SOLVE[i][col] == number) {
				return true;
			}
		}

		return false;
	}

	/**
	 * the method checks if a given number is in a box (3*3 field)
	 * 
	 * @param row    Of type int which corresponds to the row number in which a
	 *               specific number will be searched for
	 * @param col    Of type int which corresponds to the column in which a the num
	 *               param will be searched for
	 * @param number Of type int. The number the method will seek for
	 * @return true If the number can be fond
	 * @return false If the number can not be found
	 **/
	private boolean isInBox(int row, int col, int number) {
		int r = row - row % 3;
		int c = col - col % 3;

		for (int i = r; i < r + 3; i++) {
			for (int j = c; j < c + 3; j++) {
				if (GRID_TO_SOLVE[i][j] == number) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * the method checks if a given number is
	 * 
	 * @param row    Of type int which corresponds to the row number in which a
	 *               specific number will be searched for
	 * @param col    Of tyoe int which corrsponds to the column number in which the
	 *               specifi number will be searched for
	 * @param number Of type int. The number the method will seek for
	 * @return true If the number can not be found in the given row, column or a box
	 * @return false f the number can be found in the given row, column or a box
	 **/
	private boolean isOk(int row, int col, int number) {
		return !isInRow(row, number) && !isInCol(col, number) && !isInBox(row, col, number);
	}

	/**
	 * The method solves the suduko recurvisely with backtracking method
	 * 
	 * @param i Of type int which corresponds to the x coordinate where the method
	 *          will begin
	 * @param j Of type int which corrsponds to the y coordinate
	 * @return true If the suduko can be solved
	 * @return false If the suduko can not be solved
	 **/
	private boolean solve(int i, int j) {

		if (j == 9) // change row
		{
			j = 0;
			i++;
		}

		if (i == 9) { // base-case

			return true;

		}
		// There is no number in the cell
		if (GRID_TO_SOLVE[i][j] == EMPTY) {
			// we try possible numbers
			for (int number = 1; number <= 9; number++) {
				if (isOk(i, j, number)) {
					// number ok. it respects sudoku constraints, we fill the cell with a number
					GRID_TO_SOLVE[i][j] = number;

					if (solve(i, j + 1)) { // we start backtracking recursively
						return true;
					} else { // if not a solution, we empty the cell and we continue
						GRID_TO_SOLVE[i][j] = EMPTY;
					}
				}
			}

			return false; // we return false

			// If cell is not empty
		} else {

			// Temporarily picks away a value, and puts it back later, no matter if it is
			// true or false
			int nbr = GRID_TO_SOLVE[i][j];
			GRID_TO_SOLVE[i][j] = EMPTY;

			if (isOk(i, j, nbr)) {

				GRID_TO_SOLVE[i][j] = nbr;

				return solve(i, j + 1);

			}
			GRID_TO_SOLVE[i][j] = nbr;

		}

		// if the number is not satisfying the condi
		return false;

	}
	
	public boolean checkIfSolved() {
		
		if(solve(0,0) == true) {
			return true;
		}
		
		return false;
		
		
	}

}
