package Shapes;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import eg.edu.alexu.csd.oop.draw.DrawingEngine;
import eg.edu.alexu.csd.oop.draw.Shape;

public class DrawingEngineImp implements DrawingEngine {
	
	/**
	 * 
	 */
	private List<Shape> shapesList;

	/**
	 * 
	 */
	public DrawingEngineImp() {
		this.shapesList = new ArrayList<Shape>();
	}

	@Override
	public void refresh(Graphics canvas) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addShape(Shape shape) {
		if (shape == null) {
			throw new NullPointerException();
		} else {
			shapesList.add(shape);
		}
	}

	@Override
	public void removeShape(Shape shape) {
		shapesList.remove(shape);
	}

	@Override
	public void updateShape(Shape oldShape, Shape newShape) {
		if (oldShape == null || newShape == null) {
			throw new NullPointerException();
		} else {
			int oldIndex;
			oldIndex = shapesList.indexOf(oldShape);
			shapesList.remove(oldIndex);
			shapesList.add(oldIndex, newShape);
		}
	}

	@Override
	public Shape[] getShapes() {
		Shape[] shapes = (Shape[])shapesList.toArray();
		return shapes;
	}

	@Override
	public List<Class<? extends Shape>> getSupportedShapes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub

	}

	@Override
	public void redo() {
		// TODO Auto-generated method stub

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
