package Calculator;


import java.awt.Component;
import java.awt.event.ActionEvent;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTextField;

import ADT.GregorDate;
import ADT.JulianDate;
import ADT.MyJulianDate;
import Postfix_Stack.StackException;
import Postfix_Stack.StackUnderflow;
import numeric_Systems.BinFSystem;
import numeric_Systems.Hexa;
import numeric_Systems.Okta;
import numeric_Systems.OktaFSystem;
import ubung7.CalcEngine_V2;
import ubung7.Usefull_Methods;

public class UserInterface_Date extends UserInterface_HEX_BIN_OKT {
	
	protected ButtonGroup DateButtons = new ButtonGroup();
	
	MyJulianDate myJD_1;
	MyJulianDate myJD_2;
	String sep = ".";
	JTextField Datedisplay;
	
	public UserInterface_Date(CalcEngine_V2 engine, int Amount_Checkboxes) {
		super(engine, Amount_Checkboxes);
		addLayout_Date();
	}
	private void addLayout_Date() {
		
		Boxes[4] = new JCheckBox("DATE");
		
		Boxes[4].addActionListener(this);
		
		Datedisplay = new JTextField("0");
		
		Displays.add(Datedisplay);
		
		CheckBoxGrp.add(Boxes[4]);
		
        addCheckbox(Checkboxes, Boxes[4]);
        
        frame.pack();
        
        assignButtonGrps_Date();
        GreyButtons_Date();
	}
	protected void assignButtonGrps_Date() {
		
		assignButtonGrps_OKT();
		
		comp = buttonPanel.getComponents();
		
    	for(Component E : comp) {
    		if(E instanceof JButton)
    			if(isNumber(((JButton)E).getText()))
    				if(isBetween(Integer.parseInt(((JButton)E).getText()), 0, 9))
    	        		DateButtons.add((JButton)E);		
    	}
	}
	protected void GreyButtons_Date() {
		
		GreyButtons_OKT();
		
		if(Boxes[4].isSelected()) {
    		Enumeration<AbstractButton> Date = DateButtons.getElements();
        	while(Date.hasMoreElements()) {
        		JButton Button = (JButton) Date.nextElement();
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
    		setBase_Date();
    		if(Base == 0)
    		{	
    			calc.clear();
    			redisplay_Date();
    			reIndex_Display_Date();
    		}
    		GreyButtons_Date();
            redisplay_Date();
    	}
    	//numeric systems
    	else if(Base > 0) {
    		nummericCalc(command);
    	}
    	//Dates
    	else if(Base == 0) {
            if(command.equals("Clear")) {
                calc.clear();
                redisplay_OKT();
            }
            else if(command.equals("?")) {
                showInfo();
            }
            //Operator!
            else if(Usefull_Methods.CharisOperator(command.charAt(command.length()-1))){

        		try {	
	            	calc.ADDtoDisplayValue(calc.getinput());
	            	// for "="
	            	if(command.equals("=")) {
	            		//if + is in String
	            		if(calc.getDisplayValue().contains("+")) {
	            			calc.setinput(myJD_1.addDays(Integer.valueOf(calc.getinput())).toString(sep));
	            		}
	            		//if - is in String
	            		else if(calc.getDisplayValue().contains("-")) {
	            			calc.setinput(myJD_1.subtractDays(Integer.valueOf(calc.getinput())).toString(sep));
	            		}
	            		//if * is in String
	            		else if(calc.getDisplayValue().contains("x")) {
	            			myJD_2 = new MyJulianDate(new GregorDate(calc.getinput()));
	            			calc.setinput(myJD_1.daysBetween(myJD_2.getDay()));
	            		}

            			redisplay_Date();
            			calc.setDisplayValue(calc.getinput());
            			reIndex_Display_Date();
            			calc.setinput(0);
	            	}
	            	//Everything else
	            	else {
            			System.out.println(15);
	            		myJD_1 = new MyJulianDate(new GregorDate(calc.getinput()));
	            		// / dont need = to work thats why we put it in here.
	            		if(command.equals("/")) {
	            			System.out.println(myJD_1.get_Weekday());
	            			calc.setinput(myJD_1.get_Weekday());
	            			redisplay_Date();
	            			reIndex_Display_Date();
	            			calc.setinput(0);
	            		}
	            		else if(command.equals("(")||
	            				command.equals(")")||
	            				command.equals("^")){
	            			System.out.println("NOT MAPPED COMMAND");
	            		}
	            		else {
		            		calc.ADDtoDisplayValue(command);
		            		calc.setinput(0);
		            		redisplay_Date();
	            		}
	            	}
				} catch (Exception e) {}
            }
        	//Date-parts
            else if(Usefull_Methods.StringisNumber(command) || command == sep){
            	calc.ADDtoinput(command);
            }
            if(!command.equals("=") && !command.equals("/")) {
            	reIndex_Display_Date();
            }
    	}
    }
	private void setBase_Date() {
		setBase_OKT();
		
    	if(Boxes[4].isSelected()) {
    		System.out.println("Date");
    		Base = 0;	
    	}
    	redisplay_Date();
	}
	private void updateTextfields_Date() {
		
	}
	private void redisplay_Date() {
		redisplay_OKT();
		if(Boxes[4].isSelected()) {
    		display.setText(calc.getDisplayValue());
    	}
	}
	protected void reIndex_Display_Date() {
		updateTextfields_Date();
		reIndex_Display_OKT();
		if(Boxes[4].isSelected()) {
			Input_Display.setText(calc.getinput());
			Datedisplay.setText(calc.getinput());
		}
	}
	private void nummericCalc(String command) {
        //Operator!
    	if(Usefull_Methods.CharisOperator(command.charAt(command.length()-1))) {
        	exp = 0;
        	calc.ADDtoDisplayValue(calc.getinput());
        	// for "="
        	if(command.equals("=")) {
        		try {
					calc.calcResult();
				} catch (StackUnderflow | StackException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		calc.setinput(Double.valueOf(calc.result));
        		redisplay_Date();
        		calc.setDisplayValue(calc.result);
        		reIndex_Display_Date();
        		calc.setinput(0);
        	}
        	//Everything else
        	else {
        		calc.ADDtoDisplayValue(command);
        		calc.setinput(0);
        		redisplay_Date();
        	}
        	
        }
    	//Number
        else if(Usefull_Methods.StringisNumber(command)){
        	AddNumber(command);
        	if(exp<0)
        		exp--;
        }
    	else if(command.equals(".")) {
    		exp = -1;
    	}
        else if(command.equals("Clear")) {
        	exp = 0;
            calc.clear();
            redisplay_Date();
        }
        else if(command.equals("?")) {
            showInfo();
        }
        if(!command.equals("=")) {
        	reIndex_Display_Date();
        }
	}
}

