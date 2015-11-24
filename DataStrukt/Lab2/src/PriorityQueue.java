import java.util.ArrayList;
import java.util.Comparator;

public class PriorityQueue {

    public ArrayList<Bid> q = new ArrayList<Bid>();
    public Comparator<Bid> type;

    // constructor med comparator
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
    
    
    // metod som tar ut värdet på det mest prioriterade budet i orderlistan
    // vad göra om listan är tom?
    public int highestBid() {
    	return q.get(0).value;
    }
    
	// O(logn) removal
    // metod som tar bort ett element ur orderlistan
    // paramter: index till elementet
    public void deleteBid(int element_index) {
   
    	// bara elementet finns orderlistan
    	if (q.size()==1) {
        	q.remove(0);
    		return;
    	}
    	
    	int last_element_index = q.size()-1;

    	// elementet ligger sista orderlistan
    	if (last_element_index==element_index) {
        	q.remove(element_index);
    		return;
    	}
    	
    	// 1. Replace the element with the last element
    	q.set(element_index, q.get(last_element_index));
  
    	// 2. Remove the last element
    	q.remove(last_element_index);
    	last_element_index--;

    	// 3. Compare the replaced element with its children
    	// if they are in the correct order, stop.
    	
    	// hitta child_index
    	int child1_index = element_index*2+1;
    	int child2_index = element_index*2+2;
    	int child_index = child1_index;
    	// om elementet har 2 barn, välj ut det största (min heap: välj ut det minsta istället)
    	if (child2_index <= last_element_index) {
        	if (q.get(child1_index).value < q.get(child2_index).value) {
        		child_index++;
        	}
        }
    	// elementet har inga barn, klar
    	else if (child1_index > last_element_index) {
    		return;
    	}
   
    	// vid min heap ändra < till >
    	// 4.If not, swap the element and return to the previous step.
    	while (q.get(element_index).value < q.get(child_index).value) {
        	Bid tmp = q.get(element_index);    	
            
        	q.set(element_index, q.get(child_index));
        	q.set(child_index, tmp);
        	
        	element_index = child_index;
        	
        	// hitta child_index
        	child1_index = element_index*2+1;
        	child2_index = element_index*2+2;
        	child_index = child1_index;
        	// om elementet har 2 barn, välj ut det största (min heap: välj ut det minsta istället)
        	if (child2_index <= last_element_index) {
            	if (q.get(child1_index).value < q.get(child2_index).value) {
            		child_index++;
            	}
            }
        	// elementet har inga barn, klar
        	else if (child1_index > last_element_index) {
        		return;
        	}

    	}
    }
    
    

    
    // metod som tar ett namn och ett value
    // returnerar platsen för elementet i orderlistan
    // returnerar -1 om elementet ej hittas
    public int findBid(String n, int v) {
    	for (int i=0; i<q.size(); i++) {
    		if (q.get(i).name.equals(n) & q.get(i).value == v ) {
    			return i;
    		}
    	}
    	return -1;
    }
       

    
    
    // metod som printar alla bud i orderlistan
    public void print() {
    	for (int i=0; i<q.size(); i++) {
    		System.out.print(q.get(i).name + " " + q.get(i).value);
    		if (i+1<q.size()) {
    			System.out.print(", ");
    		}
    	}	
    }
   


}
