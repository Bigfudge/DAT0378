import java.util.PriorityQueue;
import Lab3Help.*;


public class Node {
	
	private String name;
	
	private PriorityQueue<BLineStop> grannmatris = new PriorityQueue<BLineStop>();

	public Node(BStop s) {
		name = s.getName();
	}
	
	public void updateNode(BLineStop b) {
		grannmatris.add(b);
	}
	
	
}