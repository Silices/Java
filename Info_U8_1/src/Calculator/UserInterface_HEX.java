package Calculator;


import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Postfix_Stack.StackException;
import Postfix_Stack.StackUnderflow;
import numeric_Systems.Hexa;
import numeric_Systems.HexaF;
import numeric_Systems.HexaFSystem;
import ubung7.CalcEngine_V2;
import ubung7.Usefull_Methods;

/**
 * A graphical user interface for the calculator. No calculation is being
 * done here. This class is responsible just for putting up the display on 
 * screen. It then refers to the "CalcEngine" to do all the real work.
 * 
 * @author Juri Wiechmann
 * @version 2019.11.25
 */
public class UserInterface_HEX extends UserInterface
    implements ActionListener
{
    protected ButtonGroup CheckBoxGrp = new ButtonGroup();
    
    private JPanel HEX_BOX;
    
    JPanel SideDisplays = new JPanel(new GridLayout(1, 2));
    JPanel Checkboxes = new JPanel(new GridLayout(5, 1));
    JPanel Displays = new JPanel(new GridLayout(5, 1));
    
    JTextField DECdisplay;
    JTextField HEXdisplay;
    
    protected ButtonGroup DECButtons = new ButtonGroup();
    protected ButtonGroup HEXButtons = new ButtonGroup();
    
    
    Component[] comp;
    
	public UserInterface_HEX(CalcEngine_V2 engine, int Amount_Checkboxes) {
		super(engine);
		this.Boxes = new JCheckBox[Amount_Checkboxes];
		addLayout_HEX();
	}	
	public void addLayout_HEX() {
        
        Boxes[0] = new JCheckBox("DEC",true);
        Boxes[1] = new JCheckBox("HEX");

        Boxes[0].addActionListener(this);
        Boxes[1].addActionListener(this);
        
        DECdisplay = new JTextField("0");
        HEXdisplay = new JTextField("0");
        
        addCheckbox(Checkboxes, Boxes[0]);
        addCheckbox(Checkboxes, Boxes[1]);
        
        Displays.add(DECdisplay);
        Displays.add(HEXdisplay);
        
        
        SideDisplays.add(Checkboxes);
        SideDisplays.add(Displays);
        
        Northside.add(SideDisplays);
              
        CheckBoxGrp.add(Boxes[0]);
        CheckBoxGrp.add(Boxes[1]);
        
        Font font1 = new Font("SansSerif", Font.BOLD, 20);
        Input_Display.setFont(font1);
 
        
        contentPane.add(Northside, BorderLayout.NORTH);
        
        HEX_BOX = new JPanel(new GridLayout(5,2));
		buttonPanel.add(HEX_BOX);
        
		HEX_BOX.add(new JLabel(" "));
		HEX_BOX.add(new JLabel(" "));
		addButton(HEX_BOX, "A");
        addButton(HEX_BOX, "B");
        addButton(HEX_BOX, "C");
        addButton(HEX_BOX, "D");
        addButton(HEX_BOX, "E");
        addButton(HEX_BOX, "F");
        HEX_BOX.add(new JLabel(" "));
        HEX_BOX.add(new JLabel(" "));
        
        contentPane.add(HEX_BOX, BorderLayout.WEST);
        frame.pack();
        
        assignButtonGrps_HEX();
        GreyButtons_HEX();
	}
    public void actionPerformed(ActionEvent event){	
    	
    	String command = event.getActionCommand();
    	boolean BaseChange = false;
    	
    	for(JCheckBox E : Boxes)
    		if(E.getText().equals(command))
    			BaseChange = true;
    		
    	if(BaseChange) {
    		setBase_HEX();
    		GreyButtons_HEX();
            redisplay_HEX();
    	}
        //Operator!
    	else if(Usefull_Methods.CharisOperator(command.charAt(command.length()-1))) {
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
        		redisplay_HEX();
        		calc.setDisplayValue(calc.result);
        		reIndex_Display_HEX();
        		calc.setinput(0);
        	}
        	//Everything else
        	else {
        		calc.ADDtoDisplayValue(command);
        		calc.setinput(0);
        		redisplay_HEX();
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
            redisplay_HEX();
            
        }
        else if(command.equals("?")) {
            showInfo();
        }
        if(!command.contentEquals("=")) {
        	reIndex_Display_HEX();
        } 
    }
    //in dependence to our selected mode the main Textfield update
	protected void redisplay_HEX()
    {
    	if(Boxes[0].isSelected()) {
    		display.setText("" + calc.getDisplayValue());
    	}
    	else if(Boxes[1].isSelected()) {
    		System.out.println(calc.getDisplayValue());
    		display.setText("" + HexaFSystem.DECtoHEXF_LINE(calc.getDisplayValue()));
    	}   
    	
    }
    protected void reIndex_Display_HEX() {
    	updateTextfields_HEX();
    	if(Boxes[0].isSelected()) {
    		Input_Display.setText("" + calc.getinput());
    	}
    	else if(Boxes[1].isSelected()) {
            try {
            	Input_Display.setText("" + HexaFSystem.DECtoHEXF(Double.valueOf(calc.getinput())));
        	}
    		catch(Exception e) {
    			Input_Display.setText("");
    		}
    	} 
    }
	//update our four seperate Textfields
    protected void updateTextfields_HEX() {
        	DECdisplay.setText("" + calc.getinput());
        try {
        	HEXdisplay.setText("" + HexaFSystem.DECtoHEXF(Double.valueOf(calc.getinput())));
    	}
    	catch(Exception e) {
        	HEXdisplay.setText("");
    	}
    }
    //Set the Base based on the selected mode
    protected void setBase_HEX() {
    	
    	if(Boxes[0].isSelected()) {
    		System.out.println("DEC");
    		Base = 10;      		
    	}
    	else if(Boxes[1].isSelected()) {
    		System.out.println("HEX");
    		Base = 16;		
    	}
    	redisplay_HEX();
    }
    /*Disable all Buttons and then enable only these which are in the 
      right ButtonGroup that corresponds to the selection mode
    */
    protected void GreyButtons_HEX() {
    	//https://coderanch.com/t/336958/java/disable-buttonGroup
    	//https://stackoverflow.com/questions/7160568/iterating-through-enumeration-of-hastable-keys-throws-nosuchelementexception-err
    	//https://stackoverflow.com/questions/1625855/how-to-disable-javax-swing-jbutton-in-java
    	
    	//Make all Grey
    	Enumeration<AbstractButton> HEX = HEXButtons.getElements();
    	while(HEX.hasMoreElements()) {
    		JButton Button = (JButton) HEX.nextElement();
    		Button.setEnabled(false);
    	}
    	Enumeration<AbstractButton> DEC_theRest = DECButtons.getElements();
    	while(DEC_theRest.hasMoreElements()) {
    		JButton Button = (JButton) DEC_theRest.nextElement();
    		Button.setEnabled(false);
    	}
    	//DEC-Mode
    	if(Boxes[0].isSelected()) {
    		Enumeration<AbstractButton> DEC = DECButtons.getElements();
        	while(DEC.hasMoreElements()) {
        		JButton Button = (JButton) DEC.nextElement();
        		Button.setEnabled(true);
        	}
    	}
    	//HEX-Mode
    	else if(Boxes[1].isSelected()) {
    		HEX = HEXButtons.getElements();
        	while(HEX.hasMoreElements()) {
        		JButton Button = (JButton) HEX.nextElement();
        		Button.setEnabled(true);
        	}
        	Enumeration<AbstractButton> DEC = DECButtons.getElements();
        	while(DEC.hasMoreElements()) {
        		JButton Button = (JButton) DEC.nextElement();
        		Button.setEnabled(true);
        	}
    	}	
	}
    //assign all the Buttons that are relevant to their ButtonGroup
    protected void assignButtonGrps_HEX() {
    	
    	comp = buttonPanel.getComponents();
    	//DEC
    	for(Component E : comp) {	
    		if(E instanceof JButton)
    			if(isNumber(((JButton)E).getText()))
    				if(isBetween(Integer.parseInt(((JButton)E).getText()), 0, 9))
    	        		DECButtons.add((JButton)E);
    	}
    	//HEX
    	comp = HEX_BOX.getComponents();
    	for(Component E : comp) {
    		if(E instanceof JButton) {
    			HEXButtons.add((JButton)E);
    		}    		      		  		
    	}
    }
    //return true if min <= value <= max
    protected boolean isBetween(int value, int min, int max) {
    	if(value >= min && value <= max)
    		return true;
    	else
    		return false;
    }
    //Checks if the String is a single digit Number
    protected boolean isNumber(String value) {
    	if( value.equals("0") ||
    		value.equals("1") ||
    		value.equals("2") ||
    		value.equals("3") ||
    		value.equals("4") ||
    		value.equals("5") ||
    		value.equals("6") ||
    		value.equals("7") ||
    		value.equals("8") ||
    		value.equals("9") ||
    		value.equals("A") ||
    		value.equals("B") ||
    		value.equals("C") ||
    		value.equals("D") ||
    		value.equals("E") ||
    		value.equals("F"))       		
        		return true;
        	else 
        		return false;
    }

}
