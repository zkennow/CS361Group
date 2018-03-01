package ChronoTimer;
/**
 * The channel for the 
 * 
 * @author Nic
 *
 */
public class Channel {
	
	private boolean _state;
	private String _sensor;
	private int _channelNum;
	
	public Channel(int channelNum) {
		_state = false;
		_sensor = null;//initialize sensor?
		_channelNum = channelNum;
	}
	
	protected void toggleState() {					//toggles state
		if (_state)
			_state = false;
		else
			_state = true;
	}
	
	protected void setSensor(String sensor) {		//set sensor type
		_sensor = sensor;
	}
	
	protected boolean getState() {					//returns current state
		return _state;
	}
	
	protected String getSensor() {					//returns sensor type
		return _sensor;
	}
	
	protected int getNum() {						//returns current channel num
		return _channelNum;
	}
	
}
