package ubung_11;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Set;
import java.util.TreeSet;

import ubung9.Point;

public interface UM {

	public static String deleteSpaces(String s) {
		
		return s.replace("\\s+", "");
	}
	
	public static boolean StringisNumber(String number) {
		
		if(number.startsWith("."))
			return false;
					
		char[] cNumber = number.toCharArray();
		
		int i = 0;
		try {
			if(cNumber[0] == '-' && CharisNumberHEX(cNumber[1]))
				i = 1;
		}
		catch (Exception e) {}
		
		for (; i < cNumber.length ; i++) {
			if(!(CharisNumberHEX(cNumber[i]) || cNumber[i] == '.'))
				return false;
		}
		return true;
	}
	public static boolean CharisNumberHEX(char number) {
		if( number > 47 && number < 58 ||
			number > 64 && number < 71)
			return true;
		else
			return false;
	}
	public static String setSpacesEachToken(String S) {
		S = deleteSpaces(S);
		char [] S_Array = S.toCharArray();
		
		//If the input String has 2 dots in a row its wrong Tested here.
		int count = 0;
		for(char E : S_Array) {
			if(E == '.')
				count++;
			else
				count = 0;
			if(count >1)
				return null;	
		}
	
		String token = "";
		S = "";
		for(int i = 0; i< S_Array.length;i++) {
			token = "";
			
			//negativ numbers
			if(	S_Array[i] == '-') {
				token += S_Array[i];
				
				try {
					//if its an neg followed by a number
					if(CharisNumberHEX(S_Array[i+1])){
						//If - is the first digit:
						if(i==0) {	
							while(CharisNumberHEX(S_Array[i+1]) || S_Array[i+1] == '.') {
								i++;
								token += S_Array[i];	
							}
						}
						else {
							//if the - is not at the first digit
							if(CharisOperator(S_Array[i-1])) {		
								while(CharisNumberHEX(S_Array[i+1]) || S_Array[i+1] == '.') {
									i++;
									token += S_Array[i];						
								}
							}	
						}
					}
					//if the neg is followed by an (
					else if(S_Array[i+1] == '(') {
						i++;
						token += S_Array[i];
					}
				}
				catch(Exception e) {}
				S += token + " ";
				continue;
			}
			//operatoren
			if( i > 0  && CharisOperator(S_Array[i])) {
				token += S_Array[i];
				S += token + " ";
				continue;
			}
			//positive Zahlen
			else if(CharisNumberHEX(S_Array[i])){
				token = Character.toString(S_Array[i]);
				try {
					while(CharisNumberHEX(S_Array[i+1]) || S_Array[i+1] == '.') {
						i++;
						token += S_Array[i];	
					}
				}
				catch(Exception e) {}
				S += token + " ";
				continue;
			}
			else {
				//If input is wrong.
				return null;
			}
		}
		
		
		return S.stripTrailing();
	}
	public static boolean CharisToken(char c) {
		if(CharisNumberHEX(c) || CharisOperator(c)) {
			return true;
		}
		else 
			return false;
	}
	public static boolean CharisOperator(char c) {
		if( c == '+' ||
			c == '-' ||
			c == 'x' ||
			c == '*' ||
			c == '/' ||
			c == '^' ||
			c == '(' ||
			c == ')' ||
			c == '=') 
			return true;
		else
			return false;
	}
	public static String getSep(String input) {
		//if you want too really seaching for an sepperator u need a more complicated way, this is the bugded version
		if(input.contains("/"))
			return  "/";
		else if(input.contains("-"))
			return  "-";
		else if(input.contains("."))
			return  ".";
		else if(input.contains("_"))
			return  "_";
		return null;
	}
	public static int twoPointsDistance(int x1, int y1, int x2, int y2) {

		return (int) Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
	}
	public static Point getMidpoint(Point p1, Point p2) {

		return new Point((p1.getX()+p2.getX())/2,(p1.getY()+p2.getY())/2);
	}
	//doesnt work right now
	public static boolean isPrime (BigInteger n) {
		
		BigInteger THREE = BigInteger.TWO.add(BigInteger.ONE);
		
		if(0>n.compareTo(BigInteger.TWO))

			return false;
					 
		if(0 == n.compareTo(BigInteger.TWO) || 0 == n.compareTo(THREE))

			return true;
									 
		//https://www.tutorialspoint.com/java/math/biginteger_mod.htm 
		boolean first = n.mod(BigInteger.TWO).compareTo(BigInteger.ZERO) == 0;
		boolean second = n.mod(THREE).compareTo(BigInteger.ZERO) == 0;
		if(first || second)
			return false;
							
		//https://www.geeksforgeeks.org/biginteger-sqrt-method-in-java/
		BigInteger sqrtN = n.sqrt().add(BigInteger.ONE);
		
		BigInteger SIX = new BigInteger("6");
		BigInteger negativOne = new BigInteger("-1");
		
		for(BigInteger i = SIX; sqrtN.compareTo(i) != -1; i = i.add(SIX)) {
			first = n.mod(SIX.add(negativOne)).compareTo(BigInteger.ZERO) == 0;
			second = n.mod(SIX.add(BigInteger.ONE)).compareTo(BigInteger.ZERO) == 0;
			if(first||second ) {
				return false;
			}				
		}		
		return true;
	}
	public static boolean isPrime (long n) {
		if (n<0) {
			return false;}
		for (long i =2; i<n; i++){
			if (n%i == 0)
			{
				return false;
			}	
		}
		return true;
	}
	public static BigInteger pow(BigInteger base, BigInteger exponent) {
		  BigInteger result = BigInteger.ONE;
		  while (exponent.signum() > 0) {
		    if (exponent.testBit(0)) result = result.multiply(base);
		    base = base.multiply(base);
		    exponent = exponent.shiftRight(1);
		  }
		  return result;
	}
	public static String FileToString(String filePath) throws IOException {
		FileReader fr = new FileReader(filePath);
		BufferedReader br = new BufferedReader(fr);
		
		String outcome = "";
		
		while(br.ready()) {
			outcome += br.readLine() + " ";
		}

	
		System.out.println("File loader complete");
		return outcome;
	}
	
	//https://stackoverflow.com/questions/9666903/every-combination-of-character-array
	public static Set<String> permute(String chars){
	    // Use sets to eliminate semantic duplicates (aab is still aab even if you switch the two 'a's)
	    // Switch to HashSet for better performance
	    Set<String> set = new TreeSet<String>();

	    // Termination condition: only 1 permutation for a string of length 1
	    if (chars.length() == 1){
	      set.add(chars);
	    }
	    else{
	    	// Give each character a chance to be the first in the permuted string
	    	for (int i=0; i<chars.length(); i++){
	    		// Remove the character at index i from the string
	    		String pre = chars.substring(0, i);
	    		String post = chars.substring(i+1);
	    		String remaining = pre+post;

	    		// Recurse to find all the permutations of the remaining chars
	    		for (String permutation : permute(remaining)){
	    			// Concatenate the first character with the permutations of the remaining chars
	    			set.add(chars.charAt(i) + permutation);
	    		}
	    	}
	    }
	    return set;
	}
}

