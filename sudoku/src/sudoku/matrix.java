package sudoku;

import java.util.ArrayList;

public class matrix {

	int[] liste;	  //ist ein array mit den Zahlen 1-9, also alle Zahlen die zulässig sind in dem Sudoku.
	int[] Hilfsliste; //identische zu der liste, nur dafür da um die liste zurücksetzen und vergleichen zu können
	int[][] MatrixArray;				//Ein 2d Array, welches die Matrix beschreibt
	int d;								//Die Wurzel der Dimension.
	int Dimension;						//Dimension der Matrix, im Falle der Gui 9
	boolean hilfewurdeausgeführt;		//true wenn die Hilfsfunktion für solving durchgegangen ist.
	int AnzahlBackTracking;				//Wurde nicht in der Gui implementiert, kann man aber ganz einfach noch hinzufügen.
	

	//die Variablen werden wie oben erwähnt initialisiert.
	public matrix(int d) {
		
		Dimension = (int) Math.pow(d, 2);
		MatrixArray = new int[Dimension][Dimension];
		liste = new int[Dimension];
		Hilfsliste = new int[Dimension];
		this.d = d;
		for (int i = 0; i < Dimension; i++) {
			liste[i] = i + 1;
			Hilfsliste[i] = i + 1;
		}
		hilfewurdeausgeführt = false;
		AnzahlBackTracking = 0;
	}

	// true wenn das sudoku gelöst ist
	public boolean issolved() {
		
		
		//Schaut ob alle Zahlen in den Teilabschnitten des Sudokus auch nur einmal vorkommen.
		//Wahrscheinlich gibt es einen effizienteren Weg den Algorithmus auch in O(n^2) nicht in O(n^4)
		//zu schreiben, den ich in Zukunft eventeull noch verbessern werde.
		for (int m = 0; m < Dimension; m = m + d) {
			for (int n = 0; n < Dimension; n = n + d) {

				for (int i = m; i < m + d; i++) {
					for (int j = n; j < n + d; j++) {
						if (contains(liste, MatrixArray[i][j])) {

							if (contains(Hilfsliste, MatrixArray[i][j])) {
								überschreiben(Hilfsliste, MatrixArray[i][j]);
							} else {

								Hilfsliste = liste.clone();
								return false;
							}
						}
					}
				}

				if (!consistsof(Hilfsliste)) {
					Hilfsliste = liste.clone();
					return false;
				} else {
					Hilfsliste = liste.clone();
				}
			}
		}

		
		
	//geht die Zeilen durch und schaut ob die Sudoku-Eigenschaften erhalten sind.	
		for (int i = 0; i < Dimension; i++) {
			for (int j = 0; j < Dimension; j++) {

				if (contains(liste, MatrixArray[i][j])) {

					if (contains(Hilfsliste, MatrixArray[i][j])) {
						überschreiben(Hilfsliste, MatrixArray[i][j]);
					} else {

						Hilfsliste = liste.clone();
						return false;
					}
				} else {
					return false;
				}
			}
			if (!consistsof(Hilfsliste)) {
				Hilfsliste = liste.clone();
				return false;
			} else {
				Hilfsliste = liste.clone();
			}
		}

		//geht die Spalten durch und schaut ob die Sudoku-Eigenschaften erhalten sind.
		//hätte man auch in Funktionen auslagern können, aber so ist es übersichtlicher, wegen Zeile/Spalte
		for (int j = 0; j < Dimension; j++) {
			for (int i = 0; i < Dimension; i++) {

				if (contains(liste, MatrixArray[i][j])) {

					if (contains(Hilfsliste, MatrixArray[i][j])) {
						überschreiben(Hilfsliste, MatrixArray[i][j]);
					} else {

						Hilfsliste = liste.clone();
						return false;
					}
				} else {
					return false;
				}
			}
			if (!consistsof(Hilfsliste)) {
				Hilfsliste = liste.clone();
				return false;
			} else {
				Hilfsliste = liste.clone();
			}
		}

		return true;
	}

	// true wenn regeln des aktuellen sudokus nicht verletzt sind, 
	//ein freies Feld zählt anders wie bei issolved nicht als Verletzung
	public boolean RegelnNichtVerletzt() {

		//schaut nach ob die regeln schon verletzt sind bei den Teilbereichen im Sudoku, 
		//aber ein freies Feld zählt anders wie oben nicht als Verletzung!
		for (int m = 0; m < Dimension; m = m + d) {
			for (int n = 0; n < Dimension; n = n + d) {
				for (int i = m; i < m + d; i++) {
					for (int j = n; j < n + d; j++) {

						if (!(MatrixArray[i][j] == 0)) {
							if (contains(liste, MatrixArray[i][j])) {

								if (contains(Hilfsliste, MatrixArray[i][j])) {
									überschreiben(Hilfsliste, MatrixArray[i][j]);
								} else {

									Hilfsliste = liste.clone();
									return false;
								}
							} else {
								Hilfsliste = liste.clone();
								return false;
							}
						} else {
							continue;
						}
					}
				}

				Hilfsliste = liste.clone();

			}
		}

		//schaut nach ob in den Zeilen die Regeln verletzt sind.
		for (int i = 0; i < Dimension; i++) {
			Hilfsliste = liste.clone();
			for (int j = 0; j < Dimension; j++) {

				if (MatrixArray[i][j] == 0) {
					continue;
				}
				if (contains(Hilfsliste, MatrixArray[i][j])) {
					überschreiben(Hilfsliste, MatrixArray[i][j]);
				} else {

					Hilfsliste = liste.clone();
					return false;
				}

			}
		}

		//schaut nach ob in den Spalten die Regeln verletzt sind.
		for (int j = 0; j < Dimension; j++) {
			Hilfsliste = liste.clone();
			for (int i = 0; i < Dimension; i++) {

				if (MatrixArray[i][j] == 0) {
					continue;
				}
				if (contains(Hilfsliste, MatrixArray[i][j])) {
					überschreiben(Hilfsliste, MatrixArray[i][j]);
				} else {

					Hilfsliste = liste.clone();
					return false;
				}

			}
		}

		return true;
	}

//löst das sudoku falls machbar oder wirft einen Fehler. 
	public void solving() throws sudokuException {

		if (!this.RegelnNichtVerletzt()) {
			sudokuException e = new sudokuException();
			throw e;
		}

		for (int i = 0; i < Dimension; i++) {

			for (int j = 0; j < Dimension; j++) {

				if (MatrixArray[i][j] == 0) {

					for (int l = 1; l < Dimension + 1; l++) {

						if (this.issolved()) {
							return;
						}

						MatrixArray = hilfe(l, MatrixArray, i, j, MatrixArray[i][j]).clone();

						if (hilfewurdeausgeführt == false && l == Dimension) {
							AnzahlBackTracking++;
							MatrixArray[i][j] = 0;
							return;
						}

						if (hilfewurdeausgeführt == true) {
							this.solving();
						} else
							continue;

					}
				}

				else
					continue;
			}
		}
	}

//	 gibt die matrix zurück mit b[i][j]= l, wenn es die regeln nicht verletzt, ansonsten nur b.	
	public int[][] hilfe(int l, int[][] o, int i, int j, int u) {

		matrix p = new matrix(d);
		int[][] result = new int[Dimension][Dimension];
		result = o.clone();

		result[i][j] = l;
		p.MatrixArray = result.clone();

		if (p.RegelnNichtVerletzt()) {

			hilfewurdeausgeführt = true;
			return result;
		} else {
			hilfewurdeausgeführt = false;
			o[i][j] = u;
			return o;
		}

	}

	// löscht die Zellen der Matrix

	public void clear() {
		matrix matrix = new matrix(this.d);
		copy(matrix);
	}

	//Überschreibt eine Matrix mit der Eingabe
	public void copy2(matrix matrix) {
		for (int i = 0; i < Dimension; i++) {
			for (int j = 0; j < Dimension; j++) {
				MatrixArray[i][j] = matrix.MatrixArray[i][j];
			}
		}
	}

	//Kopiert alle Einzelteile der eingegebenen Matrix in das Objekt
	public void copy(matrix matrix) {
		this.liste = matrix.liste;
		this.AnzahlBackTracking = matrix.AnzahlBackTracking;
		this.Hilfsliste = matrix.Hilfsliste;
		this.MatrixArray = matrix.MatrixArray;
		this.hilfewurdeausgeführt = matrix.hilfewurdeausgeführt;
	}

	// gibt in einer ArrayList alle möglichen Lösungen für die Zelle (i,j) an.
	public ArrayList<Integer> AlleLösungen(int i, int j) {

		ArrayList<Integer> Lösung = new ArrayList<>();
		for (int o = 1; o < Dimension + 1; o++) {
			matrix matrix = new matrix(d);
			matrix.copy2(this);

			matrix.MatrixArray[i][j] = o;

			try {
				matrix.solving();
			} catch (Exception e) {
				continue;
			}
			Lösung.add(o);
		}
		return Lösung;

	}

	//true wenn das array a die Zahl b enthält.
	private boolean contains(int[] a, int b) {
		for (int i = 0; i < a.length; i++) {
			if (a[i] == b) {
				return true;
			}
		}
		return false;
	}
	
//überschreibt die Zahl b im array a mit -1
	private void überschreiben(int[] a, int b) {
		for (int i = 0; i < a.length; i++) {
			if (a[i] == b) {
				a[i] = -1;
			}
		}
	}
	
//gibt true aus wenn das array a noch -1er enthält
	private boolean consistsof(int[] a) {
		for (int i = 0; i < a.length; i++) {
			if (!(a[i] == -1)) {
				return false;
			}
		}
		return true;
	}

	//platziert die Zahl a bei der erst besten freien Stelle im Array.
	public void add(int a) {
		for (int i = 0; i < Dimension; i++) {
			for (int j = 0; j < Dimension; j++) {
				if (MatrixArray[i][j] == 0) {
					MatrixArray[i][j] = a;
					return;
				}
			}
		}

	}

}
