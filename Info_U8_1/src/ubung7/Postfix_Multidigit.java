package ubung7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import Postfix_Stack.StackAsList;
import Postfix_Stack.StackException;
import Postfix_Stack.StackUnderflow;
import numeric_Systems.Hexa;

public class Postfix_Multidigit {

	String token;

	public String infixToPostfix (String infix) throws StackUnderflow, StackException
	{
		String result =""; 
		StackAsList stack = new StackAsList();
		infix = Usefull_Methods.setSpacesEachToken(infix);
		
		//https://stackoverflow.com/questions/7899525/how-to-split-a-string-by-space/7899558
		String [] infix_splitted = infix.split("\\s+");
		
		
		for (int i = 0; i < infix_splitted.length; i++)
		{			
			token = infix_splitted[i];
			System.out.println(token);
			
			if (Usefull_Methods.StringisNumber(token))
				{
					result += token + " ";
				}
			
			else if (token.equals("("))
			{
				stack.push(token);
			}
			else if (token.equals(")"))
			{
				while (!stack.top().equals("("))
				{
					result += stack.top() + " ";
					stack.pop();
				}
				stack.pop();
			}
			else if (isOperator(token.charAt(token.length()-1)))			    						
			{
				while (!stack.isEmpty() &&(!((precedence(stack.top().toString()) < precedence(token)) )||
						(token.equals("^") && precedence(stack.top().toString()) == precedence(token)))) 	
				{
					result += stack.top() + " ";
					stack.pop(); 
				}
				
				stack.push(token);
			}
			else {
				System.out.println(token);
				throw new StackException("Wrong Input 1");
			}
			
		}
		
		while (!stack.isEmpty())
		{
			result += stack.top() + " ";
			stack.pop();
		}

		return result.stripTrailing();
	}	
	public boolean isOperator (char token) {
		
		if (token == '+' ||
			token == '-' ||
			token == 'x' ||
			token == '/' ||
			token == '^')
			return true;
		
		else 
			return false;
	}	
	public int precedence (String token)
	{
		switch(token) {
		
		case "+":
		case "-":
			return 0;
		case "x":
		case "/":
			return 1;
		case "^":
			return 2;
		default:
			return -1;
			
		}
	}
	public double evaluate (String pfx) throws StackUnderflow, StackException {
		

		double rhs= 0;
		double lhs = 0;
		double tokenInt = 0;
		
		double result = 0;
		
		StackAsList stack = new StackAsList();
		
		String [] pfx_splitted = pfx.split("\\s+");

		for (int i = 0; i <pfx_splitted.length; i++)
		{			
			token = pfx_splitted[i];
			
			if (Usefull_Methods.StringisNumber(token))
			{
				tokenInt = Double.parseDouble(token);
				stack.push(tokenInt);
			}
			
			else if (isOperator(token.charAt(token.length()-1)))	 
			{
				
				rhs = (double) stack.top();
				stack.pop();
				lhs =  (double) stack.top();
				stack.pop();

				if (token.equals("+")) {
					result = lhs + rhs;
					stack.push(result);
					}
				else if (token.equals("-")) {
					result = lhs - rhs;
					stack.push(result);
					}
				else if (token.equals("x")) {
					result = lhs * rhs;
					stack.push(result);
					}
					
				else if (token.equals("/")) {
					result =  lhs /rhs;
					stack.push(result);
					}
				else if (token.equals("^")) {
					result = (double) Math.pow(lhs, rhs);
					stack.push(result);
					}
			}
			else {
				throw new StackException("Wrong Input");
			}
		}
		return (double) stack.top();
	}
}