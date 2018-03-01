package ChronoTimer;
/**
 * The parser for the ChronoTimer.
 * 
 * @author Nic
 */
public class Parser {

	private String _parsedCommand;
	
	protected Parser() {
		_parsedCommand = null;
	}
	
	/**
	 * Parses the line, and updates the system time and stores the parsed command.
	 * 
	 * @param line - the line to parse
	 */
	public void parse(String line) {
		
			String str = line.trim(); 							 // trim command
			
			if(!str.contains("	"))								 // if str doesn't contain tab return
				return;
			
			Clock.setTime(str.substring(0, str.indexOf("	")));// set system clock to time of command
			str = str.substring(str.indexOf("	")).trim();		 // parse out time
			
			_parsedCommand = str;								 // sets command to parsed command
	}
	
	/**
	 * Gets the parsed command and clears the command buffer.
	 * 
	 * @return the parsed command
	 */
	protected String getCommand() {
		
		String str = _parsedCommand;
		_parsedCommand = null;									 // clears command
		return str;												 // returns command
	}
	
}
