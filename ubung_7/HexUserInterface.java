package ubung_7;
import java.awt.*;
import javax.swing.*;
/**
 * Write a description of class HexUserInterface here.
 *
 * @author Bartholomäus Berresheim
 * @version 2019.12.06
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

