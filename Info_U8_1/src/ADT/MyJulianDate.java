package ADT;


public class MyJulianDate implements JulianDate {

	private GregorDate Day;
	public GregorDate getDay() {
		return Day;
	}
	public void setDay(GregorDate day) {
		Day = day;
	}
	
	public MyJulianDate(GregorDate Day) {
		this.Day = Day;
	}
	public MyJulianDate(int Day) {
		this.Day = JulianDate.JulianToGregor(Day);
	}
	

	@Override
	public int daysBetween(GregorDate Day) {
				
		int JDay1 = JulianDate.GregorToJulian(this.Day);
		int JDay2 = JulianDate.GregorToJulian(Day);
		return Math.abs(JDay2-JDay1);
	}
	@Override
	public String get_Weekday() {
		
		int Weekday = (JulianDate.GregorToJulian(Day)+1)%7;
		
		switch(Weekday) {
			case 0:
				return "Sunday";
			case 1:
				return "Monday";
			case 2:
				return "Tuesday";
			case 3:
				return "Wednesday";
			case 4:
				return "Thursday";
			case 5:
				return "Friday";
			case 6:
				return "Saturday";
		}
		return null;	
	}

	@Override
	public GregorDate addDays(int Days) throws Exception {
		//no negativ JulianDates allowed
		if(JulianDate.GregorToJulian(this.Day)+Days<0)
			throw new Exception("negativ JulianDate not exsistent");
		else 
			return JulianDate.JulianToGregor(JulianDate.GregorToJulian(this.Day)+Days);
	}
	@Override
	public GregorDate subtractDays(int Days) throws Exception {
		//no negativ JulianDates allowed
		if(JulianDate.GregorToJulian(this.Day)-Days<0)
			throw new Exception("negativ JulianDate not exsistent");
		else 
			return JulianDate.JulianToGregor(JulianDate.GregorToJulian(this.Day)-Days);
	}
}
