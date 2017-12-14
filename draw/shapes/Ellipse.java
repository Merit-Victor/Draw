package eg.edu.alexu.csd.oop.draw.shapes;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import eg.edu.alexu.csd.oop.draw.cs68.ShapeImp;

/**
 * @author Merit Victor
 *
 */
public class Ellipse extends ShapeImp {

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
	private Double semiMinor;

	/**
	 * 
	 */
	private Double semiMajor;
	
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
	 * 
	 */
	private static final int ONE = 1;
	
	/**
	 * Constructor.
	 */
	public Ellipse() {
		prop = new HashMap<>();
		prop.put("Semi-majoraxis", (double) ZERO);
		prop.put("Semi-minoraxis", (double) ZERO);
		this.setPosition(new Point(ZERO, ZERO));
		this.setProperties(prop);
	}

	@Override
	public void draw(Graphics canvas) {
		if (canvas != null) {
			((Graphics2D) canvas).setColor(this.getFillColor());
			canvas.fillArc(getPosition().x, getPosition().y,
					getProperties().get("Semi-majoraxis").intValue() * TWO,
					getProperties().get("Semi-minoraxis").intValue() * TWO,
					ZERO, COMPLETE_CIRCLE);
			((Graphics2D) canvas).setStroke(new BasicStroke(TWO));
			((Graphics2D) canvas).setColor(this.getColor());
			canvas.drawArc(getPosition().x, getPosition().y,
					getProperties().get("Semi-majoraxis").intValue() * TWO,
					getProperties().get("Semi-minoraxis").intValue() * TWO,
					ZERO, COMPLETE_CIRCLE);
		}
	}

	/**
	 * Getter.
	 */
	private void getCenter() {
		semiMajor = this.getProperties().get("Semi-majoraxis");
		semiMinor = this.getProperties().get("Semi-minoraxis");
		int x = this.getPosition().x + semiMajor.intValue();
		int y = this.getPosition().y + semiMinor.intValue();
		center = new Point(x, y);
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Ellipse ellipse = new Ellipse();
		ellipse.setColor(this.getColor());
		ellipse.setFillColor(this.getFillColor());
		ellipse.setPosition(this.getPosition());
		Map<String, Double> newprop = new HashMap<>();
		newprop.put("Semi-majoraxis", this.getProperties().get("Semi-majoraxis"));
		newprop.put("Semi-minoraxis", this.getProperties().get("Semi-minoraxis"));
		ellipse.setProperties(newprop);
		return ellipse;
	}

	@Override
	public boolean containsPoint(Point p) {
		getCenter();
		return (Math.pow(((p.x - center.x) / semiMajor), TWO)
				+ Math.pow(((p.y - center.y) / semiMinor), TWO))
				<= ONE;
	}
}
