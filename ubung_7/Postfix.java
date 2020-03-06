package ubung_7;

public class Postfix {

	public Postfix() {
		// TODO Auto-generated constructor stub
	}

	char token;

	public String infixToPostfix (String infix) throws StackUnderflow, StackException
	{
		String result =""; 
		StackAsList stack = new StackAsList();
		
		infix = infix.replace("\\s+", ""); 
		
		for (int i = 0; i < infix.length(); i++)
		{			
			token = infix.charAt(i);
			
			
			if ((token > 47 && token < 58) || (token >64 && token<71))
				{
					result += token;
				}
			
			else if (token == '(')
			{
				stack.push(token);
			}
			else if (token == ')')
			{
				while (stack.top() != (Character)'(')
				{
					result += stack.top();
					stack.pop();
				}
				stack.pop();
			}
			else if (isOperator(token))			    						
			{
				while (!stack.isEmpty() &&(!((precedence((char) stack.top()) < precedence(token) )||
						(token == '^' && precedence((char) stack.top()) == precedence(token) )))) 	
				{
					result += stack.top();
					stack.pop(); 
				}
				
				stack.push(token);
			}
			else {
				throw new StackException("Wrong Input");
			}
			
		}
		
		while (!stack.isEmpty())
		{
			result += stack.top();
			stack.pop();
		}

		return result;
	}
	
	public boolean isOperator (char token) {
		
		if (token == '+' ||
			token == '-' ||
			token == '*' ||
			token == '/' ||
			token == '^')
			return true;
		
		else 
			return false;
	}
	
	
	public int precedence (char token)
	{
		switch(token) {
		
		case '+':
		case '-':
			return 0;
		case '*':
		case '/':
			return 1;
		case '^':
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
		
		pfx = pfx.replace("\\s+", ""); 

		for (int i = 0; i <pfx.length(); i++)
		{			
			token = pfx.charAt(i);

			if ((token > 47 && token < 58) || (token >64 && token<71))
			{
				tokenInt = Character.getNumericValue(token);
				stack.push(tokenInt);
			}
			
			else if (isOperator(token))	 
			{

				rhs = (double) stack.top();
				stack.pop();
				lhs =  (double) stack.top();
				stack.pop();

				if (token == '+')
					{result = lhs + rhs;
					stack.push(result);
					}
				else if (token == '-')
					{result = lhs - rhs;
					stack.push(result);
					}
				else if (token == '*')
					{result = lhs * rhs;
					stack.push(result);
					}
					
				else if (token == '/')
					{result =  lhs /rhs;
					stack.push(result);
					}
				else if (token == '^')
					{result = (double) Math.pow(lhs, rhs);
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
