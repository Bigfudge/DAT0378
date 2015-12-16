import java.util.PriorityQueue;
import Lab3Help.*;


public class Node {
	
	private String name;
	private int dist;
	private PriorityQueue<BLineStop> grannmatris = new PriorityQueue<BLineStop>(new nodeComparator());

	public Node(BStop s) {
		name = s.getName();
		this.dist = 2147483647; 
	}
	
	public void updateNode(BLineStop b) {
		if (!grannmatris.contains(b)) {
			grannmatris.add(b);
		}
	}
	
	public String getName() {
		return name;
	}
	public int getDistance(){
		return dist;
	}
	public void setDistance(int s){
		dist = s;
		return;
	}
	
	
}