package eg.edu.alexu.csd.oop.draw.memento;

/**
 * @author Merit Victor
 *
 */
public class Memento {

	/**
	 * 
	 */
	private Action action;

	/**
	 * Constructor.
	 */
	public Memento() {

	}

	/**
	 * Constructor.
	 * 
	 * @param action
	 *            given.
	 */
	public Memento(Action action) {
		this.action = action;
	}

	/**
	 * Getter.
	 * 
	 * @return action.
	 */
	public Action getAction() {
		return this.action;
	}

}
