package draw.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

import eg.edu.alexu.csd.oop.draw.Shape;

/**
 * @author Merit Victor 
 * Class that implements the basic functionalities of a
 *         shape.
 */
public abstract class ShapeImp implements Shape {

	/**
	 * 
	 */
	private Point mPosition;

	/**
	 * 
	 */
	private Map<String, Double> mProperties = new HashMap<>();

	/**
	 * 
	 */
	private Color mOutlineColor = Color.BLACK;

	/**
	 * 
	 */
	private Color mFillColor = Color.WHITE;

	@Override
	public void setPosition(Point position) {
		this.mPosition = position;
	}

	@Override
	public Point getPosition() {
		if (this.mPosition != null) {
			return this.mPosition;
		} else {
			return null;
		}
	}

	@Override
	public void setProperties(Map<String, Double> properties) {
		if (properties.equals(null)) {
			throw new NullPointerException();
		} else {
			this.mProperties = properties;
		}
	}

	@Override
	public Map<String, Double> getProperties() {
		return this.mProperties;
	}

	@Override
	public void setColor(Color color) {
		this.mOutlineColor = color;
	}

	@Override
	public Color getColor() {
		if (this.mOutlineColor != null) {
			return this.mOutlineColor;
		} else {
			return null;
		}
	}

	@Override
	public void setFillColor(Color color) {
		this.mFillColor = color;
	}

	@Override
	public Color getFillColor() {
		if (this.mFillColor != null) {
			return this.mFillColor;
		} else {
			return null;
		}
	}

	@Override
	public abstract void draw(Graphics canvas);

	@Override
	public abstract Object clone() throws CloneNotSupportedException;

	/**
	 * @param p
	 *            point to be checked
	 * @return boolean.
	 */
	public abstract boolean containsPoint(Point p);
}
