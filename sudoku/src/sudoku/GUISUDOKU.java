package sudoku;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class GUISUDOKU

{

	// Frame 
	public JFrame frame;
	public JTextField[][] field_arr;
	public JButton SolveButton;
	public JButton ClearButton;
	public JButton CheckButton;
	public JButton L�sungenButton;
	JLabel label;
	JLabel label1;
	matrix matrix = new matrix(3);


	public GUISUDOKU() {
		field_arr = new JTextField[9][9];    //Die Gui basiert auf einer 9x9 Matrix
		setGUI();
	}

	// frame setzen mit Farben und Textfields
	void setGUI() {

		//Jedes relevante Abteil innerhalb des Sudokus mit durch Farben abgrenzen.
		Color[] col = { Color.cyan, Color.white, Color.cyan, Color.white, Color.cyan, Color.white, Color.cyan,
				Color.white, Color.cyan };

		
		frame = new JFrame("SUDOKU SOLVER");

		//die JTextFelder einrichten und an die gew�nschte Position setzen. 
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				field_arr[i][j] = new JTextField();
				field_arr[i][j].setBounds(50 + j * 40, 50 + i * 40, 30, 30);
				field_arr[i][j].setBackground(col[(i / 3) * 3 + (j / 3)]);
				Font font1 = new Font("SansSerif", Font.BOLD, 20);
				field_arr[i][j].setFont(font1);
				field_arr[i][j].setHorizontalAlignment(SwingConstants.CENTER);

				frame.add(field_arr[i][j]);
			}

			//nur um die Benutzer dar�ber aufzukl�ren, wie der "L�sungen" button funktioniert
			label = new JLabel("| Geben sie '?' in eine Zelle ein um sich die ");
			label.setBounds(100, 510, 300, 10);
			label1 = new JLabel("| entsprechenden L�sungen anzeigen zu lassen.");
			label1.setBounds(100, 521, 300, 10);

			// l�st das Sudoku
			SolveButton = new JButton("SOLVE");
			SolveButton.setBounds(150, 420, 80, 80);
			SolveButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {

					//das 2D Array einrichten, indem es die JTextField Eintr�ge als int speichert,
					//um es an in die Matrix zu kopieren. Nicht vorhandene Eintr�ge werden als 0 gewertet.
					
					int g[][] = new int[field_arr.length][field_arr.length];

					konvertieren(g);
					matrix.MatrixArray = g.clone();
					
					//Matrix wird gel�st oder erh�lt eine Fehlermeldung
					try {
						matrix.solving();
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, e, "Sudoku", JOptionPane.ERROR_MESSAGE);
						return;
					}

					//die Gui wird aktualisiert (h�tte man auch als Funktion auslagern k�nnen) 
					for (int i = 0; i < 9; i++) {
						for (int j = 0; j < 9; j++) {
							field_arr[i][j].setText("" + matrix.MatrixArray[i][j]);

						}
					}

				}
			});

			// l�scht alle Eingaben und setzt die Farbe zur�ck auf weis.
			ClearButton = new JButton("CLEAR");
			ClearButton.setBounds(250, 420, 80, 80);
			ClearButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					for (int i = 0; i < field_arr.length; i++) {
						for (int j = 0; j < field_arr.length; j++) {
							matrix.clear();
							field_arr[i][j].setText(null);

						}

					}
				}

			});

			// Wenn die Eingabe valide ist passiert nichts, wenn nicht kommt 
			// eine Fehlermeldung in Form einer JOptionPane Message.
			CheckButton = new JButton("CHECK");
			CheckButton.setBounds(50, 420, 80, 80);
			CheckButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {

					int g[][] = new int[field_arr.length][field_arr.length];

					konvertieren(g);
					matrix.MatrixArray = g.clone();

					try {
						matrix.solving();
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "Die eingegebene L�sung ist unzul�ssig", "Sudoku",
								JOptionPane.ERROR_MESSAGE);
						return;
					}

				}

			});

			// Gibt die Auswahl aller L�sungen f�r eine Zelle an, wenn in diese ein ? geschrieben wird. 

			L�sungenButton = new JButton("L�SUNGEN");
			L�sungenButton.setBounds(350, 420, 100, 80);
			L�sungenButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {

					ArrayList<Integer> L�sungsArray = new ArrayList<>();

					int g[][] = new int[field_arr.length][field_arr.length];

					konvertieren(g);
					matrix.MatrixArray = g.clone();

					//es wird ein identisches Array wie g erschaffen nur mit String anstatt Zahlen,
					//um zu sehen an welcher Stelle das ? eingegeben wurde.
					String h[][] = new String[field_arr.length][field_arr.length];

					for (int i = 0; i < field_arr.length; i++) {
						for (int j = 0; j < field_arr.length; j++) {
							try {
								h[i][j] = field_arr[i][j].getText();
							} catch (Exception e) {
								h[i][j] = "";

							}
						}
					}

					//Die L�sungen f�r die Zelle werden in einer JOptionPane Message als ein String array angezeigt 
					//oder es gibt eine Fehlermeldung. 
					
					for (int i = 0; i < g.length; i++) {
						for (int j = 0; j < g.length; j++) {
							if (h[i][j].equals("?")) {
								L�sungsArray = matrix.AlleL�sungen(i, j);
								if (!L�sungsArray.isEmpty()) {
									JOptionPane.showMessageDialog(null, L�sungsArray.toString(), "Sudoku",
											JOptionPane.INFORMATION_MESSAGE);
									return;
								} else {
									JOptionPane.showMessageDialog(null, "F�r das angefragte Feld gibt es keine L�sung",
											"Sudoku", JOptionPane.ERROR_MESSAGE);
									return;
								}
							}
						}
					}

				}

			});

			// button/label hinzuf�gen
			frame.add(SolveButton);
			frame.add(ClearButton);
			frame.add(CheckButton);
			frame.add(L�sungenButton);
			frame.add(label);
			frame.add(label1);
		}

		frame.setSize(500, 600);

		center();
		frame.setLayout(null);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	// zentrieren des frames
	void center() {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dimension.width / 2 - frame.getWidth() / 2, dimension.height / 2 - frame.getHeight() / 2);
		frame.setSize(500, 600);
	}
	
	
	//JTextField[][] in int[][] konvertieren.
	private void konvertieren(int[][] g){
		
		for (int i = 0; i < field_arr.length; i++) {
			for (int j = 0; j < field_arr.length; j++) {
				try {
					g[i][j] = Integer.parseInt(field_arr[i][j].getText());
				} catch (Exception e) {
					g[i][j] = 0;

				}
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
