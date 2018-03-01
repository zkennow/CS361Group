package ChronoTimer;
/**
 * The controller for the ChronoTimer.
 * This is the Interface for the ChronoTimer package.
 * 
 * @author Matt
 */
public class Controller {

	private Run _currentRun;
	private String _eventType;
	private boolean _power;
	private Parser _parser;
	private Channel[] _channels;			// array of channels where the index = channel (index + 1).
	final private int num_of_channels = 8;	// default number of channels.

	public Controller() {
		
		// set Defaults
		_parser = new Parser();
		_channels = new Channel[num_of_channels];
		_power = false;

		for(int i = 0; i < _channels.length; ++i) 
			_channels[i] = new Channel(i+1);		
	}

	/**
	 * Turns the power on and resets the system to initial state.
	 */
	private void powerON() {
		_power = true;
		reset();
	}
	
	/**
	 * Turns the power off.
	 */
	public void powerOFF() {
		_power = false;
	}

	/**
	 * Sets the system time to the time expressed by newTime.
	 * 
	 * @param time - new system time formated as "hh:mm:ss"
	 */
	private void setTime(String time) {
		Clock.setTime(time);
	}

	/**
	 * Resets the system to the initial state.
	 */
	private void reset() {
		_currentRun = null;
		_eventType = "IND";
		Clock.setSystemTime();
		for(Channel c: _channels) {
			if(c.getState())
				c.toggleState();
		}
	}

	/**
	 * Creates a new run if there isn't a current run in progress.
	 */
	private void newRun() {
		if (_currentRun == null)
			_currentRun = new Run(_eventType);
	}
	
	/**
	 * Ends the current run.
	 */
	private void endRun() {
		_currentRun = null;
	}

	/**
	 * Toggles the state of a channel.
	 * 
	 * @param chan - the channel to toggle
	 */
	private void toggle(Channel chan) {
		chan.toggleState();
	}

	/**
	 * Triggers a channel.
	 * 
	 * @param chan - the channel to trigger
	 */
	private void trigger(Channel chan) {

		// for IND races i.e. sprint 1
		if(chan.getNum() == 1) 
			_currentRun.start();

		else if (chan.getNum() == 2)
			_currentRun.finish();
	}

	/**
	 * Connects a sensor to a channel.
	 * 
	 * @param chan - the channel to connect.
	 * @param sensor - the sensor to connect.
	 */
	private void connect(Channel chan, String sensor) {
			chan.setSensor(sensor);
	}

	/**
	 * Prints the results of the current run.
	 */
	private void printRun() {
		_currentRun.print();
	}

	/**
	 * Executes the raw command from the simulator/user and parses it,
	 * then calls the corresponding method with the arguments, 
	 * or does nothing if a command is not recognized.
	 * 
	 * @param line - the raw command line from simulator.
	 */
	public void execute(String line) {
		
		_parser.parse(line);
		String command = _parser.getCommand();
		
		// return if command is null or power is off and command is not POWER
		if (command == null || (!_power && !command.equals("POWER")))
			return;
		
		try {
			String[] args = command.split(" ");

			switch(args[0]) {
			
			case "RESET":	reset(); 	return;
			case "NEWRUN":	newRun(); 	return;
			case "ENDRUN":  endRun(); 	return;
			case "PRINT" : 	printRun(); return;
			case "TIME" :	setTime(args[1]); return;
			case "SWAP" : 	_currentRun.swap(); return;
			case "DNF" 	: 	_currentRun.DNF(); 	return;
			case "EVENT":	_eventType = args[1]; return;
			case "START": 	trigger(_channels[0]); return;
			case "FINISH":  trigger(_channels[1]); return;
			case "NUM" 	: 	_currentRun.addRacer(args[1]); return;
			case "POWER":	if(_power) powerOFF(); else powerON(); return;
			case "TOG" 	:	toggle(_channels[Integer.parseInt(args[1]) - 1]); return;
			case "TRIG" :	trigger(_channels[Integer.parseInt(args[1]) -1]); return;
			case "CONN" :	connect(_channels[Integer.parseInt(args[2]) - 1], args[1]); return;
			}
			
		}catch(Exception e){
			return;
		}
	}

}
