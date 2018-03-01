package ChronoTimer;
/**
 * The parser for the ChronoTimer.
 * 
 * @author Nic
 */
public class Parser {

	private String _parsedCommand;
	private boolean _parsed;
	
	public Parser() {
		_parsedCommand = null;
		_parsed = false;
	}
	
	public void parse(String line) {	
		
		if (_parsed != true) {								//does nothing has been parsed
			String str = line.trim(); 						//trim command
			str = str.substring(str.indexOf(" ")).trim();	//parse out time
			_parsedCommand = str;							//sets command to parsed command
			_parsed = true;									//sets parsed flag true
		}
	}
	
	protected String getCommand() {							//returns command and resets state
		
		String str = _parsedCommand;
		_parsedCommand = null;								//clears command
		_parsed = false;									//resets parsed flag
		return str;											//returns command
	}
	
}
