package ubung_11;

public interface HashTable {
	public void resize();
	public void add(String item);
	public boolean contains(String item);
	public void reset();
	public String toString();
	public void print();
}
