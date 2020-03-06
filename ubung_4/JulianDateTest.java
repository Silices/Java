package ubung_4;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class JulianDateTest {


	@Test
	void testCalculateJD() {
		JulianDate test = new JulianDate();
		int output = test.calculateJD(19000101);
		assertEquals(0,output);
	}

	@Test
	void testGetdaysbetween() {

		JulianDate test = new JulianDate();
		int output = test.getdaysbetween(19000101, 20191108);
		assertEquals(43775,output);
	}

	@Test
	void testNdaysago() {
		JulianDate test = new JulianDate();
		String output = test.ndaysago(20191105, 30);
		assertEquals("6.10.2019",output);
	}

	@Test
	void testInNdays() {
		JulianDate test = new JulianDate();
		String output = test.inNdays(19000101, 30);
		assertEquals("31.1.1900",output);
	}

	@Test
	void testDayoftheweek() {
		JulianDate test = new JulianDate();
		String output = test.dayoftheweek(20191109);
		assertEquals("Saturday",output);
	}

	@Test
	void testJultogreg() {
		JulianDate test = new JulianDate();
		String output = test.jultogreg(43828);
		assertEquals("31.12.2019",output);
	}
}

