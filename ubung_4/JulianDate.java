package ubung_4;

public class JulianDate extends MetricDate implements JD {

	private int JulianDate; 
	private int daysbetween; 
	private String ndaysago; 
	private String inNdays; 
	private int day; 
	private int month; 
	private int year; 
	private String dayoftheweek; 
	private String GregDate;
	private int [] DaysBeforeMonths = {0,31,59,90,120,151,181,212,243,273,304,334};
	
	
		@Override
		public int calculateJD(int date) {
	
			JulianDate = 0;
			
			
			day = date%100;
			month = (date-day)%10000/100;
			year = date/10000;

			year -= 1;
			JulianDate = day - 1;             
			while (year >= 1900)
			{
				if ((year%4 ==0 && year%100 != 0) || (year%400 == 0)) 
				{
					JulianDate +=366;
				}
				else {
					JulianDate += 365;
				}
				year--;
			}

			JulianDate += DaysBeforeMonths[month-1]; 

				
				year = date/10000;
				if (((year%4 ==0 && year%100 != 0) || (year%400 == 0)) && month>2)       // +1 day if it's a leap year and February has passed
				{
					JulianDate +=1;
					}
			
			return JulianDate;
		}
		

		

		@Override
		public int getdaysbetween(int date1, int date2) {
			if (calculateJD(date1) < calculateJD(date2))
			{
				daysbetween = calculateJD(date2) - calculateJD(date1);
			}
			else {
				daysbetween = calculateJD(date1) - calculateJD(date2);
			}
			return daysbetween;
		}
		
		@Override
		public String ndaysago(int date, int n) {
			ndaysago = jultogreg(calculateJD(date) - n);
			return ndaysago;
		}
		
		@Override
		public String inNdays(int date, int n) {
			inNdays = jultogreg(calculateJD(date) + n);
			return inNdays;
		}
		
		@Override
		public String dayoftheweek(int date) {

			switch((calculateJD(date)%7)+1)
			{
			case 1:
				dayoftheweek = "Monday";
				break;
			case 2:
				dayoftheweek = "Tuesday";
				break;
			case 3:
				dayoftheweek = "Wednesday";
				break;
			case 4:
				dayoftheweek = "Thursday";
				break;
			case 5:
				dayoftheweek = "Friday";
				break;
			case 6:
				dayoftheweek = "Saturday";
				break;
			case 7:
				dayoftheweek = "Sunday";
				break;
			}
			
			return dayoftheweek;
		}
		
		@Override
		public String jultogreg (int JulianDate)
		{	
			day = JulianDate+1;
		

			year = 1900;

			while (day > ((year%4 ==0 && year%100 != 0) || (year%400 == 0)?366:365))
			{
				day -= ((year%4 ==0 && year%100 != 0) || (year%400 == 0)?366:365);
			
				year++;
			}
			
			for (int i =11; i >-1; i--)
			{
				if (day >DaysBeforeMonths[i]) {
				day -= DaysBeforeMonths[i];
				month = i+1;
				break;
				}
			
			}
			
			GregDate = day +"." + month + "." + year;
			return GregDate ;
			
		}		
			

}
