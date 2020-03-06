package ubung10_final;

public class EdgeWeighted implements Comparable<EdgeWeighted>{
	public VerticeWeighted start;
	public VerticeWeighted end;
	public double weight;
	
	
	public EdgeWeighted(VerticeWeighted start, VerticeWeighted end, double weight){ 
		this.start = start;
		this.end = end;
		this.weight = weight;
	}
	
	@Override
	public String toString() {
		return String.format("(%s -> %s, %f)",start.name, end.name, weight);
	}
	
	@Override
	public int compareTo(EdgeWeighted otherEdge) {
		if(this.weight > otherEdge.weight)
			return 1;
		else
			return -1;
	}
}
