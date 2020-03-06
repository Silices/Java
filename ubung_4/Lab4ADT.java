package ubung_4;

import java.text.SimpleDateFormat;
import java.util.*;

public class Lab4ADT {
	
	
	public static void main(String[] args) {
		/*
		Date date = new Date(); 
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		System.out.println(formatter.format(date)); */
		
		int date = 20191104;
		int hour = 23;
		int min = 12;
		int sek = 3;
	System.out.println(julianDate (date));

		
		dayofweek(date);		
	}

	
	public static double julianDate(int date) {  
		/*
		double JulianDate;
		int julianDaysNumber = julianDayNumber(date);
		JulianDate = (double) julianDaysNumber + (hour-12)/24 + min/1440 + sek/86400; */
		
		
		
		double JulianDate = 0;
		
		
		int day = date%100;
		int month = (date-day)%10000/100;
		int year = date/10000;

		while (year-1 >= 1900)
		{
			if (year%4 ==0 && year != 1900 && year != 2100)
			{
				day +=366;
			}
			else {
				day += 365;
			}
		}
		
		if (year%4 ==0 && year != 1900 && year != 2100)
		{
			switch(month) {
			
			case 1:  
				break;
			case 2: 
				day += 31;
				break;
			case 3: 
				day += 60; 
				break;
			case 4: 
				day += 91; 
				break;
			case 5: 
				day += 121; 
				break;
			case 6: 
				day += 152; 
				break;
			case 7: 
				day += 182; 
				break;
			case 8: 
				day += 213; 
				break;
			case 9: 
				day += 244; 
				break;
			case 10: 
				day += 274; 
				break;
			case 11: 
				day += 305; 
				break;
			case 12: 
				day += 335; 
				break;
			}
		}
		else {
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
			case 11: 
				day += 304; 
				break;
			case 12: 
				day += 334; 
				break;
				
				
			}
		}
	
		
		
		return day;
	}
	
	
	
	
	private static int julianDayNumber(int date) { //date has to be in yyyymmdd format

		int day = date%100;
		int month = (date-day)%10000/100;
		int year = date/10000;

		int Juliandaynumber = (1461 * (year + 4800 + (month-14)/12))/4 + (367*(month-2-12*((month-14)/12)))/12-(3*((year + 4900 + (month-14)/12)/100))/4 + day - 32075;
		
		return Juliandaynumber;
	}
	
	

	public static int daysBetween(int day1, int day2) {
		
		int days = 0;
		if (julianDayNumber(day1) < julianDayNumber(day2))
		{
			days = julianDayNumber(day2) - julianDayNumber(day1);
		}
		else {
			days = julianDayNumber(day1) - julianDayNumber(day2);
		}
		return days;
	}
	
	
	
	public static String dayofweek (int day) {
		
		String dayofweek = "";
		
		switch((julianDayNumber(day)%7)+1)
		{
		case 1:
			dayofweek = "Monday";
			break;
		case 2:
			dayofweek = "Tuesday";
			break;
		case 3:
			dayofweek = "Wednesday";
			break;
		case 4:
			dayofweek = "Thursday";
			break;
		case 5:
			dayofweek = "Friday";
			break;
		case 6:
			dayofweek = "Saturday";
			break;
		case 7:
			dayofweek = "Sunday";
			break;
		}
		
		return dayofweek;
	}
	
	public static double yesterday (int date, int hour, int min, int sek) {
		double yesterday = julianDate(date)-1;
		
		
		return yesterday;
		
	}
	
	public static double tomorrow (int date, int hour, int min, int sek) {
		double yesterday = julianDate(date)+1;
		
		
		return yesterday;
		
	}
}
