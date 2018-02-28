import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 *
 * @author ben
 */
public class Clock {

	private static long last;
	private static Date time;
	private static SimpleDateFormat form = new SimpleDateFormat("hh:mm:ss");

	public static boolean setTime(String newTime){
		last = System.currentTimeMillis();
		try{
			time = form.parse(newTime);
		}catch(ParseException er){
			return false;
		}
		return true;
	}

	public static void setSystemTime(){
		time = null;
	}


	public static String getTime(){
		if(time != null){
			return form.format(new Date(time.getTime() + (System.currentTimeMillis()- last)));
		}else{
			return form.format(new Date());
		}
	}

	public static Date getDate(){
		if(time != null){
			return new Date(time.getTime() + (System.currentTimeMillis()- last));
		}else{
			return new Date();
		}
	}


	public static long getLong(){
		if(time != null){
			return  time.getTime() + (System.currentTimeMillis()- last);
		}else{
			return System.currentTimeMillis();
		}
	}

	public static String format(Date date){
		return form.format(date);
	}
}

