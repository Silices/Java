package numeric_Systems;


public class Hexa implements HexaSystem {
	private String value;	
	
	public Hexa(String value) {
		setValue(value);
	}
	public String getValue() {
		return value;
	}
	public void setValue (String value) {
	
		char[] Cvalue = value.toCharArray();
		for(char E : Cvalue) {
			if((E < 48 && E > 57) || (E < 65 && E > 70)) {
				throw new IllegalArgumentException("only Chars from 1-9 and A-F");
			}
		}
		this.value = value;
	}
	@Override
	public Hexa Add(Hexa HEX) {
		
		int a = Hexa.HEXToDEC(this);
		int b = Hexa.HEXToDEC(HEX);
		
		return Hexa.DECtoHEX(a+b);
	}

	@Override
	public Hexa Subtract(Hexa HEX) {
		int a = Hexa.HEXToDEC(this);
		int b = Hexa.HEXToDEC(HEX);
		
		return Hexa.DECtoHEX(a-b);
	}

	@Override
	public Hexa Multiplycation(Hexa HEX) {
		int a = Hexa.HEXToDEC(this);
		int b = Hexa.HEXToDEC(HEX);
		
		return Hexa.DECtoHEX(a*b);
	}

	@Override
	public Hexa Division(Hexa HEX) {
		int a = Hexa.HEXToDEC(this);
		int b = Hexa.HEXToDEC(HEX);
		
		return Hexa.DECtoHEX(a/b);
	}
	public static int HEXToDEC(Hexa HEX) {	
		return Integer.parseInt(HEX.getValue(), 16);
	}
	public static Hexa DECtoHEX(int DEC) {		
		return new Hexa(Integer.toHexString(DEC).toUpperCase());
	}
	public String toString() {
		return value;
	}	
}
