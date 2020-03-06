package ubung_2;

import java.io.*;
import java.util.*;
public class histo_auch_alt {
 public static void  main(String[]args) throws IOException {

	 	Map<Character, Object> Histogram = new HashMap<Character, Object>();
	 	Histogram.put('a', "A : ");
	 	Histogram.put('b', "B : ");
	 	Histogram.put('c', "C : ");
	 	Histogram.put('d', "D : ");
	 	Histogram.put('e', "E : ");
	 	Histogram.put('f', "F : ");
	 	Histogram.put('g', "G : ");
	 	Histogram.put('h', "H : ");
	 	Histogram.put('i', "I : ");
	 	Histogram.put('j', "J : ");
	 	Histogram.put('k', "K : ");
	 	Histogram.put('l', "L : ");
	 	Histogram.put('m', "M : ");
	 	Histogram.put('n', "N : ");
	 	Histogram.put('o', "O : ");
	 	Histogram.put('p', "P : ");
	 	Histogram.put('q', "Q : ");
	 	Histogram.put('r', "R : ");
	 	Histogram.put('s', "S : ");
	 	Histogram.put('t', "T : ");
	 	Histogram.put('u', "U : ");
	 	Histogram.put('v', "V : ");
	 	Histogram.put('w', "W : ");
	 	Histogram.put('x', "X : ");
	 	Histogram.put('y', "Y : ");
	 	Histogram.put('z', "Z : ");
	 
	 	File file = new File("This.txt");
	 	Scanner fr = new Scanner(file);
	 	String stringWithSpaces = "";
	 	String stringWithoutSpaces;
	 	

	 	PrintStream out = new PrintStream(new FileOutputStream("food.txt"));
	 	
	 	while (fr.hasNextLine()) {
	 		stringWithSpaces = stringWithSpaces + fr.nextLine();
   
	 	}
	 	stringWithoutSpaces = stringWithSpaces.replaceAll("\\s+","");
	 	String stringWithOnlyChars = stringWithoutSpaces.replaceAll("\r","");


			int count_a = 0;
	 	for (int index = 0; index < stringWithOnlyChars.length(); index++) {
	 		char bib = Character.toLowerCase(stringWithOnlyChars.charAt(index));

	 		switch(bib)
	 		{
	 		case 'a':
	 			Histogram.put('a', Histogram.get('a') + "*");
	 			break;
	 			}}
			Histogram.put('a', count_a);
			
			/*
	 		case 'b':
	 			Histogram.put('b', Histogram.get('b') + "*");
	 			break;
	 		case 'c':
	 			Histogram.put('c', Histogram.get('c') + "*");
	 			break;
	 		case 'd':
	 			Histogram.put('d', Histogram.get('d') + "*");
	 			break;
	 		case 'e':
	 			Histogram.put('e', Histogram.get('e') + "*");
	 			break;
	 		case 'f':
	 			Histogram.put('f', Histogram.get('f') + "*");
	 			break;
	 		case 'g':
	 			Histogram.put('g', Histogram.get('g') + "*");
	 			break;
	 		case 'h':
	 			Histogram.put('h', Histogram.get('h') + "*");
	 			break;
	 		case 'i':
	 			Histogram.put('i', Histogram.get('i') + "*");
	 			break;
	 		case 'j':
	 			Histogram.put('j', Histogram.get('j') + "*");
	 			break;
	 		case 'k':
	 			Histogram.put('k', Histogram.get('k') + "*");
	 			break;
	 		case 'l':
	 			Histogram.put('l', Histogram.get('l') + "*");
	 			break;
	 		case 'm':
	 			Histogram.put('m', Histogram.get('m') + "*");
	 			break;
	 		case 'n':
	 			Histogram.put('n', Histogram.get('n') + "*");
	 			break;
	 		case 'o':
	 			Histogram.put('o', Histogram.get('o') + "*");
	 			break;
	 		case 'p':
	 			Histogram.put('p', Histogram.get('p') + "*");
	 			break;
	 		case 'q':
	 			Histogram.put('q', Histogram.get('q') + "*");
	 			break;
	 		case 'r':
	 			Histogram.put('r', Histogram.get('r') + "*");
	 			break;
	 		case 's':
	 			Histogram.put('s', Histogram.get('s') + "*");
	 			break;
	 		case 't':
	 			Histogram.put('t', Histogram.get('t') + "*");
	 			break;
	 		case 'u':
	 			Histogram.put('u', Histogram.get('u') + "*");
	 			break;
	 		case 'v':
	 			Histogram.put('v', Histogram.get('v') + "*");
	 			break;
	 		case 'w':
	 			Histogram.put('w', Histogram.get('w') + "*");
	 			break;
	 		case 'x':
	 			Histogram.put('x', Histogram.get('x') + "*");
	 			break;
	 		case 'y':
	 			Histogram.put('y', Histogram.get('y') + "*");
	 			break;
	 		case 'z':
	 			Histogram.put('z', Histogram.get('z') + "*");
	 			break;		
	 			
	 		}}*/
	 		
	 	Iterator<Object> itr = Histogram.values().iterator();
	 	while (itr.hasNext()) {
	 		out.println(itr.next());
	 	}				
	 			 	
	 	
 	}
}

