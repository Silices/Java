package ubung_4_5;

public class EU_Flag {

	public static void main(String[] args) {
		
		String outputLine;  
		for (int row = 1; row <= 40; row++){      
		    outputLine = "";      
		    for (int column = 1; column <= 40; column++){
		        outputLine = outputLine+determineCharacter (column, row);
		    }      
		    System.out.println (outputLine);
		}  
	}
	
	public static char determineCharacter (int column, int row) {
		char symbol = 0;
		int addition = column + row;
		
		if (column%5==0 && row <= (40-column))					//vertical lines
			symbol = '(';
		if (row %3 == 0 && column >= (38-row+3))				//horizontal lines
			symbol = ')';
		
		if (column == 17 && row <=16)							//vertical box lines
			symbol = '|';
		if (row == 17 && column <=17)							//horizontal box lines
			symbol = '-';
		if (column <=16 && row <= 16)							//box interrior
			{
				if (addition%3 == 0)
					symbol = '/';
				if (addition%3 ==1)
					symbol = '=';
				if (addition%3 ==2)
					symbol = ' ';
			}
		if (addition == 41)										//diagonal line
			symbol = '+';
		if (column <=2 || column >=39 || row <=2 || row >= 39)	// frame
			symbol = '?';
		
		return symbol;
	}
	
}

