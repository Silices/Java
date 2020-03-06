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
import numeric_Systems.BinFSystem;
import numeric_Systems.Hexa;
import numeric_Systems.Okta;
import numeric_Systems.OktaFSystem;
import ubung7.CalcEngine_V2;
import ubung7.Usefull_Methods;

public class UserInterface_HEX_BIN_OKT extends UserInterface_HEX_BIN {
	
	protected ButtonGroup OKTButtons = new ButtonGroup();
	
	JTextField OKTdisplay;
	
	public UserInterface_HEX_BIN_OKT(CalcEngine_V2 engine, int Amount_Checkboxes) {
		super(engine, Amount_Checkboxes);
		addLayout_OKT();
	}
	private void addLayout_OKT() {
		
		Boxes[3] = new JCheckBox("OKT");
		
		Boxes[3].addActionListener(this);
		
		OKTdisplay = new JTextField("0");
		
		CheckBoxGrp.add(Boxes[3]);
		
        addCheckbox(Checkboxes, Boxes[3]);
        Displays.add(OKTdisplay);
        
        frame.pack();
        
        assignButtonGrps_OKT();
        GreyButtons_OKT();
	}
	protected void assignButtonGrps_OKT() {
		
		assignButtonGrps_BIN();
		
		comp = buttonPanel.getComponents();
		
    	for(Component E : comp) {
    		if(E instanceof JButton)
    			if(isNumber(((JButton)E).getText()))
    				if(isBetween(Integer.parseInt(((JButton)E).getText()), 0, 7))
    	        		OKTButtons.add((JButton)E);		
    	}
	}
	protected void GreyButtons_OKT() {
		
		GreyButtons_BIN();
		
		if(Boxes[3].isSelected()) {
    		Enumeration<AbstractButton> OKT = OKTButtons.getElements();
        	while(OKT.hasMoreElements()) {
        		JButton Button = (JButton) OKT.nextElement();
        		Button.setEnabled(true);
        	}
    	}	
	}
    public void actionPerformed(ActionEvent event){	
    	
    	String command = event.getActionCommand();
    	System.out.println(command);
    	System.out.println(calc.getDisplayValue());
    	
    	boolean BaseChange = false;
    	
    	for(JCheckBox E : Boxes)
    		if(E.getText().equals(command))
    			BaseChange = true;
    		
    	if(BaseChange) {
    		setBase_OKT();
    		GreyButtons_OKT();
            redisplay_OKT();
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
        		redisplay_OKT();
        		calc.setDisplayValue(calc.result);
        		reIndex_Display_OKT();
        		calc.setinput(0);
        	}
        	//Everything else
        	else {
        		calc.ADDtoDisplayValue(command);
        		calc.setinput(0);
        		redisplay_OKT();
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
            redisplay_OKT();
        }
        else if(command.equals("?")) {
            showInfo();
        }
        if(!command.contentEquals("=")) {
        	reIndex_Display_OKT();
        }
    }
	protected void setBase_OKT() {
		setBase_BIN();
		
    	if(Boxes[3].isSelected()) {
    		System.out.println("OKT");
    		Base = 8;	
    	}
    	redisplay_OKT();
	}
	protected void updateTextfields_OKT() {
		updateTextfields_BIN();
		
		try {
			OKTdisplay.setText("" + OktaFSystem.DECtoOKTF(Double.valueOf(calc.getinput())));
		}
		catch(Exception e) {
			OKTdisplay.setText("");
		}
	}
	protected void redisplay_OKT() {
		redisplay_BIN();
		if(Boxes[3].isSelected()) {
    		display.setText("" + OktaFSystem.DECtoOKTF_LINE(calc.getDisplayValue()));
    	}
	}
	protected void reIndex_Display_OKT() {
		updateTextfields_OKT();
		reIndex_Display_BIN();
		
		if(Boxes[3].isSelected()) {
            try {
            	Input_Display.setText("" + OktaFSystem.DECtoOKTF(Double.valueOf(calc.getinput())));
        	}
    		catch(Exception e) {
    			Input_Display.setText("");
    		}
		}
	}
}

