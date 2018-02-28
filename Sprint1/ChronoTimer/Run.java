import java.util.LinkedList;

/**
 *
 * @author ben
 */
public class Run {
	LinkedList<Racer> _waiting;
	LinkedList<Racer> _racing;
	LinkedList<Racer> _finished;
	String _event;
	
	protected Run() {
		this("IND");
	}
	
	protected Run(String eventType) {
		_event = eventType;
		_waiting = new  LinkedList<Racer>();
		_racing = new  LinkedList<Racer>();
		_finished = new  LinkedList<Racer>();
	}

	protected void addRacer(String id) {
		Racer racer = new Racer(id);
		_waiting.addLast(racer);
	}

	protected void start() {
		
		if(!_waiting.isEmpty()) {
			Racer racer = _waiting.removeFirst();
			racer.start();
			_racing.addLast(racer);
		} else {
			Racer racer = new Racer("000"); // anonymous racer number
			racer.start();
			_racing.addLast(racer);
		}
	}

	/**
	 *
	 *  @return returns true only if there was a racer in the racing queue
	 */
	protected boolean finish() {
		
		if(_racing.isEmpty())
			return false;
		
		Racer racer = _racing.removeFirst();
		racer.end();
		racer.print(); //this just returns a string right now. not sure if i should acutally print it
		_finished.addLast(racer);
		return true;
	}

	/**
	 *
	 *  @return returns true only if there was a racer in the racing queue
	 */
	protected boolean DNF() {
		
		if(_racing.isEmpty())
			return false;
		
		Racer racer = _racing.removeFirst();
		racer.DNF();
		racer.print(); //this just returns a string right now. not sure if i should acutally print it
		_finished.addLast(racer);
		return true;
	}


	/** takes racer out of racing queue and pushes back into waiting queue, if a racer exists.
	 *
	 *  @return returns true only if there was a racer in the racing queue
	 */
	protected boolean cancel() {
		
		if(_racing.isEmpty())
			return false;
		
		Racer racer = _racing.removeFirst();
		_waiting.addLast(racer);
		return true;
	}
	
	protected boolean swap() {
		
		if(_racing.size() < 2)
			return false;
		
		Racer tmp = _racing.removeFirst();
		_racing.add(1, tmp);
		return true;
	}
	
	protected void print() {
		for(Racer r : _finished)
			r.print();
	}

	public int waitintgQueueSize() {
		return _waiting.size();
	}

	public int racingQueueSize() {
		return _racing.size();
	}

	public int finishedQueueSize() {
		return _finished.size();
	}

	public boolean hasActiveRace() {
		return !_racing.isEmpty();
	}

	public boolean hasRacerWaiting() {
		return !_waiting.isEmpty();
	}

}
