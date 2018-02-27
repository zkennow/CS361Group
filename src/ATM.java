package ATM;
import Simulator.Simulator;

public class ATM {

	private String _time;				// time of last command
	private String _currentCommand; 	// current command read into ATM
	private int _state;					// current state of ATM
	private Bank _bank;					// bank associated with this ATM
	private Account _currentAccount;	// current account active on ATM

	private Printer _printer;			// printer connected to ATM
	private CardReader _cardReader;		// card reader connected to ATM
	private CashDispenser _dispenser;	// cash dispenser connected to ATM
	private Simulator _sim;				// simulator for ATM system

	public ATM(Simulator sim){

		_sim = sim;
		_bank = new Bank(this);
		_cardReader = new CardReader(this);
		_dispenser = new CashDispenser();
		_printer = new Printer();

	}

	public void start(){
		_state = 1;
	}

	public Bank getBank() {
		return _bank;
	}

	public double getBalance(){
		return _currentAccount.getBalance();
	}

	protected String getCurrentCommand() {
		return _currentCommand;
	}

	public void getStatement(){

		switch(_state){
		case 0:
			_sim.display("ATM OFF");
			return;
		case 1:
			_sim.display("ENTER ACCOUNT");
			return;
		case 2:
			_sim.display("ENTER PIN");
			return;
		case 3:
			_sim.display("CHOOSE TRANSACTION");
			return;
		case 4:
		case 5:
			_sim.display("AMOUNT?");
			return;
		}
	}

	public void execute(String command){

		try {

			_time = command.substring(0, command.indexOf(" "));	
			_currentCommand = command.trim().substring(command.indexOf(" ") + 1);

			if(_currentCommand.equals("BUTTON CANCEL")) {
				_state = 1;
				_currentAccount = null;
				return;
			}
			else if(_currentCommand.startsWith("PRINT")) 
				_printer.print(_currentCommand.substring(_currentCommand.indexOf("PRINT ") + 6));

			switch(_state){
			// Account #
			case 1:        

				// pass control to _cardReader
				if( !_cardReader.acctNumber() )
					return;

				// get account # from _cardReader and get Account from _bank
				if( (_currentAccount = _bank.getAccount(_cardReader.getAccountNumber())) == null )
					return;

				_state = 2;

				return;

				// Pin #
			case 2:

				// have _bank validate _currentAccount
				if(!_bank.validate(_currentAccount))
					return;

				_state = 3;
				return;

				// Transaction?
			case 3:

				if(_currentCommand.trim().equals("BUTTON W"))
					_state = 4;

				else if(_currentCommand.trim().equals("BUTTON D")) 
					_state = 5;

				else if(_currentCommand.trim().equals("BUTTON CB")) {
					_sim.display(String.format("Current Balance = $%.2f", getBalance()));
					return;
				}
			// Amount? _state 4 = withdrawal, 5 = deposit
			case 4:
			case 5:

				if(!_currentCommand.startsWith("NUM"))
					return;

				double amount = Double.parseDouble(_currentCommand.substring(_currentCommand.indexOf("NUM") + 4));
				String transaction = "D";
				double trans = amount;

				// if withdrawal multiply amount by -1
				if(_state == 4) {
					trans = amount * -1;
					transaction = "W";
				}

				if(!_currentAccount.transaction(trans))
					amount = 0;

				// does nothing
				if(_state == 5)
					_dispenser.dispense(amount);

				_printer.printReceipt(_time, transaction, amount);
				_state = 3;

			default : return;
			}	

		// catch all exceptions
		}catch(Exception e) {
			return;
		}

	}

	public int getState(){
		return _state;
	}

}
