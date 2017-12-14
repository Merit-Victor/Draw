package eg.edu.alexu.csd.oop.draw.command;

import java.util.List;

import eg.edu.alexu.csd.oop.draw.Shape;
import eg.edu.alexu.csd.oop.draw.cs68.DrawingEngineImp;

/**
 * @author Merit Victor Update Command.
 */
public class UpdateCommand {

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
	public UpdateCommand(DrawingEngineImp engine,
			List<Shape> shapesList) {
		this.myEngine = engine;
		this.myShapesList = shapesList;
	}

	/**
	 * Executes the command.
	 * 
	 * @param oldShape
	 * @param newShape
	 */
	public void execute(Shape oldShape, Shape newShape) {
		if (myEngine.getLastActionIsUndo()) {
			myEngine.clearRedoStack();
			myEngine.setLastActionToUndo(false);
		}
		int oldIndex;
		oldIndex = myShapesList.indexOf(oldShape);
		myShapesList.remove(oldIndex);
		myShapesList.add(oldIndex, newShape);
	}
}
