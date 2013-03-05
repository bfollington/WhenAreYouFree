package custom_types;

public class Subject {
	
	public String _code, _title, _day;
	public int _startTime, _endTime;
	
	public Subject(String day, String code, String title, int startTime, int endTime)
	{
		_day = day;
		_code = code;
		_title = title;
		_startTime = startTime;
		_endTime = endTime;
	}
	
	public String toString()
	{
		return _day + ", " + _code + ", " + _title + ", " + Integer.toString(_startTime) + ", " + Integer.toString(_endTime);
 	}

}
