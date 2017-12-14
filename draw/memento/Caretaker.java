package eg.edu.alexu.csd.oop.draw.memento;

import java.util.Stack;

import eg.edu.alexu.csd.oop.draw.Shape;

/**
 * @author Merit Victor
 *
 */
public class Caretaker {

	/**
	 * 
	 */
	private Stack<Memento> mementoeStack = 
			new Stack<>();

	/**
	 * 
	 */
	private Stack<Memento> redoStack = 
			new Stack<Memento>();

	/**
	 * 
	 */
	private static final int TWENTY = 20;

	/**
	 * Add memento.
	 * 
	 * @param m
	 *            memento.
	 */
	public void addMemento(Memento m) {
		if (mementoeStack.size() 
				+ redoStack.size() == TWENTY) {
			mementoeStack.remove(0);
		}
		mementoeStack.push(m);
	}

	/**
	 * UNDO.
	 * 
	 * @return memento.
	 */
	public Memento rollback() {
		if (!mementoeStack.isEmpty()) {
			Memento memento = mementoeStack.pop();
			memento = switchState(memento);
			redoStack.push(memento);
			return memento;
		} else {
			return null;
		}
	}

	/**
	 * REDO.
	 * 
	 * @return memento.
	 */
	public Memento stepForward() {
		if (!redoStack.isEmpty()) {
			Memento memento = redoStack.pop();
			memento = switchState(memento);
			mementoeStack.push(memento);
			return memento;
		} else {
			return null;
		}

	}

	/**
	 * Switch.
	 * 
	 * @param memento
	 *            given.
	 * @return memento after switch.
	 */
	private Memento switchState(Memento memento) {
		if (memento instanceof MultiMemento) {
			for (Memento thisMemento :
				((MultiMemento) memento).getMementos()) {
				thisMemento = switchState(thisMemento);
			}
		} else {
			if (memento.getAction().getState() == State.added) {
				memento.getAction().setState(State.removed);
			} else if (memento.getAction().getState() == State.removed) {
				memento.getAction().setState(State.added);
			} else if (memento.getAction().getState() == State.updated) {
				Shape temp = memento.getAction().getOldShape();
				memento.getAction().setOldShape(
						memento.getAction().getNewShape());
				memento.getAction().setNewShape(temp);
			}
		}
		return memento;
	}

	/**
	 * Clears REDO stack.
	 */
	public void clearRedoStack() {
		redoStack.clear();
	}

	/**
	 * Clears UNDO stack.
	 */
	public void clearUndoStack() {
		mementoeStack.clear();
	}
}
