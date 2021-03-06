package eg.edu.alexu.csd.oop.draw.command;

import java.util.List;

import eg.edu.alexu.csd.oop.draw.Shape;
import eg.edu.alexu.csd.oop.draw.cs68.DrawingEngineImp;

/**
 * @author Merit Victor Add command class.
 */
public class AddCommand {

	/**
	 * 
	 */
	private DrawingEngineImp myEngine;

	/**
	 * 
	 */
	private List<Shape> myShapesList;

	/**
	 * Constructor.
	 * 
	 * @param engine
	 * @param shapesList
	 */
	public AddCommand(DrawingEngineImp engine, List<Shape> shapesList) {
		this.myEngine = engine;
		this.myShapesList = shapesList;
	}

	/**
	 * Executes the command.
	 * 
	 * @param newShape
	 */
	public void execute(Shape newShape) {
		if (myEngine.getLastActionIsUndo()) {
			myEngine.clearRedoStack();
			myEngine.setLastActionToUndo(false);
		}
		myShapesList.add(newShape);
	}

}
