import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFileChooser;

import custom_types.PPoint;
import custom_types.Subject;

/**
 * 
 * @author bfollington
 * The comparison class is able to load two MySINet xls files and compare
 * the data within them to determine free time.
 * 
 * It uses lists of Subject objects for the comparison
 *
 */

public class Comparison {
	
	//Initialise the free time array from 8-9 five days a week
	public static boolean[][] freeTime = new boolean[5][13];
	//Easy reference for days
	public static ArrayList<String> _days = new ArrayList<String>(Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday"));
	
	//File choosers
	final static JFileChooser fc1 = new JFileChooser();
	final static JFileChooser fc2 = new JFileChooser();
	
	public Comparison()
	{
		
		String path1, path2;
		
		if(fc1.showOpenDialog(null) == JFileChooser.CANCEL_OPTION) path1 = "src/ps.xls";
		else path1 = fc1.getSelectedFile().getPath();
		if(fc2.showOpenDialog(null) == JFileChooser.CANCEL_OPTION) path2 = "src/ps-3.xls";
		else path2 = fc2.getSelectedFile().getPath();
		
		Parser parse1 = new Parser(path1);
		Parser parse2 = new Parser(path2);
		fillArray();
		
		compare(parse1.getSubjects(), parse2.getSubjects());
		printArray();
	}
	
	/* Fill the free time array with default values */
	private static void fillArray()
	{
		for (int i = 0; i < freeTime.length; i++)
		{
			for (int j = 0; j < freeTime[0].length; j++)
			{
				freeTime[i][j] = true;
			}
		}
	}
	
	/* Takes two lists of subjects and edits the free time array to determine when two people share a break */
	private static void compare(ArrayList<Subject> one, ArrayList<Subject> two)
	{
		//The earliest class is at 8
		int timeOffset = 8;
		int dayOfWeek = 0;
		
		//For each list, loop through each entry and blank out the appropriate slots in the array
		for (Subject sub : one)
		{
			dayOfWeek = _days.indexOf(sub._day);
			
			for (int i = sub._startTime; i < sub._endTime; i++)
			{
				freeTime[dayOfWeek][i-timeOffset] = false;
			}
		}
		
		for (Subject sub : two)
		{
			dayOfWeek = _days.indexOf(sub._day);
			
			for (int i = sub._startTime; i < sub._endTime; i++)
			{
				freeTime[dayOfWeek][i-timeOffset] = false;
			}
		}
	}
	
	/* Print a text representation of the free time array to System.out */
	public static void printArray()
	{
		
		int endOfRange = 0;
		
		for (int i = 0; i < freeTime.length; i++)
		{
			for (int j = 0; j < freeTime[0].length; j++)
			{
				if(freeTime[i][j])
				{
					//This code determine how long the period two people have off is
					endOfRange = getRange(i, j);
					System.out.println(_days.get(i) + ": " + (j+8) + "-" + (endOfRange+8));
					j = endOfRange;
				}
			}
		}
	}
	
	public ArrayList<PPoint> getPlotPoints()
	{
		
		ArrayList<PPoint> retPoints = new ArrayList<PPoint>();
		
		for (int i = 0; i < freeTime.length; i++)
		{
			for (int j = 0; j < freeTime[0].length; j++)
			{
				if(freeTime[i][j])
				{
					retPoints.add(new PPoint(i, j));
				}
			}
		}
		
		return retPoints;
		
	}
	
	/* Recursively calculate the length of a block of free time */
	public static int getRange(int day, int hour)
	{
		
		int _day, _hour;
		_day = day;
		_hour = hour;
		
		if (!freeTime[_day][_hour])
		{
			return _hour;
		} else if (_hour < 12) {
			return getRange(_day, _hour + 1);
		}
		
		return 13;
			
	}
	
}
