package ChronoTimer;
/**
 * The racer actor in the system.
 * 
 * @author Ben
 */
public class Racer {
	
	private String _name;
    private long _start;		// start time
    private long _end;			// end time
    private String _raceTime;	// total race time
    
    protected Racer(String id) {
        _name = id;
    }
    
    /**
     * @return name of racer
     */
    protected String getName() {
    	return _name;
    }
    
    /**
     * Records the start time of the racer.
     */
    protected void start() {
        _start = Clock.getLong();
    }
    
    /**
     * Records the end time of the racer.
     */
    protected void end() {
        _end = Clock.getLong();
        _raceTime = format(_end - _start);
    }
    
    /**
     * Records that the racer did not finish (DNF).
     */
    protected void DNF() {
        _raceTime = "DNF";
    }
    
    /**
     * Prints the results of the racer in the form "<name> <time>".
     */
    protected void print() {
    	
        //if(_raceTime == null)
            //return;
        
       Printer.print(_name + " " + _raceTime);
    }
    
    /**
     * Formats a duration of time (long) in the form "hh:mm:ss"
     * and returns it as a String.
     * 
     * @param duration - the duration of time as a long.
     * @return the duration as a String.
     */
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
        return hours+":"+mins+":"+secs;
    }
       
}
