
import java.util.Comparator;
//Ger positivt v�rde om barn har mindre v�rde �n f�r�lder
public class sellComparator implements Comparator<Bid>{
	@Override
	public int compare(Bid a, Bid b){
			int value1 = a.value;
			int value2 = b.value;
		return value2-value1;
	}


}
