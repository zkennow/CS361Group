package ATM;

public class CardReader {

	private ATM _atm;
	private int _accountNumber;

	public CardReader(ATM atm) {
		_atm = atm;
	}
	
	protected int getAccountNumber() {
		return _accountNumber;
	}

	protected boolean acctNumber() {

		String command = _atm.getCurrentCommand();
			
			try {				
				
				if(!command.startsWith("CARDREAD"))
					return false;
				
				// get Account # from command
				_accountNumber = Integer.parseInt(command.substring(command.indexOf("CARDREAD") + 9));
				
			}catch(Exception e){
				return false;
			}
		
		return true;
	}

}
