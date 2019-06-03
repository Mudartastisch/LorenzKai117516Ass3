import java.util.ArrayList;
import java.util.Collections;
import java.util.Map.Entry;

/**
 * graphTemplate
 */
public class GraphTemplate {
	static ArrayList<Node> nodes = new ArrayList<Node>();
	static ArrayList<Node> minPriorityQueue = new ArrayList<Node>();

	public static ArrayList<Node> sortQ(ArrayList<Node> queue) {
		// QuickSort
		ArrayList<Node> left = new ArrayList<Node>();
		ArrayList<Node> right = new ArrayList<Node>();
		ArrayList<Node> pivotL = new ArrayList<Node>();
		ArrayList<Node> result = new ArrayList<Node>();
		int pivot = queue.size() / 2; //Select pivot in the middle

		if (queue.size() > 2) {
			pivotL.add(queue.get(pivot));
			for (int i = 0; i <= queue.size() - 1; i++) {
				if (i == pivot) {
					continue; //ignore pivot
				}
				if (queue.get(i).getDistance() < queue.get(pivot).getDistance()) { //put into appropriate side
					left.add(queue.get(i));
				} else {
					right.add(queue.get(i));
				}
			}

			if (left.size() >= 2) { //sub sort
				left = sortQ(left);
			}

			if (right.size() >= 2) { //sub sort
				right = sortQ(right);
			}
		}

		if (queue.size() == 2) { //smallest compare
			if (queue.get(0).getDistance() > queue.get(1).getDistance()) {
				Collections.swap(queue, 0, 1);
			}
			result.addAll(queue);
		}
		//combine all results
		result.addAll(left);
		result.addAll(pivotL);
		result.addAll(right);
		return result;

	}
	
	public static Node extractFirst(ArrayList<Node> queue) {
		Node temp = queue.get(0);
		queue.remove(queue.get(0));
		return temp;
	}
	
	public static void Dijkstra(Node source) {
		source.setDistance(0);
		source.setParent(source);
		minPriorityQueue.add(source); //select the starting element
		Node u;
		int dist;
		while(!minPriorityQueue.isEmpty()) {
			
			u = extractFirst(minPriorityQueue);		//using the element with the highest priority aka lowest distance		
			
			for(Entry<Node, Integer> v : u.adjacentNodes.entrySet()) {//select every connection
				dist = u.getDistance() + v.getValue(); //calculate distance to source
				if(minPriorityQueue.contains(v) && v.getValue() > dist) { //if the edge is in the queue and the new distance is lower
					v.getKey().setDistance(dist); //update the data
					v.getKey().setParent(u);
				}else {
					if(v.getKey().getParent() == null) { //if the edge is not yet in the queue
						v.getKey().setDistance(dist);//update data
						v.getKey().setParent(u);
						minPriorityQueue.add(v.getKey());//put in queue
					}
				}

			}
			sortQ(minPriorityQueue);
		}
		
		for(Node n : nodes) {//print
			System.out.println(n.toString());
		}
	}

	public static void main(String[] args) {
		Node Berlin = new Node("Berlin");
		Node Braunschweig = new Node("Braunschweig");
		Node Hannover = new Node("Hannover");
		Node München = new Node("München");
		Node Leipzig = new Node("Leipzig");
		Node Augsburg = new Node("Augsburg");
		Node Stuttgart = new Node("Stuttgart");
		Node Bremen = new Node("Bremen");
		Node Dortmund = new Node("Dortmund");
		Node Düsseldorf = new Node("Düsseldorf");
		Node Gelsenkirchen = new Node("Gelsenkirchen");
		Node Leverkusen = new Node("Leverkusen");
		Node Erfurt = new Node("Erfurt");
		Node Frankfurt = new Node("Frankfurt");
		Node Weimar = new Node("Weimar");
		Node Nürnberg = new Node("Nürnberg");
		Node Freiburg = new Node("Freiburg");
		Node Wiesbaden = new Node("Wiesbaden");
		Node Mannheim = new Node("Mannheim");
		Node Jena = new Node("Jena");
		Node Mönchengladbach = new Node("Mönchengladbach");
		
		nodes.add(Berlin);
		nodes.add(Braunschweig);
		nodes.add(Hannover);
		nodes.add(München);
		nodes.add(Leipzig);
		nodes.add(Augsburg);
		nodes.add(Stuttgart);
		nodes.add(Bremen);
		nodes.add(Dortmund);
		nodes.add(Düsseldorf);
		nodes.add(Gelsenkirchen);
		nodes.add(Leverkusen);
		nodes.add(Erfurt);
		nodes.add(Frankfurt);
		nodes.add(Weimar);
		nodes.add(Nürnberg);
		nodes.add(Freiburg);
		nodes.add(Wiesbaden);
		nodes.add(Mannheim);
		nodes.add(Jena);
		nodes.add(Mönchengladbach);
		
		
		Augsburg.addConnection( München, 80);
		Augsburg.addConnection( Stuttgart, 162);
		Berlin.addConnection(Braunschweig, 235);
		Berlin.addConnection( Hannover, 285);
		Berlin.addConnection( Leipzig, 190);
		Bremen.addConnection( Hannover, 116);
		Dortmund.addConnection( Bremen, 236);
		Dortmund.addConnection( Düsseldorf,  70);
		Düsseldorf.addConnection( Gelsenkirchen, 48);
		Düsseldorf.addConnection( Leverkusen, 33);
		Erfurt.addConnection( Berlin, 288);
		Erfurt.addConnection( Frankfurt, 257);
		Erfurt.addConnection( Weimar, 24);
		Frankfurt.addConnection( Hannover, 354);
		Frankfurt.addConnection( Leverkusen, 196);
		Frankfurt.addConnection( Nürnberg, 227);
		Frankfurt.addConnection( Wiesbaden, 38);
		Freiburg.addConnection( Bremen, 704);
		Freiburg.addConnection( Mannheim, 199);
		Gelsenkirchen.addConnection( Dortmund, 35);
		Hannover.addConnection( Braunschweig, 66);
		Hannover.addConnection( Dortmund, 212);
		Jena.addConnection( Nürnberg, 223);
		Leipzig.addConnection( Jena, 104);
		Leverkusen.addConnection( Mönchengladbach, 57);
		Leverkusen.addConnection( Wiesbaden, 171);
		Mannheim.addConnection( Frankfurt, 84);
		Mönchengladbach.addConnection( Düsseldorf, 31);
		München.addConnection( Berlin, 585);
		München.addConnection( Freiburg, 355);
		München.addConnection( Nürnberg, 169);
		Nürnberg.addConnection( Augsburg, 150);
		Nürnberg.addConnection( Stuttgart, 210);
		Stuttgart.addConnection( Freiburg, 176);
		Stuttgart.addConnection( Mannheim ,123);
		Weimar.addConnection( Jena, 24);
		Weimar.addConnection( Leipzig, 127);
		Wiesbaden.addConnection( Mannheim, 93);		

		Dijkstra(Berlin);

	}

	// TODO: implement additional constructors //Not needed
	// TODO: implement method for adding a node //No need to capsule nodes.add
	// TODO: implement method for removing a node //Only needed in extractFirst
	// TODO: implement method for sorting the min-priority Queue //sortQ
	// TODO: implement method for extracting an element from the min-priority Queue //extractFirst
	// TODO: implement Djikstra (in another class) //From how this template looks, it would create clutter

}
