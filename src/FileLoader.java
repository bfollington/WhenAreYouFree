import java.io.*;

/**
 * 
 * @author bfollington
 * The file loader class is capable of taking the path to a file,
 * opening it, and outputting the result to a list of strings.
 * 
 * Its methods are static, don't instantiate it (but you can).
 *
 */

public class FileLoader {

	private static String _path;
	
	public FileLoader(String name)
	{
		_path = name;
		
	}
	
	/* Opens a file from a file path, errors are handled, returns a list of Strings */
	public String[] openFile() throws IOException
	{
		int numberOfLines = fileLength();
		String[ ] textData = new String[numberOfLines];
		
		FileReader fr = new FileReader(_path);
		BufferedReader textReader = new BufferedReader(fr);

		for (int i = 0; i < numberOfLines; i++)
		{
			textData[ i ] = textReader.readLine();
		}
		
		textReader.close( );
		return textData;
		
	}
	
	/* Calculates the number of lines in a file */
	public int fileLength() throws IOException
	{
		FileReader fr = new FileReader(_path);
		BufferedReader bf = new BufferedReader(fr);
		
		int numLines = 0;
		
		while (bf.readLine() != null)
		{
			numLines++;
		}
		
		bf.close();
		
		return numLines;
	}
	
}
