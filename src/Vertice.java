import java.util.ArrayList;

public class Vertice {
	ArrayList<Integer> edges = new ArrayList<Integer>();
	int num;
	boolean visited = false;
	
	public Vertice(int num, int edge) {
		edges.add(edge);
		this.num = num;
	}
	
	public void addEdge(int edge) {
		edges.add(edge);
	}
	
	public ArrayList<Integer> getEdges() {
		return edges;
	}
	
	public int getNum() {
		return this.num;
	}
	
	public void visit() {
		visited = true;
	}
}
