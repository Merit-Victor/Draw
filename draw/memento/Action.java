package eg.edu.alexu.csd.oop.draw.memento;

import eg.edu.alexu.csd.oop.draw.Shape;

/**
 * @author Merit Victor Action Class.
 */
public class Action {

	/**
	 * 
	 */
	private State mState;

	/**
	 * 
	 */
	private Shape oldShape;

	/**
	 * 
	 */
	private Shape newShape;

	/**
	 * Constructor.
	 * 
	 * @param state
	 *            given.
	 * @param newShape
	 *            given.
	 */
	public Action(State state, Shape newShape) {
		this.mState = state;
		this.newShape = newShape;
	}

	/**
	 * Constructor.
	 * 
	 * @param state
	 *            given.
	 * @param oldShape
	 *            given.
	 * @param newShape
	 *            given.
	 */
	public Action(State state, Shape oldShape, Shape newShape) {
		this.mState = state;
		this.oldShape = oldShape;
		this.newShape = newShape;
	}

	/**
	 * Returns the state.
	 * 
	 * @return state.
	 */
	public State getState() {
		return mState;
	}

	/**
	 * Setter for the state.
	 * 
	 * @param state
	 *            given.
	 */
	public void setState(State state) {
		this.mState = state;
	}

	/**
	 * Getter.
	 * 
	 * @return shape.
	 */
	public Shape getNewShape() {
		return newShape;
	}

	/**
	 * Getter.
	 * 
	 * @return
	 */
	public Shape getOldShape() {
		return oldShape;
	}

	/**
	 * Setter.
	 * 
	 * @param newShape2
	 *            given.
	 */
	public void setOldShape(Shape newShape2) {
		this.oldShape = newShape2;
	}

	/**
	 * Setter.
	 * 
	 * @param oldShape2
	 *            given.
	 */
	public void setNewShape(Shape oldShape2) {
		this.newShape = oldShape2;
	}
}
