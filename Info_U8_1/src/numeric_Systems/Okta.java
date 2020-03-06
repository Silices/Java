package numeric_Systems;

public class Okta implements OktSystem{

	String value;
	
	
	
	
	public Okta(String value) {
		setValue(value);
	}
	@Override
	public Okta Add(Okta OKT) {
		int a = Okta.OKTToDEC(this);
		int b = Okta.OKTToDEC(OKT);
		
		return Okta.DECtoOKT(a+b);
	}
	@Override
	public Okta Subtract(Okta OKT) throws Exception {
		int a = Okta.OKTToDEC(this);
		int b = Okta.OKTToDEC(OKT);
		
		return Okta.DECtoOKT(a-b);
	}
	@Override
	public Okta Multiplycation(Okta OKT) {
		int a = Okta.OKTToDEC(this);
		int b = Okta.OKTToDEC(OKT);
		
		return Okta.DECtoOKT(a*b);
	}
	@Override
	public Okta Division(Okta OKT) {
		int a = Okta.OKTToDEC(this);
		int b = Okta.OKTToDEC(OKT);
		
		return Okta.DECtoOKT(a/b);
	}
	public static int OKTToDEC(Okta OKT) {	
		return Integer.parseInt(OKT.getValue(), 8);
	}
	public static Okta DECtoOKT(int DEC) {
		return new Okta(Integer.toOctalString(DEC));
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		
		char[] Cvalue = value.toCharArray();
		for(char E : Cvalue) {
			if(E < 48 && E > 55) {
				throw new IllegalArgumentException("only Chars from 1-7");
			}
		}
		this.value = value;
	}
	public String toString() {
		return value;
	}

}
