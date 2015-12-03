public class Bid<E> {
		
	// Namn på budare
	public String name;
	
	// Typ av bud
	// Nytt bud: K eller S
	// Förändringsbud: NK eller NS
	public String type;
	
	// Värde på bud
	public int value;
	
	// Värde på gammalt bud (används bara vid förändringsbud)
	public int old_value;
	
	// Constructor för nytt bud (budlistan)
	public Bid(String n , String t, int v) {
		name = n;
		type = t;
		value = v;
	}
	
	// Constructor för förändringsbud (budlistan)
	public Bid(String n , String t, int v, int o) {
		name = n;
		type = t;
		value = v;
		old_value = o;
	}
	
	// Constructor för orderlista
	public Bid(String n, int v) {
		name = n;
		value = v;
	}
	
}
