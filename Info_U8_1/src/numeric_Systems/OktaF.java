package numeric_Systems;

public class OktaF implements OktaFSystem {
	private String [] value;
	public String[] getValue() {
		return value;
	}

	public OktaF(String value) {
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
	public String toString() {
		return value[0] + "." + value[1];
	}

	@Override
	public OktaF Add(OktaF OKTF) {
		double a = OktaFSystem.OKTFToDEC(this);
		double b = OktaFSystem.OKTFToDEC(OKTF);
		
		return OktaFSystem.DECtoOKTF(a+b);
	}

	@Override
	public OktaF Subtract(OktaF OKTF) {
		double a = OktaFSystem.OKTFToDEC(this);
		double b = OktaFSystem.OKTFToDEC(OKTF);
		
		return OktaFSystem.DECtoOKTF(a-b);
	}

	@Override
	public OktaF Multiplication(OktaF OKTF) {
		double a = OktaFSystem.OKTFToDEC(this);
		double b = OktaFSystem.OKTFToDEC(OKTF);
		
		return OktaFSystem.DECtoOKTF(a*b);
	}

	@Override
	public OktaF Division(OktaF OKTF) {
		double a = OktaFSystem.OKTFToDEC(this);
		double b = OktaFSystem.OKTFToDEC(OKTF);
		
		return OktaFSystem.DECtoOKTF(a/b);
	}

	@Override
	public OktaF Pow(OktaF OKTF) {
		double a = OktaFSystem.OKTFToDEC(this);
		double b = OktaFSystem.OKTFToDEC(OKTF);
		
		return OktaFSystem.DECtoOKTF(Math.pow(a, b));
	}
}
