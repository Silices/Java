package ubung10_final;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Set;




public class GraphWeighted implements WeightedGraph{
	private Set<VerticeWeighted> vertices;
	private boolean directed;
	
	public GraphWeighted(boolean directed) {
		this.directed = directed;
		vertices = new HashSet<>();
	}
	@Override
	public void addVertice(VerticeWeighted...n) {
		vertices.addAll(Arrays.asList(n));
	}
	@Override
	public void addEdge(VerticeWeighted start, VerticeWeighted end, double weight) {
		
		vertices.add(start);
		vertices.add(end);
		
		//Making sure, that we dont have duplicated edges.
		addEdgeHelper(start, end, weight);
		if(!directed && start != end) {
			addEdgeHelper(end, start, weight);
		}
		
	}
	@Override
	public void addEdgeHelper(VerticeWeighted start, VerticeWeighted end, double weight) {

		    for (EdgeWeighted edge : start.edges) {
		        if (edge.start.equals(start) && edge.end.equals(end)) {
		            edge.weight = weight;
		            return;
		        }
		    }
		    start.edges.add(new EdgeWeighted(start, end, weight));
	
		
	}
	
	@Override
	public void printEdges() {
		for(VerticeWeighted vertice : vertices) {
			LinkedList<EdgeWeighted> edges = vertice.edges;
			
			if(edges.isEmpty()) {
				System.out.println("Vertice " + vertice.name + " has no edges.");
				continue;
			}
			System.out.println("Vertice " + vertice.name + " has edges to:");
			
			for(EdgeWeighted edge : edges) {
				System.out.println(edge.end.name + "(" + edge.weight + ")");
			}
			System.out.println();
		}
	}
	@Override
	public boolean hasEdge(VerticeWeighted start, VerticeWeighted end) {
	    LinkedList<EdgeWeighted> edges = start.edges;
	    for (EdgeWeighted edge : edges) {
	        if (edge.end == end) {
	            return true;
	        }
	    }
	    return false;
	}
	@Override
	public void resetVerticeVisited() {
		for(VerticeWeighted vertice : vertices) {
			vertice.unvisit();
		}
	}
	
	public LinkedList<VerticeWeighted> DijkstraCheapestPath(VerticeWeighted start, VerticeWeighted end) {
		
		LinkedList<VerticeWeighted> direction = new LinkedList<VerticeWeighted>();
		
	    // We keep track of which path gives us the shortest path for each vertice
	    // by keeping track how we arrived at a particular vertice, we effectively
	    // keep a "pointer" to the parent vertice of each vertice, and we follow that
	    // path to the start
	    HashMap<VerticeWeighted, VerticeWeighted> changedAt = new HashMap<>();
	    changedAt.put(start, null);

	    // Keeps track of the shortest path we've found so far for every vertice
	    HashMap<VerticeWeighted, Double> shortestPathMap = new HashMap<>();

	    // Setting every vertice`s shortest path weight to positive infinity to start
	    // except the starting vertice, whose shortest path weight is 0
	    for (VerticeWeighted vertice : vertices) {
	        if (vertice == start)
	            shortestPathMap.put(start, 0.0);
	        else shortestPathMap.put(vertice, Double.POSITIVE_INFINITY);
	    }

	    // Now we go through all the vertice we can go to from the starting vertice
	    // (this keeps the loop a bit simpler)
	    for (EdgeWeighted edge : start.edges) {
	        shortestPathMap.put(edge.end, edge.weight);
	        changedAt.put(edge.end, start);
	    }
	    //added by Juri
	    if(start != end)
	    	start.visit();
	    
	    // This loop runs as long as there is an unvisited vertice that we can
	    // reach from any of the vertices we could till then
	    while (true) {

	    	VerticeWeighted currentVertice = closestReachableUnvisited(shortestPathMap);
	    	
	        // If we haven't reached the end Vertice yet, and there isn't another
	        // reachable vertice the path between start and end doesn't exist
	        // (they aren't connected)
	        if (currentVertice == null) {
	            System.out.println("There isn't a path between " + start.name + " and " + end.name);
	            return null;
	        }
	        // If the closest non-visited Vertice is our destination, we want to print the path
	        if (currentVertice == end) {
	        	direction = printResult(start, currentVertice, changedAt, direction, true);
	            return direction;
	        }
	        currentVertice.visit();

	        // Now we go through all the unvisited vertices our current vertice has an edge to
	        // and check whether its shortest path value is better when going through our
	        // current vertice than whatever we had before
	        for (EdgeWeighted edge : currentVertice.edges) {
	            if (edge.end.isVisited())
	                continue;

	            if (shortestPathMap.get(currentVertice) + edge.weight < shortestPathMap.get(edge.end)) {
	                shortestPathMap.put(edge.end,shortestPathMap.get(currentVertice) + edge.weight);
	                changedAt.put(edge.end, currentVertice);
	            }
	        }
	    }
	}
	public LinkedList<VerticeWeighted> DijkstraShortestPath(VerticeWeighted start, VerticeWeighted end) {
		
		//https://stackoverflow.com/questions/1579399/shortest-path-fewest-nodes-for-unweighted-graph
		HashMap<VerticeWeighted, VerticeWeighted> changedAt = new HashMap<>();
		
		LinkedList<VerticeWeighted> direction = new LinkedList<VerticeWeighted>();
		
		Queue q = new LinkedList();
		
		VerticeWeighted currentVertice = start;
		q.add(currentVertice);
		
		start.visit();
		
		while(!q.isEmpty()) {
			currentVertice = (VerticeWeighted) q.remove();
			if(currentVertice.equals(end)) {
				break;
			}
			else {
				for(VerticeWeighted vertice : currentVertice.getChildVertice()) {
					if(!vertice.isVisited()) {
						q.add(vertice);
						vertice.visit();
						changedAt.put(vertice, currentVertice);
					}
				}
			}
		}
		if(!currentVertice.equals(end)) {
			System.out.println("There isn't a path between " + start.name + " and " + end.name);
			return null;
		}


		direction = printResult(start, end, changedAt, direction, false);
		return direction;
	}
	
	private VerticeWeighted closestReachableUnvisited(HashMap<VerticeWeighted, Double> shortestPathMap) {
		double shortestDistance = Double.POSITIVE_INFINITY;
		VerticeWeighted closestReachableVertice = null;
		
		for(VerticeWeighted vertice : vertices) {
			if(vertice.isVisited())
				continue;
			
			double currentDistance = shortestPathMap.get(vertice);
			if(currentDistance == Double.POSITIVE_INFINITY)
				continue;
			if(currentDistance < shortestDistance) {
				shortestDistance = currentDistance;
				closestReachableVertice = vertice;
			}
		}
		return closestReachableVertice;
	}
	
	public void getRandomGraph(int vertices, int edges) {
		//reset the current
		this.vertices = new HashSet<>();
		
		for(int i = 0; i<vertices;i++) {
			this.vertices.add(new VerticeWeighted(i, Integer.toString(i)));
		}
		for(int i = 0; i<edges;i++) {
			VerticeWeighted randomV1 = getRandomVertice();
			VerticeWeighted randomV2 = getRandomVertice();;

			addEdge(randomV1, randomV2, Math.random()*100);

		}
	}
	
	public VerticeWeighted getRandomVertice() {
		int size = vertices.size();
		int item = new Random().nextInt(size);
		int i = 0;
		for(VerticeWeighted vertice : vertices) {
			if(i == item)
				return vertice;
			i++;
		}
		return null;
	}
	public EdgeWeighted getEdge(VerticeWeighted V1, VerticeWeighted V2) {
		boolean isChild = false;
		for(VerticeWeighted vertice : V1.getChildVertice()) {
			if(V2.equals(vertice))
				isChild = true;
			
		}
		if(!isChild) {
			System.out.println("Not a Child");
			return null;
		}
			
		for(EdgeWeighted edge : V1.edges) {
			if(edge.start == V1 && edge.end == V2 ||
			   edge.start == V2 && edge.end == V1) 
				return edge;
		}
		System.out.println("Not possible");
		return null;
	}

	public void randomShortestPath()
	{
		VerticeWeighted randomV1 = getRandomVertice();
		VerticeWeighted randomV2 = getRandomVertice();
        DijkstraShortestPath(randomV1, randomV2);
	}
	
	public void randomCheapestPath()
	{
		VerticeWeighted randomV1 = getRandomVertice();
		VerticeWeighted randomV2 = getRandomVertice();
        DijkstraCheapestPath(randomV1, randomV2);
	}
	public LinkedList<VerticeWeighted> printResult(VerticeWeighted start, VerticeWeighted end, 
	HashMap<VerticeWeighted, VerticeWeighted> changedAt, LinkedList<VerticeWeighted> direction, boolean isCheap)
	{
		String word = "amout of steps";
		if (isCheap) 
			word = "weight";
		
		for(VerticeWeighted vertice = end; vertice != null; vertice = changedAt.get(vertice)) {
			direction.add(vertice);
			if(vertice == start)
				break;
		}
		Collections.reverse(direction);
		
		System.out.println("The path with the lowest " + word +" between "
                + start.name + " and " + end.name + " is:");
		String path = ""; 
		int steps = -1;
		VerticeWeighted prefVertice = null;
		double weight = 0.0;
		for(VerticeWeighted vertice : direction) {
			
			if(prefVertice != null) {
				weight += getEdge(prefVertice, vertice).weight;
			}
			path += vertice.name + " -> ";
			steps++;
			prefVertice = vertice;
		}
		path = path.substring(0, path.length()-3);
		System.out.println(path);
		System.out.println("The path costs: " + weight);
		System.out.println("And takes: " + steps + " steps.");
		return direction;
	}
}