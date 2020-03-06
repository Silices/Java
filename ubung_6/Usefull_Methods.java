package ubung_6;

public class Usefull_Methods {

	public static String deleteSpaces(String s) {
		
		char[] cs = s.toCharArray();
		String output = "";
		for(char e : cs) {
			if(e != ' ') output += e;
		}	
		return output;
	}
}

