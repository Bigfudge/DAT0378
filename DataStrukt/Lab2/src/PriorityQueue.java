import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class PriorityQueue<E> {

	// the list
    public ArrayList<E> q = new ArrayList<E>();
    public Comparator<? super E> type;

    // constructor with comparator
    public PriorityQueue(Comparator<? super E> c) {
    	type = c;
    }
   

    // adding a bid
    public void insertBid(String n, int v) {

    	// 1.Add the element to the bottom level of the heap.
    	q.add((E)(new Bid(n,v)));

        // 2.Compare the added element with its parent; if they are in the correct order, stop.
        // 3.If not, swap the element with its parent and return to the previous step.

    	int newBid_index = q.size()-1;
    	int parent_index = (newBid_index-1)/2;
    	while ((type.compare(q.get(newBid_index), q.get(parent_index))) > 0) {
    	
        	E tmp = q.get(newBid_index);    	
        
        	q.set(newBid_index, q.get(parent_index));
        	q.set(parent_index, (E) tmp);
        	
        	newBid_index = parent_index;
        	parent_index = (newBid_index-1)/2;
    				
    	}
    }
    
    
    
    // return most significant bid
    public int highestBid() {
    	return ((Bid)(q.get(0))).value;
    }
    
	// O(logn) removal
    // removes element with index (parameter) from the list
    public void deleteBid(String n, int v) {
   
    	int element_index = findBid(n, v);
    	// only the element in the list
    	if (q.size()==1) {
        	q.remove(0);
    		return;
    	}
    	
    	int last_element_index = q.size()-1;

    	// the elementet is the last in the list
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
    	
    	// find index to the child
    	int child1_index = element_index*2+1;
    	int child2_index = element_index*2+2;
    	int child_index = child1_index;
    	// two children, take the most significant child
    	if (child2_index <= last_element_index) {
    		if ((type.compare(q.get(child1_index), q.get(child2_index))) < 0) {
        		child_index++;
        	}
        }
    	// no children, done
    	else if (child1_index > last_element_index) {
    		return;
    	}
   
    	// 4.If not, swap the element and return to the previous step.
    	while ((type.compare(q.get(element_index), q.get(child_index))) < 0) {
    	
        	E tmp = q.get(element_index);
        	q.set(element_index, q.get(child_index));
        	q.set(child_index, tmp);
        	element_index = child_index;
        	
        	// find index to the child
        	child1_index = element_index*2+1;
        	child2_index = element_index*2+2;
        	child_index = child1_index;
        	// two children, take the most significant child
        	if (child2_index <= last_element_index) {
        		if ((type.compare(q.get(child1_index), q.get(child2_index))) < 0) {
            		child_index++;
            	}
            }
        	// no children, done
        	else if (child1_index > last_element_index) {
        		return;
        	}
    	}
    }
    
    // returns the index to the element in the list
    // or -1 if the element is missing
    private int findBid(String n, int v) {
    	for (int i=0; i<q.size(); i++) {
    		if (((Bid)q.get(i)).name.equals(n) & ((Bid)(q.get(i))).value == v ) {
    			return i;
    		}
    	}
    	return -1;
    }

    
    // prints the list
    public void printList() {
    	for (int i=0; i<q.size(); i++) {
    		System.out.print(((Bid)q.get(i)).name + " " + ((Bid)(q.get(i))).value);
    		if (i+1<q.size()) {
    			System.out.print(", ");
    		}
    	}	
    }
   
    // removes all elements in the list and print each removal, significant order
    public void print() {
    	int x = q.size();
    	for (int i=0; i<x; i++) {
    		System.out.print(((Bid)q.get(0)).name + " " + ((Bid)q.get(0)).value);
    		if (i+1<x) {
    			System.out.print(", ");
    		}
    		deleteMin();
    	}	
    }
    public void deleteMin(){
    	deleteBid(((Bid)q.get(0)).name, ((Bid)q.get(0)).value);
    }


}
