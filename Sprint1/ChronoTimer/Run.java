import java.util.LinkedList;

/**
 *
 * @author ben
 */
public class Run {
	LinkedList<Racer> _waiting;
	LinkedList<Racer> _racing;
	LinkedList<Racer> _finished;

	public Run() {
		_waiting = new  LinkedList<Racer>();
		_racing = new  LinkedList<Racer>();
		_finished = new  LinkedList<Racer>();
	}

	public void addRacer(String id) {
		Racer racer = new Racer(id);
		_waiting.push(racer);
	}

	public void start() {
		
		if(!_waiting.isEmpty()) {
			Racer racer = _waiting.pop();
			racer.start();
			_racing.push(racer);
		} else {
			Racer racer = new Racer("defualt"); //dont know what else to put for now
			racer.start();
			_racing.push(racer);
		}
	}

	/**
	 *
	 *  @return returns true only if there was a racer in the racing queue
	 */
	public boolean finish(){
		if(_racing.isEmpty()){
			return false;
		}
		Racer racer = _racing.pop();
		racer.end();
		racer.print();//this just returns a string right now. not sure if i should acutally print it
		_finished.push(racer);
		return true;
	}

	/**
	 *
	 *  @return returns true only if there was a racer in the racing queue
	 */
	public boolean DNF(){
		if(_racing.isEmpty()){
			return false;
		}
		Racer racer = _racing.pop();
		racer.DNF();
		racer.print();//this just returns a string right now. not sure if i should acutally print it
		_finished.push(racer);
		return true;
	}


	/** takes racer out of racing queue and pushes back into waiting queue, if a racer exists.
	 *
	 *  @return returns true only if there was a racer in the racing queue
	 */
	public boolean cancel(){
		if(_racing.isEmpty()){
			return false;
		}
		Racer racer = this._racing.pop();
		_waiting.push(racer);
		return true;
	}

	public int waitintgQueueSize(){
		return _waiting.size();
	}

	public int racingQueueSize(){
		return _racing.size();
	}

	public int finishedQueueSize(){
		return _finished.size();
	}


	public boolean hasActiveRace(){
		return !_racing.isEmpty();
	}

	public boolean hasRacerWaiting(){
		return !_waiting.isEmpty();
	}

}
