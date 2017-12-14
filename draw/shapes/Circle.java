package eg.edu.alexu.csd.oop.draw.shapes;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import eg.edu.alexu.csd.oop.draw.cs68.ShapeImp;

/**
 * @author Merit Victor
 *
 */
public class Circle extends ShapeImp {

	/**
	 * 
	 */
	private Map<String, Double> prop;

	/**
	 * 
	 */
	private Point center;

	/**
	 * 
	 */
	private Double radius;

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
	private static final int COMPLETE_CIRCLE = 360;

	/**
	 * Constructor.
	 */
	public Circle() {
		prop = new HashMap<>();
		Point position = new Point();
		position.x = ZERO;
		position.y = ZERO;
		this.setPosition(position);
		prop.put("Radius", (double) ZERO);
		this.setProperties(prop);
	}

	@Override
	public void draw(Graphics canvas) {
		if (canvas != null) {
			((Graphics2D) canvas).setColor(this.getFillColor());
			canvas.fillArc(getPosition().x, getPosition().y,
					getProperties().get("Radius").intValue() * TWO,
					getProperties().get("Radius").intValue() * TWO,
					ZERO, COMPLETE_CIRCLE);
			((Graphics2D) canvas).setStroke(new BasicStroke(TWO));
			((Graphics2D) canvas).setColor(this.getColor());
			canvas.drawArc(getPosition().x, getPosition().y,
					getProperties().get("Radius").intValue() * TWO,
					getProperties().get("Radius").intValue() * TWO,
					ZERO, COMPLETE_CIRCLE);
		}
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Circle circle = new Circle();
		circle.setColor(this.getColor());
		circle.setFillColor(this.getFillColor());
		circle.setPosition(new Point(this.getPosition().x,
				this.getPosition().y));
		Map<String, Double> newprop = new HashMap<>();
		newprop.put("Radius", this.getProperties().get("Radius"));
		circle.setProperties(newprop);
		return circle;
	}

	/**
	 * 
	 */
	private void getCenter() {
		radius = this.getProperties().get("Radius");
		int x = this.getPosition().x + radius.intValue();
		int y = this.getPosition().y + radius.intValue();
		center = new Point(x, y);
	}

	@Override
	public boolean containsPoint(Point p) {
		getCenter();
		return (Math.pow(p.x - center.x, TWO) 
				+ Math.pow(p.y - center.y, TWO))
				<= (Math.pow(radius, TWO));
	}
}
