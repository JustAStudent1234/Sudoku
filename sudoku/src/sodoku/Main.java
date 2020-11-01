package sodoku;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		

		
		
	
//	String z="";
						
				
		matrix a = new matrix(3);
        a.b[0][0]=3;
        a.b[0][5]=1;
//		a.b[1][0]=2;
//		a.b[2][0]=3;
//		a.b[3][0]=4;
		a.b[0][1]=2;
//		a.b[1][1]=3;
//		a.b[2][1]=1;
//		a.b[3][1]=4;
//		a.b[0][2]=2;
//		a.b[1][2]=2;
//		a.b[2][2]=4;
//		a.b[3][2]=1;
//		a.b[0][3]=4;
//		a.b[1][3]=1;
//		a.b[2][3]=2;
//		a.b[3][3]=3;
//		System.out.print(a.RegelnNichtVerletzt());
//		try {
//          a.solving();
//        System.out.print(a.issolved());
//        System.out.print(java.util.Arrays.toString(a.b[0]));
//        System.out.print(java.util.Arrays.toString(a.b[1]));
//        System.out.print(java.util.Arrays.toString(a.b[2]));
//        System.out.print(java.util.Arrays.toString(a.b[3]));      
//        System.out.print(java.util.Arrays.toString(a.b[4]));
//        System.out.print(java.util.Arrays.toString(a.b[5]));
//        System.out.print(java.util.Arrays.toString(a.b[6]));
//        System.out.print(java.util.Arrays.toString(a.b[7]));
//        System.out.print(java.util.Arrays.toString(a.b[8])); 
//		} catch(Exception e) {
//			e.printStackTrace();;
//		}
//      System.out.print(a.issolved());
		
//		for(int i=0; i<4;i++) {
//		z=z+java.util.Arrays.toString(a.b[i]);
//	}
//		System.out.print(z);
		
//		System.out.print(a.AnzahlBackTracking);

		
			
		
		
		
		
		
		
//		matrix b = new matrix(2);
//   	    b.b[0][0]=1;
//		b.b[1][0]=4;
//		b.b[2][0]=3;
//		b.b[3][0]=2;
//		b.b[0][1]=2;
//		b.b[1][1]=3;
//		b.b[2][1]=1;
//		b.b[3][1]=4;
//		b.b[0][2]=1;
//		b.b[1][2]=3;
//		b.b[2][2]=1;
//		b.b[3][2]=3;
//		b.b[0][3]=2;
//		b.b[1][3]=4;
//		b.b[2][3]=2;
//		b.b[3][3]=4;
//		
//		System.out.print(b.RegelnNichtVerletzt());
		
		
//		matrix c = new matrix(2);
//	c.b[0][0]=1;
//	c.b[1][0]=4;
//	c.b[2][0]=3;
//	c.b[3][0]=2;
//	c.b[0][1]=2;
//	c.b[1][1]=3;
//	c.b[2][1]=0;
//	c.b[3][1]=0;
//	c.b[0][2]=3;
//	c.b[1][2]=2;
//	c.b[2][2]=0;
//	c.b[3][2]=0;
//	c.b[0][3]=4;
//	c.b[1][3]=0;
//	c.b[2][3]=0;
//	c.b[3][3]=0;
//	
//	System.out.print(c.RegelnNichtVerletzt());
		
		
		
//[1, 2, 1, 0][4, 3, 0, 0][3, 0, 0, 0][2, 0, 0, 0]		
		
//		matrix d = new matrix(2);
//	d.b[0][0]=1;
//	d.b[1][0]=4;
//	d.b[2][0]=3;
//	d.b[3][0]=2;
//	d.b[0][1]=2;
//	d.b[1][1]=3;
//	d.b[2][1]=1;
//	d.b[3][1]=4;
//	d.b[0][2]=3;
//	d.b[1][2]=1;
//	d.b[2][2]=4;
//	d.b[3][2]=0;
//	d.b[0][3]=4;
//	d.b[1][3]=2;
//	d.b[2][3]=0;
//	d.b[3][3]=3;
//	
//	d.solving1();
//	for(int i=0; i<d.b.length;i++) {
//	System.out.print(java.util.Arrays.toString(d.b[i]));}
	
	//i=2 j=3 
        
		
		GUISODOKU2 h= new GUISODOKU2();
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
}
	}






//a = _ _ _   _ _ _   _ _ _
//	  _ _ _   _ _ _   _ _ _
//	  _ _ _   _ _ _   _ _ _
//	
//	  _ _ _   _ _ _   _ _ _
//    _ _ _   _ _ _   _ _ _
//	  _ _ _   _ _ _   _ _ _
//	
//	  _ _ _   _ _ _   _ _ _
//	  _ _ _   _ _ _   _ _ _
//	  _ _ _   _ _ _   _ _ _
//	
//	1. 0-(i-1), (i-2i-1)

