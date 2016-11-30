import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Stack;

public class Puzzle {
	//static Node base;
	static int steps = 0;
	//static ArrayList<Graph> frontier = new ArrayList<Graph>();
	static PriorityQueue<Graph> frontier = new PriorityQueue<Graph>();
	static double t  = 0;
	static double t2 = 0;
	
	public static void main(String[] args) throws FileNotFoundException {
		ArrayList<Node> nodeList = new ArrayList<Node>();	
		
		//need to change to args[0]
		File file = new File("/Users/Archer/Downloads/test3_0.txt");
		Scanner input = new Scanner(file);
		int i = 0, x = 0, j = 0;
		
		double start = System.nanoTime();
		while(input.hasNext()) {
			String line = input.nextLine();
			if (i < 3) {
				i++;
				String[] nums = line.split("\\s+");
				for (String num : nums) {
					Node n = new Node();
					n.current = j++;
					n.num = Integer.parseInt(num);
					nodeList.add(n);
				}
			} else if (i == 3) {i++;} 
			else if (i > 3) {
				//find the node then set its goal state
				String[] nums = line.split("\\s+");
				for (String num : nums) { 
					for (Node n : nodeList) {
						//if the nodes number equals the the goal graph then set its goal to that number
						if (n.num == Integer.parseInt(num)) {
							n.goal = x;
							x++;
						}
					}
				}
			}
		}
		double stopInput = System.nanoTime();
		
		//set the original graph
		Graph graph = new Graph(nodeList);
		
		//count the number of inversions	
		i = graph.countInversions();
		
		if (i % 2 == 1) {
			System.out.println("no solution");
			System.exit(0);
		} 
		
		//this is where i can loop
		while(!graph.isGoalState()) {
		
			//check possible moves
			checkMoves(graph);
			
			//do i need to clone this???? 88888
			ArrayList<Node> moves = (ArrayList<Node>) graph.base.connections;
			
			//calculate cost for each move need to switch current first
			
			for (Node n : moves) {
				//create temp graph and remove the base and current node so that we can replace and put in temp graph
				Graph t = new Graph(graph.nodes);
				Node base = t.findBase();
				
				//get the equivalent node
				for (Node node : t.nodes) {
					if (node.num == n.num) {
						n = node;
						break;
					}
				}
				
				//switch the nodes
				int temp = n.current;
				n.current = base.current;
				base.current = temp;
				
				//put back into graph
				t.nodeChanged = n.num;
				t.steps = graph.steps + 1;
				t.parent = graph;
				
				//t.findG();
				double s = System.nanoTime();
				t.findH();
				t.findF();
				double st = System.nanoTime();
				t2 += (double)(st - s)/1000000000.0;
				
				//store in frontier with graph
				//if (t.f < 35)
				frontier.add(t);
				
				//revert base back
				//System.out.println(t.toString());
				
			}
			
			//get highest priority i.e. expand
			
			/**for (Graph g : frontier) {
				if (g.f <= graph.f) {
					graph = g;
					frontier.remove(g);
				}
			}**/
			graph = frontier.poll();
			//System.out.println(graph.toString());
		}
		double stop = System.nanoTime();
		System.out.println("Time to complete: " + (double)(stop - start)/1000000000.0 + " seconds");
		System.out.println("Time to read input: " + (stopInput - start)/1000000000.0);
		System.out.println("Time to find moves: " + t);
		System.out.println("Time for finding f and switching nodes: " + t2);
		//finished a star
		Stack<Graph> stack = new Stack<Graph>();
		while(graph.parent != null) {
			stack.push(graph);
			graph = graph.parent;
		}
		
		while(!stack.isEmpty())
			System.out.println(stack.pop().toString());
		
	}

	private static void checkMoves(Graph graph) {
		//can optimize if need be... just add to both connections as we go
		double start = System.nanoTime();
		Node base = graph.findBase();
		
		//make sure base connections is empty
		base.connections.clear();
		
		if (base.current == 0) {
			int i = 0;
			for (Node n2 : graph.nodes) {
				if (n2.num != graph.nodeChanged) {
					if (n2.current == 1 || n2.current == 3) { 
						base.connections.add(n2);
						i++;
					}
					if (i == 2) {break;}
				}
			}
		} else if (base.current == 1) {
			int i = 0;
			for (Node n2 : graph.nodes) {
				if (n2.num != graph.nodeChanged) {
					if (n2.current == 0 || n2.current == 2 || n2.current == 4) {
						base.connections.add(n2);
						i++;
					}
					if (i == 3) {break;}
				}
			}
			
		} else if (base.current == 2) {
			int i = 0;
			for (Node n2 : graph.nodes) {
				if (n2.num != graph.nodeChanged) {
					if (n2.current == 1 || n2.current == 5) {
						base.connections.add(n2);
						i++;
					}
					if (i == 2) {break;}
				}
			}
			
		} else if (base.current == 3) {
			int i = 0;
			for (Node n2 : graph.nodes) {
				if (n2.num != graph.nodeChanged) {
					if (n2.current == 0 || n2.current == 4 || n2.current == 6) {
						base.connections.add(n2);
						i++;
					}
					if (i == 3) {break;}
				}
			}
			
		} else if (base.current == 4) {
			int i = 0;
			for (Node n2 : graph.nodes) {
				if (n2.num != graph.nodeChanged) {
					if (n2.current == 1 || n2.current == 3 || n2.current == 5 || n2.current == 7) {
						base.connections.add(n2);
						i++;
					}
					if (i == 4) {break;}
				}
			}
			
		} else if (base.current == 5) {
			int i = 0;
			for (Node n2 : graph.nodes) {
				if (n2.num != graph.nodeChanged) {
					if (n2.current == 2 || n2.current == 4 || n2.current == 8) {
						base.connections.add(n2);
						i++;
					}
					if (i == 3) {break;}
				}
			}
			
		} else if (base.current == 6) {
			int i = 0;
			for (Node n2 : graph.nodes) {
				if (n2.num != graph.nodeChanged) {
					if (n2.current == 3 || n2.current == 7) {
						base.connections.add(n2);
						i++;
					}
					if (i == 2) {break;}
				}
			}
			
		} else if (base.current == 7) {
			int i = 0;
			for (Node n2 : graph.nodes) {
				if (n2.num != graph.nodeChanged) {
					if (n2.current == 4 || n2.current == 6 || n2.current == 8) {
						base.connections.add(n2);
						i++;
					}
					if (i == 3) {break;}
				}
			}
			
		} else if (base.current == 8) {
			int i = 0;
			for (Node n2 : graph.nodes) {
				if (n2.num != graph.nodeChanged) {
					if (n2.current == 5 ) {
						base.connections.add(n2);
						i++;
					} else if (n2.current == 7) {
						base.connections.add(n2);
						i++;
					}
					if (i == 2) {break;}
				}
			}
		}
		
		double stop = System.nanoTime();
		t += (double)(stop - start)/1000000000.0;
	}

}
