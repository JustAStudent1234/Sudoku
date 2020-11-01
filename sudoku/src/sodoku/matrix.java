package sodoku;


public class matrix {

	int[] liste;
	int[] liste1;
	int[][] b;
	int d; 
	int k;
	int[][] result;
	boolean hilfewurdeausgeführt=false;
	int AnzahlBackTracking=0;
	
	
	
	public matrix(int d) {	
	
	k = (int)Math.pow(d, 2);	
	b= new int[k][k];
	liste = new int[k];
	liste1 = new int[k];
	this.d=d;
	for(int i=0; i<k ; i++) {		
	liste[i]=i+1;
	liste1[i]=i+1;
	}	
	}	
	
	
	//true wenn das sodoku gelöst ist
	public boolean issolved() {
	 
    
    for(int m=0;m<k;m=m+d) {
    for(int n=0;n<k;n=n+d) {
    
    for(int i=m; i<m+d; i++) {
		for(int j=n; j<n+d; j++) {
			if(contains(liste,b[i][j])) {
				
			if(contains(liste1,b[i][j])) {	
				überschreiben(liste1,b[i][j]);
				}
			else {
				
				liste1=liste.clone();
				return false;
			}}}
			}
    
    if(!consistsof(liste1)) {
    	liste1=liste.clone();
     	return false;
		}
    else {
    	liste1=liste.clone();    }
    }  
    }
    
    
    
    
    for(int i=0; i<k;i++) {
    	for(int j=0;j<k;j++){
    		
    		if(contains(liste,b[i][j])) {

    			if(contains(liste1,b[i][j])) {	
    				überschreiben(liste1,b[i][j]);
    				}
    			else {
    				
    				liste1=liste.clone();
    				return false;
    		}
    	}else {return false;}
    }
    	 if(!consistsof(liste1)) {
    		 liste1=liste.clone();
    	     	return false;
    			}
    	    else {
    	    	liste1=liste.clone();    }
    	}
    
   
    
   
    
    
    
    for(int j=0; j<k;j++) {
    	for(int i=0;i<k;i++){
    		
    		if(contains(liste,b[i][j])) {

    			if(contains(liste1,b[i][j])) {	
    				überschreiben(liste1,b[i][j]);
    				}
    			else {
    				
    				liste1=liste.clone();
    				return false;
    		}
    	} else {return false;}
    }
    	 if(!consistsof(liste1)) {
    		 liste1=liste.clone();
    	     	return false;
    			}
    	    else {
    	    	liste1=liste.clone();    }
    	}
    
    	
   
   
    return true;	
	}
	
	
	
	
	
	//true wenn regeln des aktuellen sodokus nicht verletzt sind.
	public boolean RegelnNichtVerletzt() {
		
		for(int m=0;m<k;m=m+d) {
		    for(int n=0;n<k;n=n+d) {
		    for(int i=m; i<m+d; i++) {
				for(int j=n; j<n+d; j++) {
				
					
					if(!(b[i][j]==0)){
					if(contains(liste,b[i][j])){
						
					if(contains(liste1,b[i][j])) {
						überschreiben(liste1,b[i][j]);
						}
					else {
						
						liste1=liste.clone();
						return false;
					}
					}
					else{
						liste1=liste.clone();
					return false;
					}
					}
					else {
						continue;
					}
					}
		    }
		   
		    	liste1=liste.clone();
		     
		    }}	
		
		
		
		
		 for(int i=0; i<k; i++) {
			 liste1=liste.clone();
		    	for(int j=0; j<k; j++){   			   
		    		
		    		if(b[i][j]==0) {continue;}
		    		if(contains(liste1,b[i][j])) {	   
	    				überschreiben(liste1,b[i][j]);  
	    				}
	    			else {
	    				
	    				liste1=liste.clone();
	    				return false;
	    		}
		    	
		    		
		 }
		 }
		 
		 
		 
		 for(int j=0; j<k; j++) {
			 liste1=liste.clone();
		    	for(int i=0; i<k; i++){   			   
		    		
		    		if(b[i][j]==0) {continue;}
		    		if(contains(liste1,b[i][j])) {	   
	    				überschreiben(liste1,b[i][j]);  
	    				}
	    			else {
	    				
	    				liste1=liste.clone();
	    				return false;
	    		}
		    	
		    		
		 }
		 }

return true;}		    	
		
		    
		    
		    	
	
	

//löst das sodoku falls machbar oder wirft einen Fehler.
	public void solving()   throws sodokuException{
    		
		if(!this.RegelnNichtVerletzt()) {
			sodokuException e = new sodokuException();
			throw e;			
		}
		
		
		for(int i=0;i<k;i++) {
			
			
			
			for(int j=0; j<k; j++) {
				
				
				if(b[i][j]==0) {
				
				for(int l=1; l<k+1; l++) {
			
					
					if(this.issolved()) {						
						return;	
						}
			
					
				b=hilfe(l,b,i,j,b[i][j]).clone();   
				
//				for(int ü=0; ü<b.length;ü++) {
//					System.out.print(java.util.Arrays.toString(b[ü]));
//				}
//				System.out.print("\n");
				
				if(hilfewurdeausgeführt==false && l==k) {
					AnzahlBackTracking++;
					b[i][j]=0;
					return;
				}
				
				if(hilfewurdeausgeführt==true) {
					this.solving();
				}
				else continue;
							
			}
				}
				
				
			else continue;
			}
		}
		}
	
	
	
	
	
	
	
	
//	 gibt die matrix zurück mit b[i][j]= l, wenn es die regeln nicht verletzt, ansonsten nur b.	
	public int[][] hilfe(int l, int[][] o,int i, int j, int u) {
		
	
		
		matrix p = new matrix(d);
		int[][] result = new int[k][k];
		result=o.clone();
		
		
		
		result[i][j]=l; 
		p.b=result.clone();
		
		
		
		if(p.RegelnNichtVerletzt()) {
			
			
			hilfewurdeausgeführt=true;
			return result;
		}
		else {
			hilfewurdeausgeführt=false;
			o[i][j]=u;
			return o;
		}
			
	}
	
	
	
	
	
	private boolean contains(int[] a, int b) {
		for(int i=0; i<a.length;i++) {
			if(a[i]==b) {
				return true;
			}
		}
		return false;
	}
	
	private void überschreiben(int[] a, int b) {
	for(int i=0; i<a.length;i++) {
		if(a[i]==b) {
		a[i]=-1;	
		}
	}
	}
	private boolean consistsof(int[] a) {
	for(int i=0; i<a.length;i++) {
		if(!(a[i]==-1)) {
		return false;	
		}
	}
	return true;
	}
	
	public void add(int a) {
		for(int i=0;i<k;i++) {
			for(int j=0;j<k;j++) {
		if(b[i][j]==0) {
		b[i][j]=a;
		return;	
		}
			}
		}
		
	}
	
	
	
	
	
	
}
