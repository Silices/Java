package ubung10_final;
import java.util.LinkedList;

public class VerticeWeighted {
	public int n;
	public String name;
	private boolean visited;
	LinkedList<EdgeWeighted> edges;
	
	public VerticeWeighted(int n, String name) {
		this.n = n;
		this.name = name;
		this.visited = false;
		this.edges = new LinkedList<>();
	}
	
	public boolean isVisited() {
		return visited;
	}
	public void visit() {
		visited = true;
	}
	public void unvisit() {
		visited = false;
	}
	
	public LinkedList<VerticeWeighted> getChildVertice() {
		LinkedList<VerticeWeighted> childVertices = new LinkedList<VerticeWeighted>();
		for(EdgeWeighted edge : edges) {
			if(n == edge.start.n)
				childVertices.add(edge.end);
			if(n == edge.end.n)
				childVertices.add(edge.start);
		}
		return childVertices;
	}
}

