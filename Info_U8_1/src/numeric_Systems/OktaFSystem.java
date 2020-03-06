package numeric_Systems;

import ubung7.Usefull_Methods;

public interface OktaFSystem {
	
	public static double OKTFToDEC(OktaF OKTF) {
		
		int Bpoint = Okta.OKTToDEC(new Okta(OKTF.getValue()[0]));
		
		
		char[] c_Apoint = OKTF.getValue()[1].toCharArray();
		double Apoint = 0;
		
		for(int i = 0 ; i< c_Apoint.length;i++) {
			int hex_Value = Okta.OKTToDEC(new Okta(Character.toString(c_Apoint[i])));
			Apoint +=  hex_Value*Math.pow(8, -(i+1));
		}
		return Bpoint + Apoint;
	}
	public static OktaF DECtoOKTF(double DEC) {	
		
		Okta Bpoint = Okta.DECtoOKT((int) DEC);
		DEC -= (int) DEC;
		String Apoint = ".";
		int digit;
		
		for(int i = 0; i < 5; i++) {
			DEC *= 8;
			digit = (int) DEC;
			DEC -= digit;
			Apoint += Okta.DECtoOKT(digit);
			if(DEC == 0)
				break;
		}
		OktaF output = new OktaF(Bpoint + Apoint);
		return output;
	}
	public static String DECtoOKTF_LINE(String dec_Line) {
		if(dec_Line == "")
			return "";
		String okt_Line = "";
		dec_Line = Usefull_Methods.setSpacesEachToken(dec_Line);
		String[] Tokens = dec_Line.split("\\s+");
		OktaF number;
		
		for(String S : Tokens) {
			if(Usefull_Methods.StringisNumber(S)) {
				number = DECtoOKTF(Double.parseDouble(S));
				okt_Line += number;
			}
			else {
				okt_Line += S;
			}
		}
		return okt_Line;
	}
	public OktaF Add(OktaF OKTF);
	public OktaF Subtract(OktaF OKTF);
	public OktaF Multiplication(OktaF OKTF);
	public OktaF Division(OktaF OKTF);
	public OktaF Pow(OktaF OKTF);
	public String toString();
}
