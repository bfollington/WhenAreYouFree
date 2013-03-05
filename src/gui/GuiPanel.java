package gui;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GuiPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3044791839699636536L;
	
	// still images of Neko the Cat
	private Image [] nekoPics = new Image[9];
	// current image
	private Image currentImage = null;
	
	private ArrayList<Integer> XCoord = new ArrayList<Integer>();
	private ArrayList<Integer> YCoord = new ArrayList<Integer>();
	
	/** Create a new NekoPanel. */
	public GuiPanel(int startX) {
		setBackground(Color.white);
		// the image files
		String nekosrc[] = { "src/images/freeblock.png", "src/images/right1.gif", "src/images/right2.gif",
				"src/images/stop.gif", "src/images/yawn.gif", "src/images/scratch1.gif", 
				"src/images/scratch2.gif", "src/images/sleep1.gif", "src/images/sleep2.gif", 
				"src/images/awake.gif" };
		// toolkit to create image object from .gif file
		Toolkit tk = Toolkit.getDefaultToolkit();
		for (int i=0; i < nekoPics.length; i++) 
			nekoPics[i] = tk.getImage(nekosrc[i]);
	}
	
	public void addNewDrawPosition(int x, int y)
	{
		XCoord.add(x*100);
		YCoord.add(y*25);
	}
	
	/** Render the free time slots*/
	public void paint(Graphics g) {
		super.paintComponent(g);

		for (int i = 0; i < XCoord.size(); i++)
		{
			//System.out.println(XCoord.get(i) + ", " +  YCoord.get(i));
			g.drawImage(currentImage, XCoord.get(i), YCoord.get(i), this);
		}
	}
	
	/** Graphically blit the timetable info */
	public void drawContent() {
		currentImage = nekoPics[0];
		repaint();
	}
	
}
