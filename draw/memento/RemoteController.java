package eg.edu.alexu.csd.oop.draw.memento;

import eg.edu.alexu.csd.oop.draw.command.*;
import eg.edu.alexu.csd.oop.draw.cs68.DrawingEngineImp;
import eg.edu.alexu.csd.oop.draw.shapes.FreeShape;

import java.util.List;

import eg.edu.alexu.csd.oop.draw.DrawingEngine;
import eg.edu.alexu.csd.oop.draw.Shape;

/**
 * @author Merit Victor
 *
 */
public class RemoteController {

	/**
	 * 
	 */
	private Caretaker mCaretaker;

	/**
	 * 
	 */
	private DrawingEngine mEngine;

	/**
	 * 
	 */
	private CommandFactory factory;

	/**
	 * 
	 */
	private List<Shape> mShapesList;

	/**
	 * Constructor.
	 * 
	 * @param engine
	 *            given.
	 * @param caretaker
	 *            given.
	 * @param shapesList
	 *            given.
	 */
	public RemoteController(DrawingEngine engine,
			Caretaker caretaker, List<Shape> shapesList) {
		this.mEngine = engine;
		this.mCaretaker = caretaker;
		this.factory = new CommandFactory();
		this.mShapesList = shapesList;
	}

	/**
	 * Add.
	 * 
	 * @param newShape
	 *            shape to be added.
	 */
	public void addShape(Shape newShape) {
		Action action = new Action(State.added, newShape);
		factory.execute(mEngine, mShapesList, action);
		mCaretaker.addMemento(new Memento(action));
	}

	/**
	 * Remove.
	 * 
	 * @param shape
	 *            to be removed.
	 */
	public void removeShape(Shape shape) {
		Action action = new Action(State.removed, shape);
		factory.execute(mEngine, mShapesList, action);
		mCaretaker.addMemento(new Memento(action));
	}

	/**
	 * Update.
	 * 
	 * @param oldShape
	 *            to be replaced.
	 * @param newShape
	 *            to replace with.
	 */
	public void updateShape(Shape oldShape, Shape newShape) {
		Action action = new Action(
				State.updated, oldShape, newShape);
		factory.execute(mEngine, mShapesList, action);
		mCaretaker.addMemento(new Memento(action));
	}

	/**
	 * UNDO.
	 */
	public void rollBack() {
		((DrawingEngineImp) mEngine).setLastActionToUndo(false);
		Memento lastMemento = mCaretaker.rollback();
		if (lastMemento != null) {
			if (lastMemento instanceof MultiMemento) {
				for (Memento memento :
					((MultiMemento) lastMemento).getMementos()) {
					factory.execute(mEngine,
							mShapesList, memento.getAction());
				}
			} else {
				factory.execute(mEngine,
						mShapesList, lastMemento.getAction());
			}
		}
		((DrawingEngineImp) mEngine).setLastActionToUndo(true);
	}

	/**
	 * REDO.
	 */
	public void stepForward() {
		Memento lastMemento = mCaretaker.stepForward();
		if (lastMemento != null) {
			if (lastMemento instanceof MultiMemento) {
				for (Memento memento : 
					((MultiMemento) lastMemento).getMementos()) {
					factory.execute(mEngine,
							mShapesList, memento.getAction());
				}
			} else {
				factory.execute(mEngine,
						mShapesList, lastMemento.getAction());
			}
		}
	}

	/**
	 * Custom version of addShape method.
	 * 
	 * @param shape
	 *            to be added.
	 */
	public void addFreeShape(FreeShape shape) {
		List<Memento> mementos = 
				((MultiMemento) shape.getMemento()).getMementos();
		mCaretaker.addMemento(shape.getMemento());
		for (Memento memento : mementos) {
			factory.execute(mEngine,
					mShapesList, memento.getAction());
		}
	}
}
