import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class Lab1B {

    public static void main (String[] arg) {
  
        try {
            Scanner sc = new Scanner(new File(arg[1]));
            ArrayList<Integer> ls = new ArrayList();
            
            while (sc.hasNextInt()){
                ls.add(sc.nextInt());
            }
            sc.close();
            
            Integer[] ar = ls.toArray(new Integer[ls.size()]);
            
            System.out.println((new MySortedArray(ar)).member(Integer.parseInt(arg[0])));
        }

        catch(FileNotFoundException ex) {
            System.out.println(ex);
        }
    }
}