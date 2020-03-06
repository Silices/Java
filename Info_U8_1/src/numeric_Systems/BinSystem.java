package numeric_Systems;

public interface BinSystem {

	public Bin Add(Bin bin);
	public Bin Subtract(Bin bin) throws Exception;
	public Bin Multiplycation(Bin bin);
	public Bin Division(Bin bin);
	public String toString();
}
