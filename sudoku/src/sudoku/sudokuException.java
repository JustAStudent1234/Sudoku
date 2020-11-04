package sudoku;

public class sudokuException extends Exception {

//eine eigentlich unnötige Exception Klasse :) 
	public sudokuException() {
		super("Das eingegebene Sudoku ist nicht lösbar");
	}

}
