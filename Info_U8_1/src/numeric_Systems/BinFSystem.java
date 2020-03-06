package numeric_Systems;

import ubung7.Usefull_Methods;

public interface BinFSystem {
	public static double BINFToDEC(BinF BINF) {
		
		int Bpoint = Bin.BINToDEC(new Bin(BINF.getValue()[0]));
		
		
		char[] c_Apoint = BINF.getValue()[1].toCharArray();
		double Apoint = 0;
		
		for(int i = 0 ; i< c_Apoint.length;i++) {
			int bin_Value = Bin.BINToDEC(new Bin (Character.toString(c_Apoint[i])));
			Apoint +=  bin_Value*Math.pow(2, -(i+1));
		}
		return Bpoint + Apoint;
	}
	public static BinF DECtoBINF(double DEC) {	
		
		Bin Bpoint = Bin.DECtoBIN((int) DEC);
		DEC -= (int) DEC;
		String Apoint = ".";
		int digit;
		
		for(int i = 0; i < 5; i++) {
			DEC *= 2;
			digit = (int) DEC;
			DEC -= digit;
			Apoint += Bin.DECtoBIN(digit);
			if(DEC == 0)
				break;
		}
		BinF output = new BinF(Bpoint + Apoint);
		return output;
	}
	public static String DECtoBINF_LINE(String dec_Line) {
		if(dec_Line == "")
			return "";
		String bin_Line = "";
		dec_Line = Usefull_Methods.setSpacesEachToken(dec_Line);
		String[] Tokens = dec_Line.split("\\s+");
		BinF number;
		
		for(String S : Tokens) {
			if(Usefull_Methods.StringisNumber(S)) {
				number = DECtoBINF(Double.parseDouble(S));
				bin_Line += number;
			}
			else {
				bin_Line += S;
			}
		}
		return bin_Line;
	}
	public BinF Add(BinF BINF);
	public BinF Subtract(BinF BINF);
	public BinF Multiplication(BinF BINF);
	public BinF Division(BinF BINF);
	public BinF Pow(BinF BINF);
	public String toString();
}
