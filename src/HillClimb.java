import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class HillClimb {
    static int numVertice;
    static int numEdges;

    public static void main(String[] args) {
        readFileIn("/Users/Archer/Downloads/ran25.txt");
    }

    public static void readFileIn(String path) {
        try {
            Scanner scanner = new Scanner(new File(path));

            //store vertice objects and store vertice number list so that we can revist in hill climb
            int i = 1;
            ArrayList<Vertice> vertices = new ArrayList<>();

            while(scanner.hasNext()) {
                //First line, so we know vertices and edges
                if (i == 1) {
                    numVertice = scanner.nextInt();
                    numEdges = scanner.nextInt();
                    i ++;
                }
                
                int node = scanner.nextInt();
                int edge = scanner.nextInt();
                
                if (vertices.size() == 0) {
                	vertices.add(new Vertice(node, edge));
                	vertices.add(new Vertice(edge, node));
                }
                else {
                	boolean addNode = true, addEdge = true;
	                for (int j = 0; j < vertices.size(); j++) {
	                	if (node == vertices.get(j).getNum()) {
	                		vertices.get(j).addEdge(edge);
	                		addNode = false;
	                	}
	                	if (edge == vertices.get(j).getNum()) {
	                		vertices.get(j).addEdge(node);
	                		addEdge = false;
	                	}
	                }
	                
	                if (addNode) {
	                	vertices.add(new Vertice(node, edge));
	                	
	                }
	                if (addEdge) {
	                	vertices.add(new Vertice(edge, node));
	                }
	               
                }

                //System.out.println(scanner.nextInt());
            }
            hillClimbSearch(vertices);
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    
    private static void hillClimbSearch(ArrayList<Vertice> vertices) {
    	ArrayList<Vertice> nums = new ArrayList<Vertice>();
    	
    	double ran = Math.random() * numVertice;
    	Vertice startVert = vertices.get((int)ran);
    	
    	ArrayList<Integer> edges = startVert.getEdges();
    	
    	
    	for (int i = 0; i < vertices.size(); i++) {
    		ArrayList<Integer> testEdges = vertices.get(i).getEdges();
    		//System.out.println(vertices.get(i).getNum() + ": ");
    		boolean sameChild = false;
    		for (int e = 0; e < testEdges.size(); e ++) { 
    			//System.out.print(testEdges.get(e));
    			if (testEdges.get(e) == startVert.getNum()) {
    				vertices.remove(vertices.get(i));
    			}
    			for (int j = 0; j < edges.size(); j++) {
    				if (testEdges.get(e) == edges.get(j)) {
    					sameChild = true;
    					break;
    				}
    				
    			}
    			if (!sameChild) {
    				vertices.remove(vertices.get(i));
    				break;
    			}
    		}
    	}
    	//vertices.add(startVert);
    	//System.out.println(startVert.getNum());
    	printResults(vertices);
    }

	private static void printResults (ArrayList<Vertice> vertices) {
		for (int i = 0; i < vertices.size(); i++) {
			if (i + 1 == vertices.size()) {
				System.out.println(vertices.get(i).getNum());
				System.out.println("Size: " + i);
			} else {
				System.out.print(vertices.get(i).getNum() + ", ");
			}
		}
		
	}
}