package ChronoTimer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * The system clock for the ChronoTimer.
 * 
 * @author Ben
 */
public class Clock {

	private static long _last;
	private static Date _time;
	private static SimpleDateFormat _format = new SimpleDateFormat("hh:mm:ss");

	/**
	 * Sets the system time to the time expressed by newTime.
	 * 
	 * @param newTime - new system time formated as "hh:mm:ss"
	 * @return true if successful
	 */
	protected static boolean setTime(String newTime) {
		_last = System.currentTimeMillis();
		try{
			_time = _format.parse(newTime);
		}catch(ParseException er){
			return false;
		}
		return true;
	}

	/**
	 * Sets the system time.
	 */
	protected static void setSystemTime() {
		_time = null;
	}

	/**
	 * Gets the system time as a String in the form "hh:mm:ss".
	 * 
	 * @return formated time as a String in the form "hh:mm:ss"
	 */
	protected static String getTime() {
		if(_time != null)
			return _format.format(new Date(_time.getTime() + (System.currentTimeMillis()- _last)));
	
		return _format.format(new Date());
	}

	/**
	 * Gets the system time as a Date.
	 * 
	 * @return time of the system as a Date
	 */
	protected static Date getDate() {
		if(_time != null)
			return new Date(_time.getTime() + (System.currentTimeMillis()- _last));
		
		return new Date();	
	}

	/**
	 * Gets the system time as a long.
	 * 
	 * @return time of the system as a long
	 */
	protected static long getLong() {
		if(_time != null)
			return  _time.getTime() + (System.currentTimeMillis()- _last);
		
		return System.currentTimeMillis();
	}
}

