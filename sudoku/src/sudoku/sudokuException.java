package sudoku;

public class sudokuException extends Exception {

//eine eigentlich unn�tige Exception Klasse :) 
	public sudokuException() {
		super("Das eingegebene Sudoku ist nicht l�sbar");
	}

}
