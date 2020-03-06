package numeric_Systems;

import ubung7.Usefull_Methods;

public class HexaF implements HexaFSystem{
	
	private String [] value;
	public String[] getValue() {
		return value;
	}

	public HexaF(String value) {
		this.value = value.split("\\.");
		//If .x is missing.
		if(this.value.length==1) {
			String[] temp = new String[2];
			temp[0] = this.value[0];
			temp[1] = "0";
			this.value = temp;
		}
	}
	public HexaF Add(HexaF HEXF) {
		
		double a = HexaFSystem.HEXFToDEC(this);
		double b = HexaFSystem.HEXFToDEC(HEXF);
		
		return HexaFSystem.DECtoHEXF(a+b);
	}
	public HexaF Subtract(HexaF HEXF) {
		
		double a = HexaFSystem.HEXFToDEC(this);
		double b = HexaFSystem.HEXFToDEC(HEXF);
		
		return HexaFSystem.DECtoHEXF(a-b);
	}
	public HexaF Multiplication(HexaF HEXF) {
		
		double a = HexaFSystem.HEXFToDEC(this);
		double b = HexaFSystem.HEXFToDEC(HEXF);
		
		return HexaFSystem.DECtoHEXF(a*b);
	}
	public HexaF Division(HexaF HEXF) {
		
		double a = HexaFSystem.HEXFToDEC(this);
		double b = HexaFSystem.HEXFToDEC(HEXF);
		
		return HexaFSystem.DECtoHEXF(a/b);
	}
	public HexaF Pow(HexaF HEXF) {
		
		double a = HexaFSystem.HEXFToDEC(this);
		double b = HexaFSystem.HEXFToDEC(HEXF);
		
		return HexaFSystem.DECtoHEXF(Math.pow(a, b));
	}
	public String toString() {
		return value[0] + "." + value[1];
	}
}
