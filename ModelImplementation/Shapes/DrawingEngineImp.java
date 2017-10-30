package Shapes;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import Memento.*;
import eg.edu.alexu.csd.oop.draw.DrawingEngine;
import eg.edu.alexu.csd.oop.draw.Shape;

public class DrawingEngineImp implements DrawingEngine {
	
	/**
	 * 
	 */
	private List<Shape> shapesList;

	private Caretaker mCaretaker;

	private RemoteController mRemoteController;

	/**
	 * 
	 */
	public DrawingEngineImp() {
		this.shapesList = new ArrayList<>();
		this.mCaretaker = new Caretaker();
		this.mRemoteController = new RemoteController(this, mCaretaker);
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
			mCaretaker.addMemento(new Memento(new Action(State.added, shape)));
			shapesList.add(shape);
		}
	}

	@Override
	public void removeShape(Shape shape) {
		if (shape == null) {
			throw new NullPointerException();
		} else {
			mCaretaker.addMemento(new Memento(new Action(State.removed, shape)));
			shapesList.remove(shape);
		}
	}

	@Override
	public void updateShape(Shape oldShape, Shape newShape) {
		if (oldShape == null || newShape == null) {
			throw new NullPointerException();
		} else {
			int oldIndex;
			oldIndex = shapesList.indexOf(oldShape);
			mCaretaker.addMemento(new Memento(new Action(State.updated, oldShape, newShape)));
			shapesList.remove(oldIndex);
			shapesList.add(oldIndex, newShape);
		}
	}

	@Override
	public Shape[] getShapes() {
        Shape[] shapes = new Shape[this.shapesList.size()];
        Iterator itr = shapesList.iterator();
        int index = 0;
        while (itr.hasNext()) {
            Shape element = (Shape) itr.next();
            shapes[index] = element;
            index++;
        }
        return shapes;
	}

	@Override
	public List<Class<? extends Shape>> getSupportedShapes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void undo() {
		mRemoteController.rollBack();

	}

	@Override
	public void redo() {
		mRemoteController.stepForward();
	}

	@Override
	public void save(String path) {
		// TODO Auto-generated method stub

	}

	@Override
	public void load(String path) {
		// TODO Auto-generated method stub

	}
}
