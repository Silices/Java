package ubung_4;

public class MetricDate  {
	
	int day;
	int month;
	int year;
		public String calculateMD(int JD) {	
			
			String calculateMD ; 
			day = JD%100 ; 
			month = (JD/100)%10 ; 
			year = JD/1000;	
			
			calculateMD = day + "." + month + "." + year ; 
			return calculateMD;
	
		}
		
		public int metricToJul(int MetricDay, int MetricMonth, int MetricYear) {	
	
		
			int JulianDate = MetricDay + MetricMonth*100 + MetricYear*1000;
			return JulianDate;
		}
	
}

// 2 10 35
