public class Bid {
		
	// Namn på budare
	public String name;
	
	// Typ av bud: K,S,NK,NS
	public String type;
	
	// Värde på bud
	public int value;
	
	// Värde på gammalt bud, finns enbart när type är NK eller NS
	public int old_value;
	
	// Constructor för nytt bud
	public Bid(String n, String t, int v){
		name = n;
		type = t;
		value = v;
	}
	
	// Constructor för ändring av bud
	public Bid(String n, String t, int v, int o){
		name = n;
		type = t;
		value = v;
		old_value = o;
	}
}
