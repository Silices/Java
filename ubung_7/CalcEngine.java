package ubung_7;
/**
 * The main part of the calculator doing the calculations.
 * 
 * @author Bartholomäus Berresheim
 * @version 2019.12.06
 */
public class CalcEngine
{

    protected String calculation = "";
    protected Postfix postcalc = new Postfix();
    
    /**
     * Create a CalcEngine.
     */
    public CalcEngine()
    {
        clear();
    }

    /**
     * A number button was pressed.
     * Either start a new operand, or incorporate this number as
     * the least significant digit of an existing one.
     * @param number The number pressed on the calculator.
     */

    public void numberPressed(char number)
    {
    	calculation += number;
    }
    
    /**
     * The 'exponentiation' button was pressed. 
     */
    public void exponentiation() 
    {
    	calculation += "^";
	}
    
    /**
     * The 'multiplication' button was pressed. 
     */
    public void multiplication()
    {
    	calculation += "*";
    }
    
    /**
     * The 'division' button was pressed. 
     */
    public void division()
    {
    	calculation += "/";
    }
    
    /**
     * The 'plus' button was pressed. 
     */
    public void plus()
    {
    	calculation += "+";
    }

    /**
     * The 'minus' button was pressed.
     */
    public void minus()
    {
    	calculation += "-";
    }
    
    /**
     * The '=' button was pressed.
     */
    public void equals()
    {
    	try {
			double result = postcalc.evaluate(postcalc.infixToPostfix(calculation));
			calculation = Double.toString(result);
		} catch (StackUnderflow | StackException e) {
			e.printStackTrace();
		}
    }
    

    /**
     * The 'Clear' button was pressed.
     * Reset everything to a starting state.
     */
    public void clear()
    {
    	calculation = "";
    }

    /**
     * @return The title of this calculation engine.
     */
    public String getTitle()
    {
        return "Super Magical Calculator";
    }

    /**
     * @return The author of this engine.
     */
    public String getAuthor()
    {
        return "Bartholomäus Berresheim";
    }

    /**
     * @return The version number of this engine.
     */
    public String getVersion()
    {
       return "Version 1.0";
    }
}
