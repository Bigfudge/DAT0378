public class Bid {
		
	// Namn p� budare
	public String name;
	
	// Typ av bud: K,S,NK,NS
	public String type;
	
	// V�rde p� bud
	public int value;
	
	// V�rde p� gammalt bud, finns enbart n�r type �r NK eller NS
	public int old_value;
	
	// Constructor f�r nytt bud
	public Bid(String n, String t, int v){
		name = n;
		type = t;
		value = v;
	}
	
	// Constructor f�r �ndring av bud
	public Bid(String n, String t, int v, int o){
		name = n;
		type = t;
		value = v;
		old_value = o;
	}
}
