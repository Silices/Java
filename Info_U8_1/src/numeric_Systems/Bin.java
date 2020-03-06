package numeric_Systems;

public class Bin implements BinSystem {

	String value;
	
	public Bin(String value) {
		setValue(value);
	}
	@Override
	public Bin Add(Bin bin) {
		int a = Bin.BINToDEC(this);
		int b = Bin.BINToDEC(bin);
		
		return Bin.DECtoBIN(a+b);
	}
	@Override
	public Bin Subtract(Bin bin) throws Exception {
		int a = Bin.BINToDEC(this);
		int b = Bin.BINToDEC(bin);
		if(a<b) throw new Exception("B is bigger then A");
		
		return Bin.DECtoBIN(a-b);
	}
	@Override
	public Bin Multiplycation(Bin bin) {
		int a = Bin.BINToDEC(this);
		int b = Bin.BINToDEC(bin);
		
		return Bin.DECtoBIN(a*b);
	}
	@Override
	public Bin Division(Bin bin) {
		int a = Bin.BINToDEC(this);
		int b = Bin.BINToDEC(bin);
		
		return Bin.DECtoBIN(a/b);
	}
	public static int BINToDEC(Bin bin) {	
		return Integer.parseInt(bin.getValue(), 2);
	}
	public static Bin DECtoBIN(int DEC) {
		return new Bin(Integer.toBinaryString(DEC));
	}
	public String toString() {
		return value;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
