package eg.edu.alexu.csd.oop.draw.shapes;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import eg.edu.alexu.csd.oop.draw.cs68.ShapeImp;

/**
 * @author Merit Victor
 *
 */
public class Triangle extends ShapeImp {

	/**
	 * 
	 */
	private Map<String, Double> prop;

	/**
	 * 
	 */
	private Point p1 = new Point();
	
	/**
	 * 
	 */
	private Point p2 = new Point();
	
	/**
	 * 
	 */
	private static final int ZERO = 0;

	/**
	 * 
	 */
	private static final int TWO = 2;
	
	/**
	 * 
	 */
	private static final int THREE = 3;
	
	/**
	 * Constructor. 
	 */
	public Triangle() {
		prop = new HashMap<>();
		prop.put("height", (double) ZERO);
		prop.put("halfWidth", (double) ZERO);
		this.setProperties(prop);
	}

	@Override
	public void draw(Graphics canvas) {
		if (canvas != null) {
			getPoints();
			int[] xCoordinates = { p1.x, this.getPosition().x, p2.x};
			int[] yCoordinates = { p1.y, this.getPosition().y, p2.y};
			((Graphics2D) canvas).setColor(this.getFillColor());

			canvas.fillPolygon(xCoordinates, yCoordinates, THREE);
			((Graphics2D) canvas).setStroke(new BasicStroke(TWO));
			((Graphics2D) canvas).setColor(this.getColor());
			canvas.drawPolygon(xCoordinates, yCoordinates, THREE);
		}
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Triangle triangle = new Triangle();
		triangle.setColor(this.getColor());
		triangle.setFillColor(this.getFillColor());
		triangle.setPosition(this.getPosition());
		Map<String, Double> newprop = new HashMap<>();
		newprop.put("height", this.getProperties().get("height"));
		newprop.put("halfWidth", this.getProperties().get("halfWidth"));
		triangle.setProperties(newprop);
		return triangle;
	}

	@Override
	public boolean containsPoint(Point p) {
		getPoints();
		boolean b1, b2, b3;

		b1 = sign(p, getPosition(), p1) < (double) ZERO;
		b2 = sign(p, p1, p2) < (double) ZERO;
		b3 = sign(p, p2, getPosition()) < (double) ZERO;

		return ((b1 == b2) && (b2 == b3));
	}

	/**
	 * Method.
	 * 
	 * @param first 
	 * 				point.
	 * @param second
	 * 				 point.
	 * @param third 
	 * 				point.
	 * @return double.
	 */
	private double sign(Point first, Point second, Point third) {
		return (first.x - third.x)
				* (second.y - third.y) 
				- (second.x - third.x) 
				* (first.y - third.y);
	}
	
	/**
	 * Method.
	 */
	private void getPoints() {
		p1.x = this.getPosition().x 
				- this.getProperties()
				.get("halfWidth").intValue();	
		p1.y = this.getPosition().y 
				+ this.getProperties()
				.get("height").intValue();
		p2.x = this.getPosition().x 
				+ this.getProperties()
				.get("halfWidth").intValue();
		p2.y = this.getPosition().y 
				+ this.getProperties()
				.get("height").intValue(); 
	}
}
