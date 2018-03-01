package ChronoTimer;
import java.util.LinkedList;
/**
 * Represents a run of type "event".
 * 
 * @author Ben
 */
public class Run {
	
	String _event;
	LinkedList<Racer> _waiting;
	LinkedList<Racer> _racing;
	LinkedList<Racer> _finished;
	
	protected Run() {
		this("IND");
	}
	
	protected Run(String eventType) {
		
		_event = eventType;
		_waiting = new  LinkedList<Racer>();
		_racing = new  LinkedList<Racer>();
		_finished = new  LinkedList<Racer>();
	}

	/**
	 * Adds a new racer to the end of the waiting queue.
	 * 
	 * @param id - name of racer to add.
	 */
	protected void addRacer(String id) {
		Racer racer = new Racer(id);
		_waiting.push(racer);
	}

	/**
	 * Moves the racer at the beginning of the waiting queue 
	 * to the end of the racing queue, and records that racers start time.
	 */
	protected void start() {
		
		Racer racer;
		if(hasRacerWaiting()) 
			racer = _waiting.removeFirst();
		else 
			racer = new Racer("000"); // anonymous racer
		
		racer.start();
		_racing.addLast(racer);
	}

	/**
	 * Moves the racer at the beginning of the running queue 
	 * to the end of the finished queue, and records that racers end time.
	 * 
	 * @return returns true if successful
	 */
	protected boolean finish() {
		
		if(!hasActiveRace())
			return false;
		
		Racer racer = _racing.removeFirst();
		racer.end();
		//racer.print();
		_finished.addLast(racer);
		return true;
	}

	/**
	 * Moves the racer at the beginning of the running queue 
	 * to the end of the finished queue, and records that the racer did not finish (DNF).
	 * 
	 * @return returns true if successful
	 */
	protected boolean DNF() {
		
		if(!hasActiveRace())
			return false;
		
		Racer racer = _racing.removeFirst();
		racer.DNF();
		//racer.print();
		_finished.addLast(racer);
		return true;
	}

	/** 
	 * Moves the racer at the end of the racing queue
	 * to the beginning of the waiting queue.
	 *
	 *  @return returns true if successful
	 */
	protected boolean cancel() {
		
		if(!hasActiveRace())
			return false;
		
		Racer racer = _racing.removeLast();
		_waiting.push(racer);
		return true;
	}
	
	/**
	 * Swaps the two lead racers in the racing queue.
	 * 
	 * @return true if successful
	 */
	protected boolean swap() {
		
		if(racingQueueSize() < 2)
			return false;
		
		Racer tmp = _racing.removeFirst();
		_racing.add(1, tmp);
		return true;
	}
	
	/**
	 * Prints the results of this run.
	 */
	protected void print() {
		for(Racer r : _finished)
			r.print();
	}

	/**
	 * @return size of racing queue.
	 */
	private int racingQueueSize() {
		return _racing.size();
	}

	/**
	 * @return true if racing queue is not empty.
	 */
	private boolean hasActiveRace() {
		return !_racing.isEmpty();
	}

	/**
	 * @return true if waiting queue is not empty.
	 */
	private boolean hasRacerWaiting() {
		return !_waiting.isEmpty();
	}

}
