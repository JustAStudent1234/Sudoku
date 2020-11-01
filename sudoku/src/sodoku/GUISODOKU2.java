package sodoku;




import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;
import javax.swing.text.Position;
public class GUISODOKU2 
{

 //Frame Components
 public JFrame frame;
 public JTextField[][] field_arr;
 public JButton jbutton;
 matrix matrix = new matrix(3);
 
 
 
 
 
 //Constructor
 GUISODOKU2()
 {
  field_arr = new JTextField[9][9];        //Initializing the textfield array
  setGUI();
  
 }
 
 //Setting all the frame and setting all the colours
 void setGUI()
 {
  //Colors array to set to each block of 3*3
  Color[] col = {Color.cyan,Color.white,Color.cyan,Color.white,Color.cyan,Color.white,
		  Color.cyan,Color.white,Color.cyan};
  
 
  frame = new JFrame("SUDOKU SOLVER");  //Creating frame
  
  for(int i=0;i<9;i++)
  {
   for(int j=0;j<9;j++)
   {
    field_arr[i][j] = new JTextField();
    field_arr[i][j].setBounds(50+j*40, 50+i*40, 30, 30);      //Positioning each textfield
    field_arr[i][j].setBackground(col[(i/3)*3+(j/3)]);        //Setting colour to block from array
    Font font1 = new Font("SansSerif", Font.BOLD, 20);        //Setting font to each textfield
    field_arr[i][j].setFont(font1);                              
    field_arr[i][j].setHorizontalAlignment(SwingConstants.CENTER);    //Setting alignment of text in textfield
    
    frame.add(field_arr[i][j]);                      //adding textfield to frame
   }
   
   //Creating and adding the Solve JButton
   jbutton = new JButton("SOLVE");                  
   jbutton.setBounds(200,450,80,80);
   jbutton.addActionListener(new ActionListener() {
    
    @Override
    public void actionPerformed(ActionEvent arg0) {
     
     //Start method present in background_code.java
  
    	int g[][]= new int[field_arr.length][field_arr.length];
  
    	for(int i=0; i<field_arr.length;i++) {
    		for(int j=0; j<field_arr.length; j++) {
    			try {
    		 	g[i][j]=Integer.parseInt(field_arr[i][j].getText());
    			}catch(Exception e) {
    				g[i][j]=0;
    			
    			}
    		}
    	}
    	matrix.b=g.clone();
    	try {
    	matrix.solving();
    	}catch(Exception e) {
    		JOptionPane.showMessageDialog(null,e , "Sodoku",JOptionPane.ERROR_MESSAGE);
    		return;
    	}
    
    	  
    	  
    	  
    	  for(int i=0;i<9;i++)
    	  {
    	   for(int j=0;j<9;j++)
    	   {
    		field_arr[i][j].setText(""+matrix.b[i][j]);
    	    
    	   }
    	   }
    	 
    	 	
    	
    }
   });
   
   
   //Adding the button
   frame.add(jbutton);
   
  }
 
  
  
  //Setting the size of frame
  frame.setSize(500, 600);
  //Centering the frame
  center();
  frame.setLayout(null);
  frame.setResizable(false);
  frame.setVisible(true);

 }
 
 //Method to return the element present in particular box if not return 0
 int get_element(int i,int j)
 {
  String txt = field_arr[i][j].getText();
  if(txt.isEmpty())
   
   return 0;
  
  return Integer.parseInt(txt.trim());
 }
 
 
 //Method to set element in particular box
 void set_element(int i,int j,int val)
 {
  field_arr[i][j].setText(String.valueOf(val));
 }
 
 //Method to clear the box
 void clear()
 {
  for(int i=0;i<9;i++)
  {
   for(int j=0;j<9;j++)
   {
    field_arr[i][j].setText("");
   }
  }
   
 }
 
 //Method to center the frame
 void center()
 {
  //Taking the dimensions of present screen
  Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
  frame.setLocation(dimension.width/2 -frame.getWidth()/2 ,dimension.height/2 - frame.getHeight()/2);
  frame.setSize(500, 600);
 }
 
}

