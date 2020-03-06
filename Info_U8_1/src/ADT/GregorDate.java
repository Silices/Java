package ADT;

import java.util.Calendar;
import java.util.GregorianCalendar;

import ubung7.Usefull_Methods;

public class GregorDate {
	private int day;
	private int month;
	private int year;
	public Boolean AC;
	
	//month start with 0
	private static Calendar cal;
	
	public GregorDate(int year, int month, int day, Boolean AC) {
		
		this.cal = Calendar.getInstance();		
		this.AC = AC;
		
		setYear(year);;		
		setMonth(month);
		setDay(day);
	}
	public GregorDate(String Date) throws Exception {
		//can handle .;/;-;_
		String sep = "\\" + Usefull_Methods.getSep(Date);
		
		String[] Date_ = Date.split(sep);
		
		if(Date_.length != 3) {
			throw new Exception("Wrong Date input");
		}
		else {
			setYear(Integer.valueOf(Date_[0]));;		
			setMonth(Integer.valueOf(Date_[1]));
			setDay(Integer.valueOf(Date_[2]));
		}

	}
	public GregorDate() {
		
	}
	public String toString(String sep) {
		String FirstLetterDay = ((this.day<10) ? "0" : "");
		String FirstLetterMonth = ((this.month<10) ? "0" : "");
		return Integer.toString(year) + sep + FirstLetterMonth + Integer.toString(month) + sep + FirstLetterDay + Integer.toString(day);
	}
	public static int MonthsDays(int month, int year) {
		
		if(!(0 < month && month < 13))
			throw new IllegalArgumentException("months out of range");
		
		switch(month) {
		case 4:
			return 30;
		case 6:
			return 30;
		case 9:
			return 30;
		case 11:
			return 30;
		
		case 2:
			return ((isLeapYear(year)) ? 29 : 28);
		}
	    return 31;
	}	
	public GregorianCalendar toGregorianCalendar() {
		return new GregorianCalendar(this.year,this.month,this.day);
	}	
	//First Day is day 1
	public int getDayOfTheYear() {
		
		int dayOfTheYear = 0;
		
		for(int i = 1; i<month;i++) {
			dayOfTheYear += MonthsDays(i, this.year);
		}			
		return dayOfTheYear + day;	
	}
	public static GregorDate get_current_date() {
		
		//Here we want month + 1 cuz we our Date start with month 1
		GregorDate outcome = new GregorDate(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH) + 1,cal.get(Calendar.DAY_OF_MONTH),true);
		return outcome;
	}
	//Gives us the Month in which the Day of the year is.
	public static int getMonthOfDayOfTheYear(int day, int year) {
		int month = 1;
		for(; MonthsDays(month, year) < day; month++) {
			day -= MonthsDays(month, year);
		}
		
		return month;
	}
	//Gives us the Day of any month by input the Day of the Year
	public static int DayOfYeartoDayofMonth(int day, int year) {
		int month = 1;
		for(; MonthsDays(month, year) < day; month++) {
			day -= MonthsDays(month, year);
		}
		
		return day;
	}	
	public static boolean isLeapYear(int year) {
		return (((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) ? true : false);
	}
	//compares month and day of this Day and the param Day
	public boolean equalsMonth_Day(GregorDate Date2) {
		
		if(this.month == Date2.getMonth() && this.day == Date2.getDay())
			return true;
		else
			return false;
		
	}
	
	
	
	
	public int getDay() {
		return day;
	}
	public int getMonth() {
		return month;
	}
	public int getYear() {
		return year;
	}
	public void setDay(int day) {
		if(0 < day && day <= MonthsDays(month, year))
			this.day = day;
		else
			throw new IllegalArgumentException("days out of range");		
	}
	public void setMonth(int month) {
		if(0 < month && month < 13)
			this.month = month;
		else
			throw new IllegalArgumentException("months out of range");
	}
	public void setYear(int year) {
		this.year = year;
	}

}

