package numeric_Systems;

import ubung7.Usefull_Methods;

public interface HexaFSystem {
	
	public static double HEXFToDEC(HexaF HEXF) {
		
		int Bpoint = Hexa.HEXToDEC(new Hexa(HEXF.getValue()[0]));
		
		
		char[] c_Apoint = HEXF.getValue()[1].toCharArray();
		double Apoint = 0;
		
		for(int i = 0 ; i< c_Apoint.length;i++) {
			int hex_Value = Hexa.HEXToDEC(new Hexa (Character.toString(c_Apoint[i])));
			Apoint +=  hex_Value*Math.pow(16, -(i+1));
		}
		return Bpoint + Apoint;
	}
	public static HexaF DECtoHEXF(double DEC) {	
		
		Hexa Bpoint = Hexa.DECtoHEX((int) DEC);
		DEC -= (int) DEC;
		String Apoint = ".";
		int digit;
		
		for(int i = 0; i < 5; i++) {
			DEC *= 16;
			digit = (int) DEC;
			DEC -= digit;
			Apoint += Hexa.DECtoHEX(digit);
			if(DEC == 0)
				break;
		}
		HexaF output = new HexaF(Bpoint + Apoint);
		return output;
	}
	public static String DECtoHEXF_LINE(String dec_Line) {
		if(dec_Line == "")
			return "";
		String hex_Line = "";
		dec_Line = Usefull_Methods.setSpacesEachToken(dec_Line);
		String[] Tokens = dec_Line.split("\\s+");
		HexaF number;
		
		for(String S : Tokens) {
			if(Usefull_Methods.StringisNumber(S)) {
				number = DECtoHEXF(Double.parseDouble(S));
				hex_Line += number;
			}
			else {
				hex_Line += S;
			}
		}
		return hex_Line;
	}
	public HexaF Add(HexaF HEXF);
	public HexaF Subtract(HexaF HEXF);
	public HexaF Multiplication(HexaF HEXF);
	public HexaF Division(HexaF HEXF);
	public HexaF Pow(HexaF HEXF);
	public String toString();
}
