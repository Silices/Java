package ubung_2;

import java.io.*;
import java.util.*;
public class histogram {
 public static void  main(String[]args) throws IOException {

	 	Map<Character, Object> Histogram = new HashMap<Character, Object>();
	 	
	 	for (int index = 0; index<=127; index++) //0-127 length of the ASCII table
	 	{
	 		
	 		char Character = (char) index;
		 	Histogram.put(Character, Character +" : ");
	 	}

	 	File file = new File("This.txt");
	 	Scanner sc = new Scanner(file);
	 	String stringWithSpaces = "";
	 	String stringWithoutSpaces;
	 	
	 	
	 	while (sc.hasNextLine()) {
	 		stringWithSpaces = stringWithSpaces + sc.nextLine();				// input to string
   
	 	}
	 	stringWithoutSpaces = stringWithSpaces.replaceAll("\\s+","");			//deletes spaces
	 	String stringWithOnlyChars = stringWithoutSpaces.replaceAll("\r","");	//deletes carriage returns


	 	for (int index = 0; index < stringWithOnlyChars.length(); index++) {
	 		char bib = stringWithOnlyChars.charAt(index);
	 		
	 		
	 		Histogram.put(bib, Histogram.get( bib) + "*");

	 		}

	 	PrintStream out = new PrintStream(new FileOutputStream("frequency.txt"));	//histogram output	
	 	Iterator<Object> itr = Histogram.values().iterator();
	 	while (itr.hasNext()) {
	 		out.println(itr.next());
	 	}				
	 			 	
	 	
 	}
}

