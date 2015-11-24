import java.util.ArrayList;
import java.util.Comparator;

public class PriorityQueue {

    public ArrayList<Bid> q = new ArrayList<Bid>();
    public Comparator<Bid> type;
    // lägga in senare: prioritetsköns konstruktorer ska kunna ta en komparator som argument
    // public PriorityQueue(..., Comparator<? super E> comp, ...) {
    
    public PriorityQueue(Comparator<Bid> c) {
    	type = c;
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
     public int getHighestBid() {
    	 return q.get(0).value;
    	 
     }
     
   
//Tar bort roten i trädet. Sätter dit det sista värdet. Kollar med barnen och byter plats med den minsta/störtsa elementet.
    public void deleteHighestBid() {
	   q.remove(0);
	   q.set(0, q.get(q.size()-1));
	   int newBid_index = 0;
	   int child1=1;
	   int child2=2;
	  
	   
	   while(type.compare(q.get(Math.min(child1,child2)), q.get(newBid_index)) > 0){
		   
		   Bid tmp = q.get(newBid_index);  
		   
		   q.set(newBid_index, q.get(Math.min(q.get(child1).value, q.get(child2).value)));
		   q.set(Math.min(q.get(child1).value, q.get(child2).value), tmp);
		   
		   newBid_index = Math.min(child1, child2);
		   child1=newBid_index*2;
		   child2=newBid_index*2+1;
	   }
	   
	   
    }

	public void print() {
    	for (int i=0; i<q.size(); i++) {
    		System.out.print(q.get(i).name + " " + q.get(i).value);
    		if (i+1<q.size()) {
    			System.out.print(", ");
    		}
    	}	
    }


	public void deleteBid(String name, int old_value) {
		// TODO Auto-generated method stub
		
	}
   


}