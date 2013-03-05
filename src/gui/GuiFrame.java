package gui;
import javax.swing.*;

import custom_types.PPoint;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GuiFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1386754337213901638L;
	
	// a panel for displaying the animation
	private GuiPanel panel = new GuiPanel(0);
	
	/** Create a new NekoFrame. */
	public GuiFrame() {
		setTitle("When are you free?");
		setName("When are you free?");
		setBounds(100,50,500,325);
		
		Container c = getContentPane();
		c.add(panel);

		
		addWindowListener(new WindowAdapter ( ) {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

	}
	
	public void plotPoints(ArrayList<PPoint> arrayList)
	{
		for (PPoint pt : arrayList)
		{
			panel.addNewDrawPosition(pt.x, pt.y);
		}
	}
	
	/** Start the animation. */  
	public void drawContent() {
		panel.drawContent();
	}
}

