import java.util.PriorityQueue;
import Lab3Help.*;


public class Node {
	
	private String name;
	
	private PriorityQueue<BLineStop> grannmatris = new PriorityQueue<BLineStop>(new nodeComparator());

	public Node(BStop s) {
		name = s.getName();
	}
	
	public void updateNode(BLineStop b) {
		if (!grannmatris.contains(b)) {
			grannmatris.add(b);
		}
	}
	
	public String getName() {
		return name;
	}
	
	
}