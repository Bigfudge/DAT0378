public class Bid<E> {
		
	// Namn p� budare
	public String name;
	
	// Typ av bud
	// Nytt bud: K eller S
	// F�r�ndringsbud: NK eller NS
	public String type;
	
	// V�rde p� bud
	public int value;
	
	// V�rde p� gammalt bud (anv�nds bara vid f�r�ndringsbud)
	public int old_value;
	
	// Constructor f�r nytt bud (budlistan)
	public Bid(String n , String t, int v) {
		name = n;
		type = t;
		value = v;
	}
	
	// Constructor f�r f�r�ndringsbud (budlistan)
	public Bid(String n , String t, int v, int o) {
		name = n;
		type = t;
		value = v;
		old_value = o;
	}
	
	// Constructor f�r orderlista
	public Bid(String n, int v) {
		name = n;
		value = v;
	}
	
}
