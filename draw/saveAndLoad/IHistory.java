package eg.edu.alexu.csd.oop.draw.saveAndLoad;

/**
 * @author Merit Victor
 *
 */
public interface IHistory {
	
	/**
	 * @param path
	 * saving the current state 
	 */
	public void save(String path);
	
	
	/**
	 * @param path
	 * loading a saved state with directed path 
	 */
	public void load (String path);
	

}
