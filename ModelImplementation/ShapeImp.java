package eg.edu.alexu.csd.oop.draw.cs68;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Map;

import eg.edu.alexu.csd.oop.draw.Shape;

/**
 * @author Merit Victor
 *
 */
public class ShapeImp implements Shape {

	/**
	 * 
	 */
	private Point mPosition;

	/**
	 * 
	 */
	private Map<String, Double> mProperties;
	
	/**
	 * 
	 */
	private Color mOutlineColor;
	
	/**
	 * 
	 */
	private Color mFillColor;
	
	/**
	 * 
	 */
	private Graphics mCanvas;
	
	@Override
	public void setPosition(Point position) {
		this.mPosition = position;
	}

	@Override
	public Point getPosition() {
		return this.mPosition;
	}

	@Override
	public void setProperties(Map<String, Double> properties) {
		this.mProperties = properties;
	}

	@Override
	public Map<String, Double> getProperties() {
		if (this.mProperties.containsValue(null) 
				|| this.mProperties.containsKey(null)) {
			return null;
		}
		return this.mProperties;
	}

	@Override
	public void setColor(Color color) {
		this.mOutlineColor = color;
	}

	@Override
	public Color getColor() {
		return this.mOutlineColor;
	}

	@Override
	public void setFillColor(Color color) {
		this.mFillColor = color;
	}

	@Override
	public Color getFillColor() {
		return this.mFillColor;
	}

	@Override
	public void draw(Graphics canvas) {
		this.mCanvas = canvas;
	}
	
	@Override 
	public Object clone() throws CloneNotSupportedException {
		return null;
	}

}
