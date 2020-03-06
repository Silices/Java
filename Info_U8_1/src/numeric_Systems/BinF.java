package numeric_Systems;

public class BinF implements BinFSystem {
	private String [] value;
	public String[] getValue() {
		return value;
	}

	public BinF(String value) {
		this.value = value.split("\\.");
		//If .x is missing.
		if(this.value.length==1) {
			String[] temp = new String[2];
			temp[0] = this.value[0];
			temp[1] = "0";
			this.value = temp;
		}
	}
	@Override
	public BinF Add(BinF BINF) {
		
		double a = BinFSystem.BINFToDEC(this);
		double b = BinFSystem.BINFToDEC(BINF);
		
		return BinFSystem.DECtoBINF(a+b);
	}
	@Override
	public BinF Subtract(BinF BINF) {
		
		double a = BinFSystem.BINFToDEC(this);
		double b = BinFSystem.BINFToDEC(BINF);
		
		return BinFSystem.DECtoBINF(a-b);
	}
	@Override
	public BinF Multiplication(BinF BINF) {
		
		double a = BinFSystem.BINFToDEC(this);
		double b = BinFSystem.BINFToDEC(BINF);
		
		return BinFSystem.DECtoBINF(a*b);
	}
	@Override
	public BinF Division(BinF BINF) {
		
		double a = BinFSystem.BINFToDEC(this);
		double b = BinFSystem.BINFToDEC(BINF);
		
		return BinFSystem.DECtoBINF(a/b);
	}
	public BinF Pow(BinF BINF) {
		
		double a = BinFSystem.BINFToDEC(this);
		double b = BinFSystem.BINFToDEC(BINF);
		
		return BinFSystem.DECtoBINF(Math.pow(a, b));
	}
	@Override
	public String toString() {
		return value[0] + "." + value[1];
	}
}
