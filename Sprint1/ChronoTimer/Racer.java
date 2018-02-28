/**
 *
 * @author ben
 */
public class Racer {
    
    private long _start;
    private long _end;
    private String _name;
    private String _raceTime;
    
    protected Racer(String id) {
        _name = id;
    }
    
    protected String getName() {
    	return _name;
    }
    
    protected void start() {
        _start = Clock.getLong();
    }
    
    protected void end() {
        _end = Clock.getLong();
        _raceTime = format(_end - _start);
    }
    
    protected void DNF() {
        _raceTime = "DNF";
    }
    
    protected void print() {
    	
        if(_raceTime == null)
            return;
        
       Printer.print(_name +" , " + _raceTime);
    }
    
    protected static String format(long duration) {
    	
        double dur = new Long(duration).doubleValue();
        double second = 1000.0;
        double min = second*60.0;
        double hour = min*60.0;
        int hours = (int) Math.floor(dur/hour);
        dur -=(hours*hour);
        int mins = (int)Math.floor(dur/min);
        dur -= (mins*min);
        int secs = (int)Math.floor(dur/second);
        String ret = hours +":"+mins+":"+secs;
        return ret;
    }
       
}
