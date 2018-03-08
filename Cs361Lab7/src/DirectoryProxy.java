
public class DirectoryProxy implements Directory {

	MainDirectory _main;
	
	// should constructor take MainDirectory as param?
	public DirectoryProxy(MainDirectory main) {
		_main = main;
	}
	
	public void add(String JsonString) {
		_main.add(JsonString);
	}
	
	public void print() {
		_main.print(); 
	}
	
	public void clear() {
		_main.clear();
	}
}
