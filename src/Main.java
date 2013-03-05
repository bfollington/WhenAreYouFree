import gui.GuiFrame;

public class Main {

	public static void main(String [] args) {
		GuiFrame frame = new GuiFrame();
		Comparison compare = new Comparison();
		
		frame.plotPoints(compare.getPlotPoints());
		
		frame.setVisible(true);
		frame.drawContent();
	}
}
