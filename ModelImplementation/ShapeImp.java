package eg.edu.alexu.csd.oop.draw.cs26;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;





import eg.edu.alexu.csd.oop.draw.Shape;

public class ShapeImp implements Shape {
	
	private Point mPosition;
	private Map<String,Double> mProperties;
	private Color mOutlineColor;
	private Color mFillColor;
	private Graphics2D canvas;
	
	public ShapeImp() {
		mProperties= new HashMap<String,Double>();
	}

	  
	@Override
	public void setPosition(Point position) {
		// TODO Auto-generated method stub
		this.mPosition=position;
	}

	@Override
	public Point getPosition() {
		// TODO Auto-generated method stub
		return this.mPosition;
	}

	@Override
	public void setProperties(Map<String, Double> properties) {
	
		this.mProperties=properties;
		
	}

	@Override
	public Map<String, Double> getProperties() {	
		return this.mProperties;
	}

	@Override
	public void setColor(Color color) {
		this.mOutlineColor=color;
	}

	@Override
	public Color getColor() {
		return this.mOutlineColor;
	}

	@Override
	public void setFillColor(Color color) {
		this.mFillColor=color;
	}

	@Override
	public Color getFillColor() {
		return this.mFillColor;
	}

	@Override
	public void draw(Graphics canvas) {
		this.canvas=(Graphics2D) canvas;
	}
	@Override
	public Object clone() throws CloneNotSupportedException{
		return null;
		
	}
	
}
