package Calculator;

import Postfix_Stack.Postfix;
import ubung7.CalcEngine_V2;

/**
 * The main class of a simple calculator. Create one of these and you'll
 * get the calculator on screen.
 * 
 * @author David J. Barnes and Michael Kolling
 * @version 2008.03.30
 */
public class Calculator
{
    public CalcEngine_V2 engine;
    public UserInterface gui;

    /**
     * Create a new calculator and show it.
     * @param Set the Numeric System
     * mode == 1: Basic Calc
     * mode == 2: HexCalc
     * mode == 3: BIN_HEX Calc
     * mode == 4: OKT_BIN_HEX Calc
     */
    public Calculator(int mode)
    {
        engine = new CalcEngine_V2();
        if(mode == 1) gui = new UserInterface(engine);
        if(mode == 2) gui = new UserInterface_HEX(engine, mode);
        if(mode == 3) gui = new UserInterface_HEX_BIN(engine, mode);
        if(mode == 4) gui = new UserInterface_HEX_BIN_OKT(engine, mode);
        if(mode == 5) gui = new UserInterface_Date(engine, mode);
    }

    /**
     * In case the window was closed, show it again.
     */
    public void show()
    {
        gui.setVisible(true);
    }
    public void setEngine(CalcEngine_V2 engine) {
    	this.engine = engine;
    }
}
