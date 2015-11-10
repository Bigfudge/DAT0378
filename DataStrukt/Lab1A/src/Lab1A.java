import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class Lab1A {

    public static void main (String[] arg) {
  
        try {
            Scanner sc = new Scanner(new File(arg[1]));
            ArrayList<Integer> ls = new ArrayList();
            
            while (sc.hasNextInt()){
                ls.add(sc.nextInt());
            }
            sc.close();
            
            int[] ar = new int[ls.size()];
            
            for (int i=0; i<ls.size(); i++) {
            	ar[i]=ls.get(i);
            }
            
            System.out.println((new MySortedIntArray(ar)).member(Integer.parseInt(arg[0])));
        }

        catch(FileNotFoundException ex) {
            System.out.println(ex);
        }
    }
}