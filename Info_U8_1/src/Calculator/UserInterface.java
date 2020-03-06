
package Calculator;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

import Postfix_Stack.Postfix;
import Postfix_Stack.StackException;
import Postfix_Stack.StackUnderflow;
import numeric_Systems.Hexa;
import ubung7.CalcEngine_V2;
import ubung7.Usefull_Methods;

/**
 * A graphical user interface for the calculator. No calculation is being
 * done here. This class is responsible just for putting up the display on 
 * screen. It then refers to the "CalcEngine" to do all the real work.
 * 
 * @author David J. Barnes and Michael Kolling
 * @version 2008.03.30
 */
public class UserInterface
    implements ActionListener
{
	public JCheckBox[] Boxes;
    protected CalcEngine_V2 calc;
    protected boolean showingAuthor;

    protected JFrame frame;
    protected JTextField display;
    protected JTextField Input_Display;
    protected JLabel status;
    
    protected JPanel buttonPanel;
    protected JPanel Northside;
    protected JPanel contentPane;
    protected int exp = 0;
    public int Base = 10;
    /**
     * Create a user interface.
     * @param engine The calculator engine.
     */
    public UserInterface(CalcEngine_V2 engine)
    {
        calc = engine;
        showingAuthor = true;
        makeFrame();
        frame.setVisible(true);
    }

    /**
     * Set the visibility of the interface.
     * @param visible true if the interface is to be made visible, false otherwise.
     */
    public void setVisible(boolean visible)
    {
        frame.setVisible(visible);
    }

    /**
     * Make the frame for the user interface.
     */
    private void makeFrame()
    {
        frame = new JFrame(calc.getTitle());
        
        contentPane = (JPanel)frame.getContentPane();
        contentPane.setLayout(new BorderLayout(8, 8));
        contentPane.setBorder(new EmptyBorder( 10, 10, 10, 10));

        display = new JTextField("0");
        Input_Display = new JTextField("0");
        
        Northside = new JPanel(new GridLayout(3, 1));
        
        Northside.add(display);
        Northside.add(Input_Display);
        
        contentPane.add(Northside, BorderLayout.NORTH);
              
        buttonPanel = new JPanel(new GridLayout(6, 4));       
            
        	addButton(buttonPanel, "(");
        	addButton(buttonPanel, "Clear");
        	addButton(buttonPanel, ")");
        	addButton(buttonPanel, "/");
        	
        	
            addButton(buttonPanel, "7");
            addButton(buttonPanel, "8");
            addButton(buttonPanel, "9");
            addButton(buttonPanel, "x");
            
            
            addButton(buttonPanel, "4");
            addButton(buttonPanel, "5");
            addButton(buttonPanel, "6");
            addButton(buttonPanel, "-");
            
            
            addButton(buttonPanel, "1");       
            addButton(buttonPanel, "2");
            addButton(buttonPanel, "3");
            addButton(buttonPanel, "+");
            
            addButton(buttonPanel, ".");
            addButton(buttonPanel, "0");
            addButton(buttonPanel, "?");
            addButton(buttonPanel, "=");
            addButton(buttonPanel, "^");
                   
            
        contentPane.add(buttonPanel, BorderLayout.CENTER);

        status = new JLabel(calc.getAuthor());
        contentPane.add(status, BorderLayout.SOUTH);

        frame.pack();
    }

    /**
     * Add a button to the button panel.
     * @param panel The panel to receive the button.
     * @param buttonText The text for the button.
     */
    protected void addButton(Container panel, String buttonText)
    {
        JButton button = new JButton(buttonText);
        button.addActionListener(this);
        panel.add(button);
    }
    protected void addCheckbox(Container panel, JCheckBox JCB) {
    	panel.add(JCB);
    }

    /**
     * An interface action has been performed.
     * Find out what it was and handle it.
     * @param event The event that has occured.
     */
    public void actionPerformed (ActionEvent event)
    {
        String command = event.getActionCommand();
        //Operator!
        if(Usefull_Methods.CharisOperator(command.charAt(command.length()-1))) {
        	exp = 0;
        	calc.ADDtoDisplayValue(calc.getinput());
        	// for "="
        	if(command.contentEquals("=")) {
        		try {
					calc.calcResult();
				} catch (StackUnderflow | StackException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		calc.setinput(Double.valueOf(calc.result));
        		redisplay();
        		calc.setDisplayValue(calc.result);
        		reIndex_Display();
        		calc.setinput(0);
        	}
        	//Everything else
        	else {
        		calc.ADDtoDisplayValue(command);
        		calc.setinput(0);
        		redisplay();
        	}
        	
        }
    	//Number
        else if(Usefull_Methods.StringisNumber(command)){
        	AddNumber(command);
        	if(exp<0)
        		exp--;
        }
    	else if(command.contentEquals(".")) {
    		exp = -1;
    	}
        else if(command.equals("Clear")) {
        	exp = 0;
            calc.clear();
            redisplay();
            
        }
        else if(command.equals("?")) {
            showInfo();
        }
        if(!command.contentEquals("=")) {
        	reIndex_Display();
        } 
    }
    /**
     * Update the interface display to show the current value of the 
     * calculator.
     */
    protected void redisplay()
    {
        display.setText("" + calc.getDisplayValue());
    }
    protected void reIndex_Display() {
        Input_Display.setText("" + calc.getinput());

    }

    /**
     * Toggle the info display in the calculator's status area between the
     * author and version information.
     */
    protected void showInfo()
    {
        if(showingAuthor)
            status.setText(calc.getVersion());
        else
            status.setText(calc.getAuthor());

        showingAuthor = !showingAuthor;
    }
    protected void AddNumber(String command) {
    	if(exp == 0) {
        	calc.ADDtoinput((Hexa.HEXToDEC(new Hexa(command))), Base);	
    	}
    	else {
    		int digit = Hexa.HEXToDEC(new Hexa(command));  		
        	double input = Double.valueOf(calc.getinput());
        	double ADDNumber = digit;
    		ADDNumber = ADDNumber*Math.pow(Base, exp);  		
    		calc.setinput(input + ADDNumber);
    	}
	}
}

