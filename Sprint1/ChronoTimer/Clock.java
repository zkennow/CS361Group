import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 *
 * @author ben
 */
public class Clock {

	private static long _last;
	private static Date _time;
	private static SimpleDateFormat _format = new SimpleDateFormat("hh:mm:ss");

	protected static boolean setTime(String newTime){
		_last = System.currentTimeMillis();
		try{
			_time = _format.parse(newTime);
		}catch(ParseException er){
			return false;
		}
		return true;
	}

	protected static void setSystemTime(){
		_time = null;
	}

	protected static String getTime(){
		if(_time != null)
			return _format.format(new Date(_time.getTime() + (System.currentTimeMillis()- _last)));
	
		return _format.format(new Date());
	}

	protected static Date getDate(){
		if(_time != null)
			return new Date(_time.getTime() + (System.currentTimeMillis()- _last));
		
		return new Date();	
	}

	protected static long getLong(){
		if(_time != null)
			return  _time.getTime() + (System.currentTimeMillis()- _last);
		
		return System.currentTimeMillis();
	}

	protected static String format(Date date){
		return _format.format(date);
	}
}

