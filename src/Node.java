import java.util.HashMap;

public class Node{
    String label;
    HashMap<Node,Integer> adjacentNodes; // Pair connected node + weight
    Node parent;
    int distance;

    public Node(){
        label = "";
        adjacentNodes = new HashMap<Node,Integer>();
        parent = null;
        distance = Integer.MAX_VALUE;
    }

    public Node(String name){
        label = name;
        adjacentNodes = new HashMap<Node, Integer>();
        parent = null;
        distance = Integer.MAX_VALUE;
    }
    
    public Node(String name, int d){
        label = name;
        adjacentNodes = new HashMap<Node, Integer>();
        parent = null;
        distance = d;
    }

	public Node(String label, HashMap<Node, Integer> adjacentNodes, Node parent, int distance) {
		this.label = label;
		this.adjacentNodes = adjacentNodes;
		this.parent = parent;
		this.distance = distance;
	}
	
	public void addConnection(Node n, int d) {
		adjacentNodes.put(n, d);
	}
	
	public void removeConnection(Node n) {
		
		if(adjacentNodes.containsKey(n)) {
			adjacentNodes.remove(n);
		}
		/*
		else {
			System.err.println("Node: " + n.toString() + " does not exist");
		}
		*/
	}
	
	public void setParent(Node n) {
		parent = n;
	}
    
	public Node getParent() {
		return parent;			
	}
	
	public void setDistance(int d) {
		distance = d;
	}
	
	public int getDistance() {
		return distance;
	}
	
	public String toString() {
		return label + " " + Integer.toString(distance);
	}

}