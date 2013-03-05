import java.util.ArrayList;

import lib.ExtStr;

import custom_types.Subject;


public class SubjectParser {
	
	private String[] _lines;
	//Lines to "ignore" in the source file
	private String[] _days = {"Course", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
	//An array of information about each session in a day
	private String[] _nodeInfo = new String[6];
	private String _currentDay;
	
	public ArrayList<Subject> subjects = new ArrayList<Subject>();

	public SubjectParser(String[] path)
	{
		_lines = path;
		
		
		//Get nodes and get subjects / times
		for (int i = 0; i < _lines.length; i++)
		{
			//A new row in the table means a day or a subject
			if (_lines[i].contentEquals("<tr>"))
			{
				//If it's a day, save what the day is so we can tell the object later
				if (isDay(returnNodeContent(_lines[i+1])))
				{
					_currentDay = returnNodeContent(_lines[i+1]);
				//Otherwise we want to parse the node info out of the file
				} else {
					
					for (int j = 0; j < _nodeInfo.length; j++)
					{
						_nodeInfo[j] = returnNodeContent(_lines[j+i+1]);
						//System.out.print(_nodeInfo[j] + ", ");
					}
					
					//Add a new subject to the list of subjects in a week
					subjects.add(new Subject(_currentDay, _nodeInfo[0], _nodeInfo[1], convertTimetoInt(_nodeInfo[4]), convertTimetoInt(_nodeInfo[5])));
					System.out.print(subjects.get(subjects.size()-1).toString());
					
					System.out.print("\n");
					
				}
			}
		}
	}
	
	public ArrayList<Subject> getSubjects()
	{
		return subjects;
	}
	
	public int convertTimetoInt(String input)
	{
		boolean morning = isMorning(input);
		
		input = input.substring(0, input.length() - 3);
		
		ArrayList<String> time = ExtStr.splitAt(':', input);
		
		// If it's in the morning, convert time to 24hr int, rounded to nearest hour
		// Otherwise if it's 12PM just return 12
		// If it's the afternoon, add 12 and return the time rounded to the nearest hour
		if (morning) return Integer.parseInt(time.get(0)) + (Integer.parseInt(time.get(1))+10)/60;
		else if (time.get(0).contentEquals("12")) return Integer.parseInt(time.get(0)) + (Integer.parseInt(time.get(1))+10)/60;
		else return Integer.parseInt(time.get(0)) + 12 + (Integer.parseInt(time.get(1))+10)/60;
	}
	
	/* Can determine whether a string representation of a time (12:00 AM) is morning or afternoon */
	public boolean isMorning(String input)
	{
		if (input.endsWith("AM"))
		{
			return true;
		} else {
			return false;
		}
	}
	
	/* Can determine is a String is the name of a day */
	public boolean isDay(String node)
	{
		for (String day : _days)
		{
			if (node.contentEquals(day))
			{
				return true;
			}
		}
		
		return false;
	}
	
	/* gets the content within an HTML/XML tag, i.e. <td>HELLO</td> -> HELLO */
	public String returnNodeContent(String str)
	{
		return ExtStr.splitAt('>', ExtStr.splitAt('<', str).get(1)).get(1);
	}
	
	
	
}
