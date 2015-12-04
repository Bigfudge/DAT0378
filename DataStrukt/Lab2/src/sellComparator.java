import java.util.Comparator;

// Returns 0 if the name and value is the same
// Returns 1 if Child < Parent
// Returns -1 if Child >= Parent
public class sellComparator implements Comparator<Bid>{
	@Override
	public int compare(Bid a, Bid b){
			int value1 = a.getNewValue();
			int value2 = b.getNewValue();
			String name1 = a.getName();
			String name2 = b.getName();
			
			if (name1.equals(name2) &  value1 == value2 ) {
				return 0;
			}
			else if (value1 < value2) {
				return 1;
			}
			else {
				return -1;
			}
	}
}