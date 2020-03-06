package Calculator;


import java.awt.Component;
import java.awt.event.ActionEvent;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTextField;

import Postfix_Stack.StackException;
import Postfix_Stack.StackUnderflow;
import numeric_Systems.Bin;
import numeric_Systems.BinFSystem;
import numeric_Systems.Hexa;
import numeric_Systems.HexaFSystem;
import numeric_Systems.OktaFSystem;
import ubung7.CalcEngine_V2;
import ubung7.Usefull_Methods;

public class UserInterface_HEX_BIN extends UserInterface_HEX {

	protected ButtonGroup BINButtons = new ButtonGroup();
	
	JTextField BINdisplay;
	
	public UserInterface_HEX_BIN(CalcEngine_V2 engine, int Amount_Checkboxes) {
		super(engine, Amount_Checkboxes);
		addLayout_BIN();
	}
	private void addLayout_BIN() {
		
		Boxes[2] = new JCheckBox("BIN");
		
		Boxes[2].addActionListener(this);
		
		BINdisplay = new JTextField("0");
		
		CheckBoxGrp.add(Boxes[2]);
		
        addCheckbox(Checkboxes, Boxes[2]);
        Displays.add(BINdisplay);
        
        frame.pack();
        
        assignButtonGrps_BIN();
        GreyButtons_BIN();
	}
	protected void assignButtonGrps_BIN() {
		
		assignButtonGrps_HEX();
		
		comp = buttonPanel.getComponents();
		
    	for(Component E : comp) {
    		if(E instanceof JButton)
    			if(isNumber(((JButton)E).getText()))
    				if(isBetween(Integer.parseInt(((JButton)E).getText()), 0, 1))
    	        		BINButtons.add((JButton)E);		
    	}
	}
	protected void GreyButtons_BIN() {
		
		GreyButtons_HEX();
		
		if(Boxes[2].isSelected()) {
    		Enumeration<AbstractButton> BIN = BINButtons.getElements();
        	while(BIN.hasMoreElements()) {
        		JButton Button = (JButton) BIN.nextElement();
        		Button.setEnabled(true);
        	}
    	}	
	}
    public void actionPerformed(ActionEvent event){	
    	
    	String command = event.getActionCommand();
    	boolean BaseChange = false;
    	
    	for(JCheckBox E : Boxes)
    		if(E.getText().equals(command))
    			BaseChange = true;
    		
    	if(BaseChange) {
    		setBase_BIN();
    		GreyButtons_BIN();
            redisplay_BIN();
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
        		redisplay_BIN();
        		calc.setDisplayValue(calc.result);
        		reIndex_Display_BIN();
        		calc.setinput(0);
        	}
        	//Everything else
        	else {
        		calc.ADDtoDisplayValue(command);
        		calc.setinput(0);
        		redisplay_BIN();
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
            redisplay_BIN();
        }
        else if(command.equals("?")) {
            showInfo();
        }
        if(!command.contentEquals("=")) {
        	reIndex_Display_BIN();
        } 
    }
	protected void reIndex_Display_BIN() {
		updateTextfields_BIN();
		reIndex_Display_HEX();
		
		if(Boxes[2].isSelected()) {
            try {
            	Input_Display.setText("" + BinFSystem.DECtoBINF(Double.valueOf(calc.getinput())));
        	}
    		catch(Exception e) {
    			Input_Display.setText("");
    		}
		}
	}
	protected void updateTextfields_BIN() {
		updateTextfields_HEX();	
		try {
			BINdisplay.setText("" + BinFSystem.DECtoBINF(Double.valueOf(calc.getinput())));
		}
		catch(Exception e) {
			BINdisplay.setText("");
		}
	}
	protected void redisplay_BIN() {
		redisplay_HEX();
		if(Boxes[2].isSelected()) {
    		display.setText("" + BinFSystem.DECtoBINF_LINE(calc.getDisplayValue()));
    	}
	}
    protected void setBase_BIN() {
    	setBase_HEX();
    	
    	if(Boxes[2].isSelected()) {
    		System.out.println("BIN");
    		Base = 2;
    	}

    	redisplay_BIN();
    }
}
