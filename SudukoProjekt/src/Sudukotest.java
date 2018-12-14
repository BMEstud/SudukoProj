import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Sudukotest {
	
	private Solver solver;
	private SudukoGame gui;
	private int[][] EMPTY_GRID = { { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, };
	
	private int[][] UNSOLVABLE_GRID = { { 5, 0, 0, 5, 0, 0, 0, 1, 0 }, { 2, 7, 8, 0, 0, 0, 4, 0, 0 },
			{ 0, 0, 0, 0, 0, 1, 0, 0, 0 }, { 0, 2, 0, 0, 0, 0, 0, 9, 0 }, { 0, 0, 0, 0, 4, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, };
	
	private int[][] SOLVABLE_GRID = { { 9, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, };
	
	private int[][] SOLVED_GRID = { { 1, 2, 3, 4, 5, 6, 7, 8, 9 }, { 4, 5, 6, 7, 8, 9, 1, 2, 3 },
			{ 7, 8, 9, 1, 2, 3, 4, 5, 6 }, {2, 1, 4, 3, 6, 5, 8, 9, 7 }, {3, 6, 5, 8, 9, 7, 2, 1, 4 },
			{ 8, 9, 7, 2, 1, 4, 3, 6, 5 }, { 5, 3, 1, 6, 4, 2, 9, 7, 8 }, { 6, 4, 2, 9, 7, 8, 5, 3, 1 },
			{ 9, 7, 8, 5, 3, 1, 6, 4, 2 }, };

	@BeforeEach
	void setUp() throws Exception {
		 gui = new SudukoGame();
	}


	@Test
	void testUnsolvableGrid() {

		Solver solver = new Solver(UNSOLVABLE_GRID);

		assertFalse(solver.checkIfSolved());

	}

	@Test
	void testEmptyGrid() {
		Solver solver = new Solver(EMPTY_GRID);
		assertTrue(solver.checkIfSolved());
	}

	@Test
	void testSolvableGrid() {
		Solver solver = new Solver(SOLVABLE_GRID);
		assertTrue(solver.checkIfSolved());
	}

}
