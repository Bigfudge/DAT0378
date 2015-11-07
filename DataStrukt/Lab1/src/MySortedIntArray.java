public class MySortedIntArray	implements MyIntSet {

    public int[] ar;
    
    public MySortedIntArray(int[] x) {
        ar = x;
    }

    public boolean member(int el) {
    
// test.txt innehåller: 0 2 4 9 12 19 22 75 76 89 99 120
    
        int min = 0;
        int max = ar.length - 1;
        
        while (min <= max) {
        
            int mid = min + (max - min) / 2;
            
            if (el < ar[mid]) {
                max = mid - 1;
            }
            else if (el > ar[mid]) {
                min = mid + 1;
            }
            else if (el == ar[mid]) {
                return true;
            }
        }
        return false;
        
    }

}