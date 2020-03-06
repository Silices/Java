package ubung_2;
import java.io.*;
import java.lang.*;
import java.util.*;

public class histo_alt
{
 public static void  main(String[]args) throws IOException {
	 
	 File file = new File("Dokument.rtf");
	 Scanner fr = new Scanner(file);
	 String stringWithSpaces = "";
	 String stringWithoutSpaces;
  
	 while (fr.hasNextLine()) {
		 stringWithSpaces = stringWithSpaces + fr.nextLine(); 
		 }
	 
	 stringWithoutSpaces = stringWithSpaces.replaceAll("\\s+","");
	 String stringWithOnlyChars = stringWithoutSpaces.replaceAll("\r","");
	 
	 for (int i = 0; i < stringWithOnlyChars.length(); i++) {
		 System.out.println(stringWithOnlyChars.charAt(i));
    }
	 
	 
	 int number =16;
	 String word= "something";
	 
	 File files = new File("Dokument.txt");
	 PrintStream out = new PrintStream(new FileOutputStream("food.txt"));	
	 out.println(word);
	 out.println(number);
	 
 } 
   
   
  /* BufferedReader br= new BufferedReader(fr);
  int c = 0;
  while((c = br.read()) != -1)
  {
   char character = (char) c;
   System.out.println(character);
  }
  if (inputString == null)
  String stringWithoutSpaces = fr.replaceAll("\\s+","");
  String stringWithOnlyChars = stringWithoutSpaces.replaceAll("\r","");
  for (int i = 0; i < stringWithOnlyChars.length(); i++) {
   System.out.println(stringWithOnlyChars.charAt(i));
   }
   char toNormalise = 'b';
   Normalisation (toNormalise);
   
   Character.toLowerCase(toNormalise);
   Character.toUpperCase(toNormalise);
 }
 
 
 public char Normalisations (char toNormalise) {

	 char Normalised = 0;
	 switch (toNormalise) {
	 
	 case 'a': 
		 Normalised = 'A';
	 
 	 case 'A': 
 		Normalised = 'a';
 }	 
	 return Normalised ;
 }
 
 
 public static char Normalisation (char toNormalise) {

	 char Normalised = 0;
	 if (toNormalise <91 && toNormalise > 64)
	 {
		 Normalised = (char) (toNormalise + 32);
	 }
	 if (toNormalise <123 && toNormalise > 96)
	 {
		 Normalised = (char) (toNormalise - 32);
	 }

	 return Normalised ;
 }*/
}
