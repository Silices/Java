package ubung_12;

import java.util.Random;

public class ScrabbleWordFinder {
	
	
	public static void main(String[] args) throws Exception {
		
		//Filling dictionary
		MyHashTable<String> dictionary = new MyHashTable<String>();
		System.out.println("TableSize: " + dictionary.M);
		String filePath = "S:\\\\the real dictionary.txt";
		for(String word : UM.FileToString(filePath).split("\\s+")) {
			dictionary.add(word);
		}
		System.out.println("Amount of empty lists: " +dictionary.getEmptyLists() + "%");
		
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String bench = getRandomString(7, alphabet);
		
		PermuteGenerator allPermutes = new PermuteGenerator(bench);
		//our Bench
		
		System.out.println(bench);
		System.out.println("All that exits:");
		for(int i = 0; i< bench.length() ; i++) {
			System.out.println("All " + (bench.length()-i) + " letter words:");
			for(String s : allPermutes.permutations[i]) {
				if(dictionary.contains(s))
					System.out.println(s);
			}
		}
		
		//dictionary.printListsSize();
	}
	private static String getRandomString(int i, String chars) {
		String randomString = "";
		for(;i != 0; i--) {
			randomString += getRandomChar(chars);
		}
		return randomString;
	}
	private static char getRandomChar(String source) {
		Random r = new Random();
		return source.charAt(r.nextInt(source.length()));
	}
}
