import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class PriorityQueue {

    public ArrayList<Bid> q = new ArrayList<Bid>();
    public Comparator<Bid> type;
    // lägga in senare: prioritetsköns konstruktorer ska kunna ta en komparator som argument
    // public PriorityQueue(..., Comparator<? super E> comp, ...) {
    
    public PriorityQueue(List<Bid> b, Comparator<Bid> c) {
    	type = c;
    	for(int i=0; i < b.size(); i++){
    		insertBid(b.get(i).name, b.get(i).value);
    	}
    }
   

    // metod som lägger till nytt bud
    public void insertBid(String n, int v) {

    	// 1.Add the element to the bottom level of the heap.
    	q.add((new Bid(n,v)));

        // 2.Compare the added element with its parent; if they are in the correct order, stop.
        // 3.If not, swap the element with its parent and return to the previous step.

    	int newBid_index = q.size()-1;
    	int parent_index = (newBid_index-1)/2;
    	while ((type.compare(q.get(newBid_index), q.get(parent_index))) > 0) {
    	
        	Bid tmp = q.get(newBid_index);    	
        
        	q.set(newBid_index, q.get(parent_index));
        	q.set(parent_index, tmp);
        	
        	newBid_index = parent_index;
        	parent_index = (newBid_index-1)/2;
    				
    	}
    }
    

    // metod som tar ut högsta värdet i listan
     public int highestBid() {
    	 return q.get(0).value;
     }
     
   
   // metod som tar bort budet från orderlistan
   // ingen träff: felmeddelande om försök att ändra icke existerande bud
    public void deleteBid(String n, int v) {
	   
    }
    
    
    public void print() {
    	for (int i=0; i<q.size(); i++) {
    		System.out.print(q.get(i).name + " " + q.get(i).value);
    		if (i+1<q.size()) {
    			System.out.print(", ");
    		}
    	}	
    }
   


}