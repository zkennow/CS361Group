/**
 *
 * @author ben
 */
public class Racer {
    
    private long _start;
    private long _end;
    private String _name;
    private String _raceTime;
    
    public Racer(String id) {
        _name = id;
    }
    
    public void start() {
        _start = Clock.getLong();
    }
    
    public void end() {
        _end = Clock.getLong();
        _raceTime = format(_end - _start);
    }
    
    public void DNF() {
        _raceTime = "DNF";
    }
    
    public String print() {
    	
        if(_raceTime == null)
            throw new IllegalStateException();
        
       if(_raceTime.equalsIgnoreCase("DNF"))
           return _name +" , " + _raceTime;
       
       return _name + " , " + _raceTime;
    }
    
    private static String format(long duration) {
    	
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
