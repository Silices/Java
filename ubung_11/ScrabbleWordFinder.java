package ubung_11;

public class ScrabbleWordFinder {
	
	
	public static void main(String[] args) throws Exception {
		
		//Filling dictionary
		MyHashTable<String> dictionary = new MyHashTable<String>();
		System.out.println("TableSize: " + dictionary.M);
		String filePath = "S:\\the real dictionary.txt";
		
	
		
		for(String word : UM.FileToString(filePath).split("\\s+")) {
			dictionary.add(word);
		}
		
		//Search for words with letters (sequence irrelevant)
		String bench = "eetstrl";
		for(String permute : dictionary.lookup(bench)) {
			System.out.println(permute);
		}
		
		System.out.println("Amount of empty lists: " +dictionary.getEmptyLists() + "%");
		//dictionary.printListsSize();
	}
}
