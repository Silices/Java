package ubung_6;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

class TestPost {

	@Test
	void testEvaluate() throws StackUnderflow, StackException {
		
		Postfix test = new Postfix();
		double output = 0;
		
		output = test.evaluate("12*3+");
		assertEquals(5,output);
		
		output = test.evaluate("123*+");
		assertEquals(7,output);
		
		output = test.evaluate("12+34^-");
		assertEquals(-78,output);
		
		output = test.evaluate("12^34*-");
		assertEquals(-11,output);
		
		output = test.evaluate("123*+45^-6+");
		assertEquals(-1011,output);
		
		output = test.evaluate("12+3*456-^+");
		assertEquals(9.25,output);
		
		output = test.evaluate("12+34/+5+678+*+");
		assertEquals(98.75,output);
		
		output = test.evaluate("91-2-32*-1-");
		assertEquals(-1,output);

	}
	
	@Test
	void testInfixToPostfix() throws StackUnderflow, StackException {
		
		Postfix test = new Postfix();
		String output = "";
		
		output = test.infixToPostfix("1*2+3");
		assertEquals("12*3+",output);
		
		output = test.infixToPostfix("1+2*3");
		assertEquals("123*+",output);
		
		output = test.infixToPostfix("1+2-3^4");
		assertEquals("12+34^-",output);
		
		output = test.infixToPostfix("1^2-3*4");
		assertEquals("12^34*-",output);
		
		output = test.infixToPostfix("1+2*3-4^5+6");
		assertEquals("123*+45^-6+",output);
		
		output = test.infixToPostfix("(1+2)*3+(4^(5-6))");
		assertEquals("12+3*456-^+",output);
		
		output = test.infixToPostfix("1+2+3/4+5+6*(7+8)");
		assertEquals("12+34/+5+678+*+",output);
		
		output = test.infixToPostfix("9-1-2-3*2-1");
		assertEquals("91-2-32*-1-",output);

	}
	
	@Test
	void testReadInfix() throws StackUnderflow, StackException, IOException {
		
		Postfix test = new Postfix();
		while (true)
		test.readInfix();
		


	}
}


