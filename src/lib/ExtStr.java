package lib;
import java.util.ArrayList;

/**
 * 
 * @author bfollington
 *
 * An extended string class I wrote, with a static method for splitting text at delimiters
 *
 */

public class ExtStr {

	/* Custom implementation of a split at delimiter function */
	public static ArrayList<String> splitAt(char del, String src)
	{
		
		ArrayList<String> retArr = new ArrayList<String>();
		int start = 0;
		
		for (int i = 0; i < src.length(); i++)
		{
			if (src.charAt(i) == del)
			{
				retArr.add(src.substring(start, i));
				start = i+1;
			}
		}
		
		retArr.add(src.substring(start, src.length()));
		
		return retArr;
	}
	
}
