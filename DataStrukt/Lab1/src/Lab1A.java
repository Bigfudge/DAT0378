import java.io.File;
import java.util.Scanner;

public class Lab1A {

   public static void main (String[] arg) {
   
//      int element = Integer.parseInt(arg[0]);
      
      // arg[1] = fil som ska läsas in med scanner och göras om till en lista (som sedan läggs i constructorn)
	  // System.out.println("Skriv fil:");
	   String s;
       Scanner sc = new Scanner(System.in);
       s = sc.nextLine();
       File openFile = new File(s);
       
       MySortedIntArray test = new MySortedIntArray(openFile.getAbsolutePath());
   for(int i=0;i<10;i++){
       System.out.println(test.ints[i]);
   }
   
}
}



//If your program is invoked using the command java Lab1A <element> <file>,
//where <file> is a file containing a sorted list of integers separated by spaces, and <element> is an integer, 
//then the program should print true on standard output if <element> is in <file>, and false otherwise.



