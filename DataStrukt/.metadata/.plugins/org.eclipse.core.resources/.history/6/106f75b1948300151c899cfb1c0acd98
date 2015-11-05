import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

// en klass innehållande en sorterad lista och en metod
public class MySortedIntArray implements MyIntSet {

// en sorterad lista av integers som sätts vid skapandet av classen
   public int[] ints;

// constructor, sätter listan
   public MySortedIntArray(String file){
   		try {
   				Scanner sc = new Scanner(new File(file));
   				while (sc.hasNextInt()) {
   		          ints[ints.length+1]=(sc.nextInt());
   				}
   			}
		   catch(FileNotFoundException ex){
			   System.out.println(ex);
		   }
		   		
	      
	   }
	   
// undersöker om argumentet finns i listan 
   public boolean member(int element) {
      if (ints[0] == element)
         return true;
      else
         return false;
   }

}