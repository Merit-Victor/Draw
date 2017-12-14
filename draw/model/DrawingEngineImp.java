package draw.model;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import eg.edu.alexu.csd.oop.draw.memento.*;
import eg.edu.alexu.csd.oop.draw.saveAndLoad.Json;
import eg.edu.alexu.csd.oop.draw.saveAndLoad.Xml;
import eg.edu.alexu.csd.oop.draw.shapes.*;
import eg.edu.alexu.csd.oop.draw.DrawingEngine;
import eg.edu.alexu.csd.oop.draw.Shape;

/**
 * @author Merit Victor 
 * Implementation of the drawing engine interface.
 */

public class DrawingEngineImp implements DrawingEngine {

	/**
	 * 
	 */
	private List<Shape> shapesList;

	/**
	 * 
	 */
	private static List<Class<? extends Shape>> classesList;

	/**
	 * 
	 */
	private Caretaker mCaretaker;

	/**
	 * 
	 */
	private RemoteController mRemoteController;

	/**
	 * 
	 */
	private boolean lastActionIsUndo;

	/**
	 * 
	 */
	private Json json;

	/**
	 * 
	 */
	private Xml xml;

	/**
	 * 
	 */
	private static final int ONE = 1;

	/**
	 * 
	 */
	public DrawingEngineImp() {
		json = new Json(this);
		xml = new Xml(this);
		shapesList = new ArrayList<>();
		DrawingEngineImp.classesList 
		= new ArrayList<Class<? extends Shape>>();
		classesList.add(Ellipse.class);
		classesList.add(Circle.class);
		classesList.add(LineSegment.class);
		classesList.add(Rectangle.class);
		classesList.add(Square.class);
		classesList.add(Triangle.class);
		this.mCaretaker = new Caretaker();
		this.mRemoteController 
		= new RemoteController(this, mCaretaker, shapesList);
	}

	@Override
	public void refresh(Graphics canvas) {
		for (Shape shape : shapesList) {
			shape.draw(canvas);
		}
	}

	@Override
	public void addShape(Shape shape) {
		if (shape == null) {
			throw new NullPointerException();
		} else {
			mRemoteController.addShape(shape);
		}
	}

	@Override
	public void removeShape(Shape shape) {
		if (shape == null) {
			throw new NullPointerException();
		} else {
			mRemoteController.removeShape(shape);
		}
	}

	@Override
	public void updateShape(Shape oldShape, Shape newShape) {
		if (oldShape == null || newShape == null) {
			throw new NullPointerException();
		} else {
			mRemoteController.updateShape(oldShape, newShape);
		}
	}

	@Override
	public Shape[] getShapes() {
		Shape[] shapes = new Shape[shapesList.size()];
		Iterator<Shape> itr = shapesList.iterator();
		int index = 0;
		while (itr.hasNext()) {
			shapes[index] = itr.next();
			index++;
		}
		return shapes;
	}

	@Override
	public List<Class<? extends Shape>> getSupportedShapes() {
		return classesList;
	}

	/**
	 * @param plugin
	 *            new type of shapes.
	 */
	public void installPlugin(Class<? extends Shape> plugin) {
		classesList.add(plugin);

	}

	@Override
	public void undo() {
		mRemoteController.rollBack();
	}

	@Override
	public void redo() {
		lastActionIsUndo = false;
		mRemoteController.stepForward();
	}

	@Override
	public void save(String path) {
		if (path.charAt(path.length() - ONE) == 'n' 
				|| path.charAt(path.length() - ONE) == 'N') {
			json.save(path);
		} else if (path.charAt(path.length() - ONE) == 'l' 
				|| path.charAt(path.length() - ONE) == 'L') {
			xml.save(path);
		}
	}

	@Override
	public void load(String path) {
		if (path != null) {
			shapesList.clear();
			if (path.charAt(path.length() - ONE) == 'n' 
					|| path.charAt(path.length() - ONE) == 'N') {
				json.load(path);
			} else if (path.charAt(path.length() - ONE) == 'l'
					|| path.charAt(path.length() - ONE) == 'L') {
				xml.load(path);
			}
		}
	}

	/**
	 * Clears the shapeslist.
	 */
	public void clear() {
		shapesList.clear();
	}

	/**
	 * Sets it to the given value.
	 * 
	 * @param bool value.
	 */
	public void setLastActionToUndo(boolean bool) {
		this.lastActionIsUndo = bool;
	}

	/**
	 * The getter function.
	 * 
	 * @return boolean.
	 */
	public boolean getLastActionIsUndo() {
		return this.lastActionIsUndo;
	}

	/**
	 * Clears Undo stack.
	 */
	public void clearUndoStack() {
		mCaretaker.clearUndoStack();
	}

	/**
	 * Clears redo stack.
	 */
	public void clearRedoStack() {
		mCaretaker.clearRedoStack();
	}

	/**
	 * Custom version of addShape method.
	 * 
	 * @param shape to be added.
	 */
	public void addFreeShape(FreeShape shape) {
		mRemoteController.addFreeShape(shape);
	}

}
