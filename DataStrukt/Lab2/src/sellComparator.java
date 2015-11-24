
import java.util.Comparator;
//Ger positivt värde om barn har mindre värde än förälder
public class sellComparator implements Comparator<Bid>{
	@Override
	public int compare(Bid a, Bid b){
			int value1 = a.value;
			int value2 = b.value;
		return value2-value1;
	}


}
