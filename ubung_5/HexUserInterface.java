package ubung_5;
import java.awt.*;
import javax.swing.*;
/**
 * Write a description of class HexUserInterface here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class HexUserInterface extends UserInterface
{

    protected JPanel FcontentPane = (JPanel)frame.getContentPane();

	public HexUserInterface(CalcEngine engine) {
		super(engine);
		makePanel();
		FcontentPane.add(hexaPanel, BorderLayout.WEST);
		frame.pack();
	}

    public void makePanel(){


       hexaPanel = new JPanel(new GridLayout(4, 2));
             addButton(hexaPanel, "A");
             addButton(hexaPanel, "B");
             addButton(hexaPanel, "C");
             addButton(hexaPanel, "D");
             
             addButton(hexaPanel, "E");
             addButton(hexaPanel, "F");

    }
}

