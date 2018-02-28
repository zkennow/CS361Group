import java.text.SimpleDateFormat;
/**
 * This is the controller class for the ChronoTimer.
 * @author Matt
 *
 */
public class Controller {

	private Run _currentRun;
	private String _eventType;
	private boolean _power;
	public Parser _parser;
	private Channel[] _channels;
	final private int num_of_channels = 8;

	public Controller() {
		// set Defaults
		_parser = new Parser();
		_eventType = "IND";			
		_channels = new Channel[num_of_channels];
		_power = false;

		for(int i = 0; i < _channels.length; ++i) {
			_channels[0] = new Channel(i+1);
		}

	}

	private void power() {
		_power = !_power;
	}

	private void setTime(String time) {
		Clock.setTime(time);
	}

	private void reset() {
		_currentRun = null;
		_eventType = "IND";

		for(Channel c: _channels) {
			if(c.getState())
				c.toggleState();
		}
	}

	private void newRun() {
		_currentRun = new Run(_eventType);
	}
	
	private void endRun() {
		_currentRun = null;
	}

	private void toggle(Channel chan) {
		chan.toggleState();
	}

	private void trigger(Channel chan) {

		// for IND races i.e. sprint 1
		if(chan.getNum() == 1) 
			_currentRun.start();

		else if (chan.getNum() == 2)
			_currentRun.finish();
	}

	private void connect(int index, String sensor) {

			_channels[index].setSensor(sensor);

	}

	private void printRun() {

		_currentRun.print();
	}

	public void execute(String line) {

		try {
			String[] args = line.split(" ");

			switch(args[0]) {

			case "POWER":	power(); 	return;
			case "RESET":	reset(); 	return;
			case "TIME" :	setTime(args[1]); return;
			case "TOG" 	:	toggle(_channels[Integer.parseInt(args[1]) - 1]); return;
			case "TRIG" :	trigger(_channels[Integer.parseInt(args[1])] - 1); return;
			case "CONN" :	connect(Integer.parseInt(args[1]) - 1, args[1]); return;
			case "EVENT":	_eventType = args[1]; return;
			case "NEWRUN":	newRun(); 	return;
			case "ENDRUN":  endRun(); 	return;
			case "PRINT" : 	printRun(); return;
			case "NUM" 	: 	_currentRun.addRacer(args[1]); return;
			case "SWAP" : 	_currentRun.swap(); return;
			case "DNF" 	: 	_currentRun.DNF(); 	return;
			case "START": 	trigger(_channels[0]); return;
			case "FINISH":  trigger(_channels[1]); return;
			}
			
		}catch(Exception e){
			return;
		}
	}

}
