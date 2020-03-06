package ubung7;

import Calculator.UserInterface_Date;
import Postfix_Stack.Postfix;
import Postfix_Stack.StackException;
import Postfix_Stack.StackUnderflow;

public class CalcEngine_V2 extends Postfix_Multidigit {
	
	private UserInterface_Date UIDate;
	private String displayValue;
	public String getDisplayValue() {
		return displayValue;
	}
	public void ADDtoDisplayValue(String AddString) {
		if(!AddString.equals("0.0"))
			displayValue += AddString;
		else
			System.out.println("OP after =");
	}
	public void setDisplayValue(String displayValue) {
		this.displayValue = displayValue;
	}	
	private String input = "0";
	
	public String getinput() {

		if (input == "0" || (input.charAt(0) == '0' && input.charAt(1) == '.' && input.charAt(2) == '0'))
			return input = "0";
			
		else return input;
	}
	
	public void ADDtoinput(int AddNumber, int Base){
		if(!(input.equals("0.0") && AddNumber == 0)) {
			System.out.println(input);
			input = Double.toString((Double.valueOf(input))*Base + AddNumber);
		}		
	}
	public void ADDtoinput(String input){
		if (this.input == "0")
			this.input = input;
		else this.input += input;
	}
	public void setinput(double input) {
		this.input = Double.toString(input);
	}
	public void setinput(String input) {
		this.input = input;
	}	
	
	public String result;
	public CalcEngine_V2() {
		super();
		this.displayValue = "";
	}
	public String getTitle()
	{
		return "Cool Java Calculator";
	}
	/**
	* @return The author of this engine.
	*/
	public String getAuthor()
	{
		return "David J. Barnes and Michael Kolling";
	}
	/**
	* @return The version number of this engine.
	*/
	public String getVersion()
	{
	    return "Version 1.0";
	}
	public void clear() {
		displayValue = "";
		input = "0";
	}
	public void calcResult() throws StackUnderflow, StackException {
		result = Double.toString(evaluate(infixToPostfix(getDisplayValue())));
	}
	public String getResult() {
		return result;
	}
	
}


