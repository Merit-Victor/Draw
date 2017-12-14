package eg.edu.alexu.csd.oop.draw.command;

import eg.edu.alexu.csd.oop.draw.memento.*;

import java.util.List;

import eg.edu.alexu.csd.oop.draw.DrawingEngine;
import eg.edu.alexu.csd.oop.draw.Shape;
import eg.edu.alexu.csd.oop.draw.cs68.DrawingEngineImp;

/**
 * @author Merit Victor Command factory
 */
public class CommandFactory {

	/**
	 * Searches the probable type of command.
	 * 
	 * @param engine 
	 * @param shapesList
	 * @param action
	 */
	public void execute(DrawingEngine engine,
			List<Shape> shapesList, Action action) {
		State state = action.getState();
		if (state.equals(State.added)) {
			new AddCommand((DrawingEngineImp) engine,
					shapesList).execute(action.getNewShape());
		} else if (state.equals(State.removed)) {
			new RemoveCommand((DrawingEngineImp) engine,
					shapesList).execute(action.getNewShape());
		} else if (state.equals(State.updated)) {
			new UpdateCommand((DrawingEngineImp) engine,
					shapesList).execute(action.getOldShape(),
					action.getNewShape());
		}
	}
}
