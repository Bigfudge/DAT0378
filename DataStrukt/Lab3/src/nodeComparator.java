import java.util.Comparator;
import Lab3Help.*;

public class nodeComparator implements Comparator<BLineStop> {
	@Override
	public int compare(BLineStop a, BLineStop b){
		
			int value1 = a.getTime();
			int value2 = b.getTime();
	
			if (value1 < value2) {
				return 1;
			}
			else {
				return -1;
			}
	}
}
