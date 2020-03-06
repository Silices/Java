package ubung_4;

public class randopm {


	public static void main(String[] args) {
		
		JulianDate jd = new JulianDate();
		System.out.println(jd.calculateMD(130));

		
		
		

		int date = 20191104;
		int hour = 23;
		int min = 12;
		int sek = 3;

	
	}

	
	public double julianDate(int date) {  
		/*
		double JulianDate;
		int julianDaysNumber = julianDayNumber(date);
		JulianDate = (double) julianDaysNumber + (hour-12)/24 + min/1440 + sek/86400; */
		
		
		
		double JulianDate = 0;
		
		
		int day = date%100;
		int month = (date-day)%10000/100;
		int year = date/10000;


		day -= 1;              // we start our julian date at January the first 1900
		while (year-1 >= 1900)
		{
			if (year%4 ==0 && year != 1900 && year != 2100)
			{
				day +=366;
			}
			else {
				day += 365;
			}
			year--;
		}
		
		
		if (year%4 ==0 && year != 1900 && year != 2100 && month>2)       
		{
			day +=1;
			}
																//days passed in the current year if there it isn't a leap year
			switch(month) {
			
			case 1:  
				break;
			case 2: 
				day += 31; 
				break;
			case 3: 
				day += 59; 
				break;
			case 4: 
				day += 90; 
				break;
			case 5: 
				day += 120; 
				break;
			case 6: 
				day += 151; 
				break;
			case 7: 
				day += 181; 
				break;
			case 8: 
				day += 212; 
				break;
			case 9: 
				day += 243; 
				break;
			case 10: 
				day += 273; 
				break;

				
			}
		
		return day;
	}
	

	
	public static String jultogreg (int date)
	{	
		int JulianDate = date+1;
	
		int [] DaysBeforeMonths = {334,304};
	int day = 0;
	int month = 0;
	int year = 1900;

		while (JulianDate > 364)
		{
			JulianDate -= ((year%4 ==0 && year%100 != 0) || (year%400 == 0)?366:365);
		
			year++;
		}

		if (JulianDate >DaysBeforeMonths[0]) {
			JulianDate -= DaysBeforeMonths[0];
			month = 12;
		}
		
		
		String GregDate = JulianDate +"." + month + "." + year;
		return GregDate ;
		
	}		
		
}




