import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Lab1A {

    public static void main (String[] arg) {
  
        try {
            Scanner sc = new Scanner(new File("test.txt"));//byta ut "test.txt" mot arg[1]
            List<Integer> ls = new ArrayList<Integer>();
            
            while (sc.hasNextInt()){
                ls.add(sc.nextInt());
            }
            
            int[] ar = new int[ls.size()];
            
            for (int i=0; i<ls.size(); i++) {
                ar[i]=ls.get(i);
            }

            System.out.println((new MySortedIntArray(ar)).member(79));//byta ut # mot Integer.parseInt(arg[0])
        }

        catch(FileNotFoundException ex) {
            System.out.println(ex);
        }
    }
}