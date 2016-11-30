import java.util.ArrayList;
import java.util.Iterator;

public class Graph implements Comparable<Graph> {
	Graph parent;
	Node base;
	int steps = 0;
	int f = 0;
	int h = 0;
	int nodeChanged;
	
	ArrayList<Node> nodes = new ArrayList<Node>();
	
	public Graph() {

	}
	public Graph(ArrayList<Node> n) {
		for (Node a : n) {
			nodes.add(new Node(a));
		}
	}
	
	public void setParent(Graph g) {
		parent = g;
	}
	
	public Graph getParent() {
		return parent;
	}
	
	public boolean isGoalState() {
		for (Node n : nodes) {
			if (n.current != n.goal) {
				return false;
			}
		}
		
		return true;
	}
	
	public Node findBase() {
		for (Node node : nodes) {
			if (node.num == 0) {
				base = node;
				return node;
			}
		}
		return null;
	}
	
	public void findF() {		
		f = steps + h;
	}
	
	/*public void findH() {
		
		for (Node n : nodes) {
			if (n.num != '0') {
			if (n.current != n.goal) {
				h++;
			}
			}
		}
		
		//add 1 because we would be adding a step
		/*if (parent != null) {
			h = x + parent.steps + 1;
		} else {
			h = x + steps;
		}
		//won't work right now because I need have it for the node passed
		//x += checkLinearConflict();
		//h += steps;
	}*/
	
	
	
	public void findH() {
		
		double start = System.nanoTime();
		for (Node n : nodes) {
			if (n.current == 0) {
				if (n.goal == 1) {
					h += 1;
				} else if (n.goal == 2) {
					h += 4;
				} else if (n.goal == 3) {
					h += 1;
				} else if (n.goal == 4) {
					h += 2;
				} else if (n.goal == 5) {
					h += 5;
				} else if (n.goal == 6) {
					h += 4;
				} else if (n.goal == 7) {
					h += 5;
				} else if (n.goal == 8) {
					h += 8;
				} 
			} else if(n.current == 1) {
				if (n.goal == 0) {
					h += 1;
				} else if (n.goal == 2) {
					h += 1;
				} else if (n.goal == 3) {
					h += 2;
				} else if (n.goal == 4) {
					h += 1;
				} else if (n.goal == 5) {
					h += 2;
				} else if (n.goal == 6) {
					h += 5;
				} else if (n.goal == 7) {
					h += 4;
				} else if (n.goal == 8) {
					h += 5;
				} 
				
			} else if(n.current == 2) {
				if (n.goal == 0) {
					h += 4;
				} else if (n.goal == 1) {
					h += 1;
				} else if (n.goal == 3) {
					h += 5;
				} else if (n.goal == 4) {
					h += 2;
				} else if (n.goal == 5) {
					h += 1;
				} else if (n.goal == 6) {
					h += 8;
				} else if (n.goal == 7) {
					h += 5;
				} else if (n.goal == 8) {
					h += 4;
				} 
			} else if(n.current == 3) {
				if (n.goal == 0) {
					h += 1;
				} else if (n.goal == 1) {
					h += 2;
				} else if (n.goal == 2) {
					h += 5;
				} else if (n.goal == 4) {
					h += 1;
				} else if (n.goal == 5) {
					h += 4;
				} else if (n.goal == 6) {
					h += 1;
				} else if (n.goal == 7) {
					h += 2;
				} else if (n.goal == 8) {
					h += 5;
				} 
				
			} else if(n.current == 4) {
				if (n.goal == 0) {
					h += 2;
				} else if (n.goal == 1) {
					h += 1;
				} else if (n.goal == 2) {
					h += 2;
				} else if (n.goal == 3) {
					h += 1;
				} else if (n.goal == 5) {
					h += 1;
				} else if (n.goal == 6) {
					h += 2;
				} else if (n.goal == 7) {
					h += 1;
				} else if (n.goal == 8) {
					h += 2;
				} 
			} else if(n.current == 5) {
				if (n.goal == 0) {
					h += 5;
				} else if (n.goal == 1) {
					h += 2;
				} else if (n.goal == 2) {
					h += 1;
				} else if (n.goal == 3) {
					h += 4;
				} else if (n.goal == 4) {
					h += 1;
				} else if (n.goal == 6) {
					h += 5;
				} else if (n.goal == 7) {
					h += 2;
				} else if (n.goal == 8) {
					h += 1;
				} 		
			} else if(n.current == 6) {
				if (n.goal == 0) {
					h += 4;
				} else if (n.goal == 1) {
					h += 5;
				} else if (n.goal == 2) {
					h += 8;
				} else if (n.goal == 3) {
					h += 1;
				} else if (n.goal == 4) {
					h += 2;
				} else if (n.goal == 5) {
					h += 5;
				} else if (n.goal == 7) {
					h += 1;
				} else if (n.goal == 8) {
					h += 4;
				} 
				
			} else if(n.current == 7) {
				if (n.goal == 0) {
					h += 5;
				} else if (n.goal == 1) {
					h += 4;
				} else if (n.goal == 2) {
					h += 5;
				} else if (n.goal == 3) {
					h += 2;
				} else if (n.goal == 4) {
					h += 1;
				} else if (n.goal == 5) {
					h += 2;
				} else if (n.goal == 6) {
					h += 1;
				} else if (n.goal == 8) {
					h += 1;
				}
			} else if(n.current == 8) {
				if (n.goal == 0) {
					h += 8;
				} else if (n.goal == 1) {
					h += 5;
				} else if (n.goal == 2) {
					h += 4;
				} else if (n.goal == 3) {
					h += 5;
				} else if (n.goal == 4) {
					h += 2;
				} else if (n.goal == 5) {
					h += 1;
				} else if (n.goal == 6) {
					h += 4;
				} else if (n.goal == 7) {
					h += 1;
				}
			}
			//linearConflict(n);
		}
		//System.out.println("Time to complete: " + (double)(System.nanoTime() - s)/1000000000.0 + " seconds");
	}
	
	private void linearConflict(Node n) {
		boolean conflict = false;
		for (Node n2 : nodes) {
			if (n.current == 0) {
				if (n.goal == 1 || n.goal == 2) {
					if ((n2.current == 1 || n2.current == 2) && (n2.goal == 0 || n2.goal == 1)) {
						conflict = true;
					}
				}
			} else if (n.current == 1) {
				if (n.goal == 2) {
					if(n2.current == 2 && n2.goal < 2) {
						conflict = true;
					}
				}
			} else if (n.current == 2) {
				if (n.goal < 2) {
					if (n2.current < 2 && n2.goal == 2) {
						conflict = true;
					}
				}
			} else if (n.current == 3) {
				if (n.goal == 4 || n.goal == 5) {
					if ((n2.current == 4 || n2.current == 5) && (n2.goal == 3 || n2.goal == 4)) {
						conflict = true;
					}
				}
			} else if (n.current == 4) {
				if (n.goal == 5) {
					if(n2.current == 5 && (n2.goal == 3 || n2.goal == 4)) {
						conflict = true;
					}
				}
			} else if (n.current == 5) {
				if (n.goal == 3 || n.goal == 4) {
					if ((n2.current == 3 || n2.current == 4) && n2.goal == 5) {
						conflict = true;
					}
				}
			} else if (n.current == 6) {
				if (n.goal == 7 || n.goal == 8) {
					if ((n2.current == 7 || n2.current == 8) && (n2.goal == 6 || n2.goal == 7)) {
						conflict = true;
					}
				}
			} else if (n.current == 7) {
				if (n.goal == 8) {
					if(n2.current == 8 && (n2.goal == 6 || n2.goal == 7)) {
						conflict = true;
					}
				}
			} else if (n.current == 8) {
				if (n.goal == 6 || n.goal == 7) {
					if ((n2.current == 6 || n2.current == 7) && n2.goal == 8) {
						conflict = true;
					}
				}
			}
			if (conflict) {
				h += 2;
				//break;
			}
		}
		
	}
	public int countInversions() {
		ArrayList<Node> temp = (ArrayList<Node>) nodes.clone();
		int inversions = 0;
		
		for (int i = 0; i < temp.size(); i++) {
			int x = temp.get(i).num;
			if (x != 0) {
				for (int j = i + 1; j < temp.size(); j++) {
					int y = temp.get(j).num;
					if (y != 0) {
						if (x > y) {
							inversions ++;
						}
					}
				}
			} else {
				continue;
			}
		}
		
		return inversions;
	}

	@Override
	public int compareTo(Graph o) {
		if (f < o.f) {
			return -1 ;
		} else if (f > o.f) {
			return 1;
		} else {
			if (steps < o.steps) {
				return -1;
			} else if (steps > o.steps) {
				return 1;
			} else {
				return 1;
			}
		}
	}
	
	@Override
	public String toString() {
		String[] in = new String[nodes.size()];
		
		if (nodes.isEmpty()) {
			return "empty graph";
		}
		for (Node n : nodes) {
			in[n.current] = Integer.toString(n.num);
		}
		return String.format("\nMove #: " + steps + "\n--------------\nG:" + steps + 
				"\nH:" + h + "\nF:" + f + "\n--------\n%s %s %s  |\n%s %s %s  |   Moved tile: " + nodeChanged + "\n%s %s %s  |\n--------", in[0], in[1], in[2], in[3], in[4], in[5], in[6], in[7], in[8]);
	}
	
}
