
import java.util.Comparator;
//Ger positivt v�rde om barn har st�rre v�rde �n f�r�lder
public class buyComparator implements Comparator<Bid>{
	@Override
	public int compare(Bid a, Bid b){
			int value1 = a.value;
			int value2 = b.value;
		return value1-value2;
	}


}
