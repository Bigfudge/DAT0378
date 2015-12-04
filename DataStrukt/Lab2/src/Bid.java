public class Bid {
	
	private String name;
	private String type;
	private int oldValue;
	private int newValue;
	
	// Constructor for bids in bid list	(type NK or NS)
	public Bid(String n , String t, int ov, int nv) {
		name = n;
		type = t;
		oldValue = ov;
		newValue = nv;
	}
	
	// Constructor for bids in bid list	(type K or S)
	public Bid(String n , String t, int nv) {
		name = n;
		type = t;
		newValue = nv;
	}
	
	// Constructor for bids in order list
	public Bid(String n, int nv) {
		name = n;
		newValue = nv;
	}
	
	public String getName() {
		return name;
	}
	
	public String getType() {
		return type;
	}
	
	public int getOldValue() {
		return oldValue;
	}
	
	public int getNewValue() {
		return newValue;
	}
	
}