package ubung10_final;

public interface WeightedGraph {
	
	void addVertice(VerticeWeighted...n);
	void addEdge(VerticeWeighted start, VerticeWeighted end, double weight);
	void addEdgeHelper(VerticeWeighted start, VerticeWeighted end, double weight);
	void printEdges();
	boolean hasEdge(VerticeWeighted start, VerticeWeighted end);
	void resetVerticeVisited();


}

