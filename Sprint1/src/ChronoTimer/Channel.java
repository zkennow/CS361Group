package ChronoTimer;
/**
 * The channel for the ChronoTimer.
 * 
 * @author Nic
 *
 */
public class Channel {
	
	private boolean _state;
	private String _sensor;
	private int _channelNum;
	
	public Channel(int channelNum) {
		_channelNum = channelNum;
	}
	
	/**
	 * Toggles the state of the channel.
	 */
	protected void toggleState() {
		if (_state)
			_state = false;
		else
			_state = true;
	}
	
	/**
	 * Connects a sensor to this channel.
	 * 
	 * @param sensor - the sensor being connected
	 */
	protected void setSensor(String sensor) {
		_sensor = sensor;
	}
	
	/**
	 * Gets the state of this channel.
	 * 
	 * @return state of channle on = true, off = false
	 */
	protected boolean getState() {
		return _state;
	}
	
	/**
	 * Gets the sensor connected to this channel.
	 * 
	 * @return sensor of this channel
	 */
	protected String getSensor() {
		return _sensor;
	}
	
	/**
	 * Gets the number associated to this channel.
	 * 
	 * @return number of channel
	 */
	protected int getNum() {
		return _channelNum;
	}
	
}
