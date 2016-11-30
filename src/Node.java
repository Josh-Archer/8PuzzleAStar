import java.util.ArrayList;

public class Node {
	int current, goal, num;
	//h should equal nodes out of place plus step count
	//g should be added to parent g and is path cost
	int g,h,f;
	ArrayList<Node> connections = new ArrayList<Node>();
	
	public Node() {
		
	}
	
	public Node(Node n) {
		current = n.current;
		goal = n.goal;
		num = n.num;
		g = n.g;
		h = n.h;
		f = n.f;
		
		for (Node a : n.connections) {
			connections.add(new Node(a));
		}
	}
	
	
}
