package ubung_12;

import java.util.HashSet;
import java.util.Set;

public class PermuteGenerator {
	
	//Array of Sets, are distinguished by the length of words
	//At 0 the biggest word.
	//At Array.length-1 one letter words.
	Set<String> permutations[];

	public PermuteGenerator(String source) {
		
		permutations = new Set[source.length()];
		for(int i = 0; i< permutations.length; i++) {
			permutations[i] = new HashSet<String>();
		}
		permutations[0] = UM.permute(source);
		
		//For all the lower number of letter permutations
		//safes the Base Permutations
		Set<String> base = UM.permute(source);	
		//we cant set base = permutation and safe the calc: 
		//unknown Error shows up, look picture
		for(int i = 1; i< source.length(); i++) {
			for(String s : base) {
				//gets all the 3 Letter Permutations(doubles are possible)
				//Sets cant have doubles to here we get rid of them
				permutations[i].add(s.substring(i));
			}
		}
	}
	
}
