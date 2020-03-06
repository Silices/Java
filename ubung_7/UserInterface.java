package ubung_7;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * A graphical user interface for the calculator. No calculation is being
 * done here. This class is responsible just for putting up the display on 
 * screen. It then refers to the "CalcEngine" to do all the real work.
 * 
 * @author Bartholomäus Berresheim
 * @version 2019.12.06
 */
public class UserInterface
    implements ActionListener
{
    protected CalcEngine calc;
    protected boolean showingAuthor;
    protected JFrame frame;
    protected JTextField display;
    protected JLabel status;

    protected JPanel hexaPanel;
    protected ButtonGroup buttonG = new ButtonGroup();

    private String command = "=";
    /**
     * Create a user interface.
     * @param engine The calculator engine.
     */
    public UserInterface(CalcEngine engine)
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
    protected void makeFrame()
    {
        frame = new JFrame(calc.getTitle());
        
        JPanel contentPane = (JPanel)frame.getContentPane();
        contentPane.setLayout(new BorderLayout(0, 8));
        contentPane.setBorder(new EmptyBorder( 10, 10, 10, 10));
        
        JPanel buttonPanel = new JPanel(new GridLayout(4, 5));
        addButton(buttonPanel, "7");
        addButton(buttonPanel, "8");
        addButton(buttonPanel, "9");
        addButton(buttonPanel, "Clear");
        addButton(buttonPanel, "?");
        
        addButton(buttonPanel, "4");
        addButton(buttonPanel, "5");
        addButton(buttonPanel, "6");
        addButton(buttonPanel, "*");
        addButton(buttonPanel, "/");
        
        addButton(buttonPanel, "1");
        addButton(buttonPanel, "2");
        addButton(buttonPanel, "3");
        addButton(buttonPanel, "+");
        addButton(buttonPanel, "-");
        
        addButton(buttonPanel, "0");
        buttonPanel.add(new JLabel(" "));
        buttonPanel.add(new JLabel(" "));
        addButton(buttonPanel, "^");
        addButton(buttonPanel, "=");
        
        contentPane.add(buttonPanel, BorderLayout.CENTER);
        

        
        JPanel checkboxPanel = new JPanel(new GridLayout(3,1));
        display = new JTextField();
        checkboxPanel.add(display);

        addCheckbox(checkboxPanel, "DEC", false);
        addCheckbox(checkboxPanel, "HEX", true);

        contentPane.add(checkboxPanel, BorderLayout.NORTH);

        status = new JLabel(calc.getAuthor());
        contentPane.add(status, BorderLayout.SOUTH);
        frame.pack();
    }

    /**
     * Add a button to the button panel.
     * @param panel The panel to receive the button.
     * @param buttonText The text for the button.
     */
    protected void addCheckbox(Container panel, String checkboxText, boolean selected)
    {
    	JCheckBox checkbox = new JCheckBox(checkboxText, selected);
    	checkbox.addActionListener(this);
    	buttonG.add(checkbox);
        panel.add(checkbox);
    }
    
    protected void addButton(Container panel, String buttonText)
    {
        JButton button = new JButton(buttonText);
        button.addActionListener(this);
        panel.add(button);
    }

    /**
     * An interface action has been performed.
     * Find out what it was and handle it.
     * @param event The event that has occured.
     */
    public void actionPerformed(ActionEvent event)
    {
        command = event.getActionCommand();
        char c;

        if(command.equals("DEC"))
        {hexaPanel.setVisible(false);}
        
        else if(command.equals("HEX"))
        {hexaPanel.setVisible(true);}
        
        else if(command.equals("0") ||
        		command.equals("1") ||
        		command.equals("2") ||
        		command.equals("3") ||
        		command.equals("4") ||
        		command.equals("5") ||
        		command.equals("6") ||
        		command.equals("7") ||
        		command.equals("8") ||
        		command.equals("9")) {
        	c = (char) (command.charAt(0));
            calc.numberPressed(c);
        }
        else if(command.equals("A") ||
        		command.equals("B") ||
        		command.equals("C") ||
        		command.equals("D") ||
        		command.equals("E") ||
        		command.equals("F")) {
        	c = (char) (command.charAt(0));
            calc.numberPressed(c);
        }
        else if(command.equals("^")) {
            calc.exponentiation(); 
        }
        else if(command.equals("*")) {
            calc.multiplication(); 
        }
        else if(command.equals("/")) {
           calc.division();
        }
        else if(command.equals("+")) {
            calc.plus();
        }
        else if(command.equals("-")) {
            calc.minus();
        }
        else if(command.equals("=")) {
            calc.equals();
        	
        }
        else if(command.equals("Clear")) {
            calc.clear();
        }
        else if(command.equals("?")) {
            showInfo();
        }
        // else unknown command.

        redisplay();
        if(command.equals("=")) {
        	calc.clear();
        }
    }

    /**
     * Update the interface display to show the current value of the 
     * calculator.
     */
    protected void redisplay()
    {
    	if(hexaPanel.isVisible() == true && command.equals("="))
    	{
    		double calcD = Double.valueOf(calc.calculation);
    		int calcInt = (int) calcD;
    		calc.calculation = (Integer.toHexString(calcInt)).toUpperCase();
    	}
    	
    	display.setText("" + calc.calculation);

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
}
