package ADT;

public interface JulianDate {
	
	static GregorDate FirstDay = new GregorDate(4712,1,1, false);

	
	public String get_Weekday();
	int daysBetween(GregorDate Day);
	GregorDate addDays(int Days) throws Exception;
	GregorDate subtractDays(int Days) throws Exception;
	
	public static GregorDate get_FirstDay() {
		return FirstDay;
	}
	public static GregorDate JulianToGregor(int JD) {
		
		GregorDate outcome = new GregorDate();
		
		int JD0 = 1721426;
		int N400 = (JD-JD0)/146097;
		int R400 = (JD-JD0)%146097;
		int N100 = R400/36524;
		int R100 = R400%36524;
		int N4 = R100/1461;
		int R4 = R100%1461;
		int N1 = R4/365;
		int LT = R4%365;
		
		int LJ = 400*N400 + 100*N100 + 4*N4 + N1;
		
		outcome.setYear(LJ+1);
		outcome.setMonth((LT+1)/30 + 1);
		outcome.setDay(GregorDate.DayOfYeartoDayofMonth(LT + 1, outcome.getYear()));
				
		return outcome;		
	}
	//https://de.wikipedia.org/wiki/Umrechnung_zwischen_julianischem_Datum_und_gregorianischem_Kalender
	public static int GregorToJulian(GregorDate Day) {
		int JD;
		int JD0 = 1721426;
		int LJ = Day.getYear()-1;
		int N400 = LJ/400;
		int R400 = LJ%400;
		int N100 = R400/100;
		int R100 = R400%100;
		int N4 = R100/4;
		int N1 = R100%4;
		int LT = Day.getDayOfTheYear()-1;
		
		//-1 weil getDayOFTheYear bei 1 anfängt und nicht wie in der Formel bei 0
		JD = JD0 + N400*146097 + N100*36524 + N4*1461 + N1*365 + LT;
				
		return JD;		
	}
}
