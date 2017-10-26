package Shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.Map;

import eg.edu.alexu.csd.oop.draw.Shape;

public abstract class ShapeImp implements Shape, Cloneable {

	private Point mPosition;
	private Map<String,Double> mProperties;
	private Color mOutlineColor;
	private Color mFillColor;
	private Graphics2D canvas;

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
		if (this.mOutlineColor != null) {
			return this.mOutlineColor;
		} else {
			return null;
		}
	}

	@Override
	public void setFillColor(Color color) {
		this.mFillColor=color;
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
	public Object clone() throws CloneNotSupportedException {
		return this.clone();
	}
}
