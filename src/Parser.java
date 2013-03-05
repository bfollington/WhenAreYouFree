import java.io.IOException;
import java.util.ArrayList;

import custom_types.Subject;

public class Parser {
	
	private SubjectParser _sp;
	
	/* Set up a Parser and provide a list of string to a DecipherNodes class */
	public Parser(String filePath)
	{
		String path = filePath;
		String lines[] = null;
		
		//Load file
		try {
			FileLoader file = new FileLoader(path);
			lines = file.openFile();
		} catch (IOException e)
		{
			System.err.print("Error " + e);
		}
		
		_sp = new SubjectParser(lines);
	
	}
	
	public ArrayList<Subject> getSubjects()
	{
		return _sp.getSubjects();
	}
	
}
