import java.util.*;

public class PriorityQueue<E> {

	// The queue
    private ArrayList<E> q = new ArrayList<E>();
    
	// The queues comparator
    private Comparator<? super E> c;

    // The constructor, sets the queues comparator.
    public PriorityQueue(Comparator<? super E> comparator) {
    	c = comparator;
    }
    
    // Returns the size of the queue
    public int sizeQueue() {
    	return q.size();
    }
   
    // Returns the element with the highest priority in the queue.
    public E topElement() {
    	return q.get(0);
    }
    
    // Adds the element to the queue.
    public void addElement(E e) {
    	// 1.Add the element to the bottom level of the heap.
    	q.add(e);
        // 2.Compare the added element with its parent; if they are in the correct order, stop.
        // 3.If not, swap the element with its parent and return to the previous step.
    	int newBid_index = q.size()-1;
    	int parent_index = (newBid_index-1)/2;
    	
    	while ((c.compare(q.get(newBid_index), q.get(parent_index))) > 0) {
        	E tmp = q.get(newBid_index);    	
        	q.set(newBid_index, q.get(parent_index));
        	q.set(parent_index, tmp);
        	newBid_index = parent_index;
        	parent_index = (newBid_index-1)/2;		
    	}
    }
    
    // Returns the index to the element in the queue.
    private int findElement(E e) {
    	for (int i=0; i < sizeQueue(); i++) {
    		if (c.compare(q.get(i),e) == 0) {
    			return i;
    		}
    	}
    	return -1;
    }
    			
    // Removes the element from the queue, will generate an exception if the element isn't in the list.
    public void deleteElement(E e) {
   
    	int element_index = findElement(e);

    	// The element is alone in the queue
    	if (sizeQueue()==1 & element_index == 0 ) {
    		q.remove(0);
    		return;
    	}
    	
    	int last_element_index = sizeQueue()-1;

    	// The elementet is last in the list
    	if (element_index == last_element_index) {
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
    		if ((c.compare(q.get(child1_index), q.get(child2_index))) < 0) {
        		child_index++;
        	}
        }
    	// no children, done
    	else if (child1_index > last_element_index) {
    		return;
    	}
   
    	// 4.If not, swap the element and return to the previous step.
    	while ((c.compare(q.get(element_index), q.get(child_index))) < 0) {
    	
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
        		if ((c.compare(q.get(child1_index), q.get(child2_index))) < 0) {
            		child_index++;
            	}
            }
        	// no children, done
        	else if (child1_index > last_element_index) {
        		return;
        	}
    	}
    }
}