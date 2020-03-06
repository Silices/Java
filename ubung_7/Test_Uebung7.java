package ubung_7;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class Test_Uebung7 {
	
	CalcEngine engine = new CalcEngine();
	HexUserInterface gui = new HexUserInterface(engine);
	
	@Test
	void testCalculation_1() {

		engine.numberPressed('1');
		engine.plus();
		engine.numberPressed('7');
		engine.multiplication();
		engine.numberPressed('5');
		engine.equals();
		assertEquals("36.0",engine.calculation);	// 1+7*5
	}
	
	@Test
	void testCalculation_2() {

		engine.numberPressed('F');
		engine.minus();
		engine.numberPressed('6');
		engine.multiplication();
		engine.numberPressed('5');
		engine.equals();
		assertEquals("-15.0",engine.calculation);	// F-6*5
	}
	
	@Test
	void testCalculation_3() {

		engine.numberPressed('6');
		engine.division();
		engine.numberPressed('3');
		engine.plus();
		engine.numberPressed('9');
		engine.multiplication();
		engine.numberPressed('2');
		engine.equals();
		gui.redisplay();
		assertEquals("14",engine.calculation);	// 6/3+9*2
	}
	
	@Test
	void testCalculation_4() {

		engine.numberPressed('3');
		engine.plus();
		engine.numberPressed('7');
		engine.multiplication();
		engine.numberPressed('5');
		engine.division();
		engine.numberPressed('4');
		engine.equals();
		assertEquals("11.75",engine.calculation);	// 3+7*5/4
	}
	
	@Test
	void testCalculation_5() {

		engine.numberPressed('A');
		engine.plus();
		engine.numberPressed('C');
		engine.division();
		engine.numberPressed('6');
		engine.equals();
		assertEquals("12.0",engine.calculation);	// A+C/6
	}
	
	@Test
	void testCalculation_6() {

		engine.numberPressed('D');
		engine.multiplication();
		engine.numberPressed('8');
		engine.minus();
		engine.numberPressed('6');
		engine.multiplication();
		engine.numberPressed('5');
		engine.equals();
		assertEquals("74.0",engine.calculation);	// D*8-6*5
	}
	
	@Test
	void testCalculation_7() {

		engine.numberPressed('6');
		engine.plus();
		engine.numberPressed('6');
		engine.multiplication();
		engine.numberPressed('6');
		engine.minus();
		engine.numberPressed('6');
		engine.division();
		engine.numberPressed('6');
		
		engine.equals();
		assertEquals("41.0",engine.calculation);	// 6+6*6-6/6
	}
	
	@Test
	void testCalculation_8() {

		engine.numberPressed('2');
		engine.division();
		engine.numberPressed('A');
		engine.plus();
		engine.numberPressed('3');
		engine.multiplication();
		engine.numberPressed('5');
		engine.plus();
		engine.numberPressed('F');
		engine.equals();
		assertEquals("30.2",engine.calculation);	// 2/A+3*5+F
		
	}
	
	@Test
	void testCalculation_9() {

		engine.numberPressed('F');
		engine.minus();
		engine.numberPressed('3');
		engine.multiplication();
		engine.numberPressed('5');
		engine.plus();
		engine.numberPressed('A');
		engine.division();
		engine.numberPressed('4');
		engine.equals();
		assertEquals("2.5",engine.calculation);	// F-3*5+A/4
	}
	
	@Test
	void testCalculation_10() {
		engine.numberPressed('8');
		engine.plus();
		engine.numberPressed('7');
		engine.multiplication();
		engine.numberPressed('D');
		engine.division();
		engine.numberPressed('4');
		engine.equals();
		assertEquals("30.75",engine.calculation);	// 8+7*D/4
	}
	
	@Test
	void testCalculation_11() {

		engine.numberPressed('1');
		engine.minus();
		engine.numberPressed('B');
		engine.plus();
		engine.numberPressed('8');
		engine.division();
		engine.numberPressed('5');
		engine.minus();
		engine.numberPressed('2');
		engine.equals();
		assertEquals("-10.4",engine.calculation);	// 1-B+8/5-2
	}

	@Test
	void testCalculation_12() {

		engine.numberPressed('3');
		engine.plus();
		engine.numberPressed('3');
		engine.multiplication();
		engine.numberPressed('5');
		engine.plus();
		engine.numberPressed('F');
		engine.division();
		engine.numberPressed('A');
		engine.equals();
		assertEquals("19.5",engine.calculation);	// 3+3*5+F/A
	}
	
	@Test
	void testCalculation_13() {

		engine.numberPressed('1');
		engine.multiplication();
		engine.numberPressed('2');
		engine.multiplication();
		engine.numberPressed('7');
		engine.minus();
		engine.numberPressed('4');
		engine.minus();
		engine.numberPressed('B');
		engine.division();
		engine.numberPressed('8');
		engine.minus();
		engine.numberPressed('9');
		engine.multiplication();
		engine.numberPressed('E');
		engine.multiplication();
		engine.numberPressed('E');
		engine.equals();
		assertEquals("-1755.375",engine.calculation);	// 1*2*7-4-B/8-9*E*E
	}
	
	@Test
	void testCalculation_14() {

		engine.numberPressed('4');
		engine.plus();
		engine.numberPressed('8');
		engine.multiplication();
		engine.numberPressed('7');
		engine.minus();
		engine.numberPressed('4');
		engine.minus();
		engine.numberPressed('F');
		engine.division();
		engine.numberPressed('2');
		engine.plus();
		engine.numberPressed('5');
		engine.multiplication();
		engine.numberPressed('C');
		engine.division();
		engine.numberPressed('3');
		engine.equals();
		assertEquals("68.5",engine.calculation);	// 4+8*7-4-F/2+5*C/3
	}
	
	@Test
	void testCalculation_15() {

		engine.numberPressed('1');
		engine.minus();
		engine.numberPressed('2');
		engine.multiplication();
		engine.numberPressed('3');
		engine.plus();
		engine.numberPressed('4');
		engine.division();
		engine.numberPressed('5');
		engine.division();
		engine.numberPressed('6');
		engine.plus();
		engine.numberPressed('7');
		engine.multiplication();
		engine.numberPressed('8');
		engine.minus();
		engine.numberPressed('9');
		engine.multiplication();
		engine.numberPressed('A');
		engine.plus();
		engine.numberPressed('B');
		engine.division();
		engine.numberPressed('C');
		engine.plus();
		engine.numberPressed('D');
		engine.multiplication();
		engine.numberPressed('E');
		engine.minus();
		engine.numberPressed('F');
		
		engine.equals();
		assertEquals("129.05",engine.calculation);	// 1-2*3+4/5/6+7*8-9*A+B/C+D*E-F
	}
}