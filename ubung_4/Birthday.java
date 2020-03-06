package ubung_4;


import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Birthday {

	public static void main(String[] args) throws IOException {
		JulianDate jd = new JulianDate();

		Date date = new Date(); 
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");		//todays date
		int dateInt = Integer.parseInt( formatter.format(date));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	

		System.out.println("What is you date of Birth?" + "\n" + "Please write it in the following format: yyyyMMdd");

		int input = Integer.parseInt( br.readLine());
		
		int InputDaysBetween = jd.getdaysbetween(input, dateInt);
		String InputWeekday = jd.dayoftheweek(input);
			
		
		System.out.println("You were born on a " + InputWeekday +", " + InputDaysBetween + " days ago!");
		
		
		if (input%10000 == dateInt%10000) {		//if today is birthday
			System.out.println("Look at that, we've got a birthday kid! Here's a present for you: " + "\n" +" __OO__" 
					+ "\n" + "|  |  |" + "\n" + "|--|--|"+ "\n" + "|__|__|");	
		}
	
		if (InputDaysBetween%100 ==0)     		//if birthday divisible by 100
		{
			System.out.println("That's exactly " + InputDaysBetween/100 + " times 100 days, today must be your lucky day!" );
		}
	}
		
	
	
}
