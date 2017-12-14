package eg.edu.alexu.csd.oop.draw.controller;

import java.util.ArrayList;
import java.util.List;

import eg.edu.alexu.csd.oop.draw.DrawingEngine;
import eg.edu.alexu.csd.oop.draw.Shape;
import eg.edu.alexu.csd.oop.draw.cs68.DrawingEngineImp;

/**
 * @author Merit Victor Shape Factory
 */
public class ShapeFactory {

	/**
	 * 
	 */
	private DrawingEngine drawEngine = 
			new DrawingEngineImp();
	
	/**
	 * 
	 */
	private List<Class<? extends Shape>> supportedShapes =
			new ArrayList<Class<? extends Shape>>();

	/**
	 * Returns the correct type of shape.
	 * 
	 * @param shapeType
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public Shape getShape(String shapeType) throws
	InstantiationException, IllegalAccessException {
		supportedShapes = drawEngine.getSupportedShapes();
		for (int i = 0; i < supportedShapes.size(); i++) {
			if (supportedShapes.get(i).getSimpleName()
					.equalsIgnoreCase(shapeType)) {
				Class<? extends Shape> shapeClass = 
						supportedShapes.get(i);
				return shapeClass.newInstance();
			}
		}
		return null;
	}
}
