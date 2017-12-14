package eg.edu.alexu.csd.oop.draw.shapes;

import java.awt.*;
import java.awt.geom.Line2D;
import java.util.HashMap;
import java.util.Map;

import eg.edu.alexu.csd.oop.draw.cs68.ShapeImp;

/**
 * @author Merit Victor
 *
 */
public class LineSegment extends ShapeImp {

	/**
	 * 
	 */
	private Map<String, Double> prop;

	/**
	 * 
	 */
	protected Point startPoint;

	/**
	 * 
	 */
	protected Point endPoint;

	/**
	 * 
	 */
	protected Double slope;

	/**
	 * 
	 */
	protected Double xDiff;

	/**
	 * 
	 */
	protected Double yDiff;

	/**
	 * 
	 */
	private static final int ZERO = 0;

	/**
	 * 
	 */
	private static final int THREE = 3;
	
	/**
	 * Constructor.
	 */
	public LineSegment() {
		prop = new HashMap<>();
		prop.put("Endpoint-x", (double) ZERO);
		prop.put("Endpoint-y", (double) ZERO);
		this.setProperties(prop);
	}

	@Override
	public void draw(Graphics canvas) {
		if (canvas != null) {
			endPoint = new Point(getProperties()
					.get("Endpoint-x").intValue(),
					getProperties().get("Endpoint-y").intValue());
			startPoint = new Point(
					this.getPosition().x, this.getPosition().y);
			findSlope();
			((Graphics2D) canvas).setColor(this.getColor());
			((Graphics2D) canvas).setStroke(new BasicStroke(THREE));
			canvas.drawLine(startPoint.x, startPoint.y,
					endPoint.x, endPoint.y);
		}
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		LineSegment line = new LineSegment();
		line.setColor(this.getColor());
		line.setFillColor(this.getColor());
		Point newPoint = new Point(
				this.getPosition().x, this.getPosition().y);
		line.setPosition(newPoint);
		Map<String, Double> newprop = new HashMap<>();
		newprop.put("Endpoint-x",
				this.getProperties().get("Endpoint-x"));
		newprop.put("Endpoint-y",
				this.getProperties().get("Endpoint-y"));
		line.setProperties(newprop);
		return line;
	}

	@Override
	public boolean containsPoint(Point p) {
		Double result = Line2D.ptSegDist(startPoint.x,
				startPoint.y, endPoint.x, endPoint.y, p.x, p.y);
		if (result.intValue() <= 5) {
			return true;
		} else {
			return false;
		}
	}

	/** 
	 * remember slope = (y2 - y1)/(x2- x1)
	 */
	public void findSlope() {
		endPoint = new Point(getProperties().get("Endpoint-x")
				.intValue(),
				getProperties().get("Endpoint-y").intValue());
		startPoint = new Point(this.getPosition().x,
				this.getPosition().y);
		this.xDiff = (double) (endPoint.x - startPoint.x);
		this.yDiff = (double) (endPoint.y - startPoint.y);
		this.slope = yDiff / xDiff;
	}

	/**
	 * finds the second point on the line
	 * using the first one. x2 - x1 = xDiff
	 * y2 - y1 = yDiff for the same slope ;)
	 * 
	 * @param firstPoint
	 */
	public void findSecondPoint(Point firstPoint) {
		findSlope();
		Point secondPoint = new Point(xDiff.intValue() +
				firstPoint.x, yDiff.intValue() +
				firstPoint.y);
		if (sameSlope(firstPoint, secondPoint)) {
			Map<String, Double> newprop = new HashMap<>();
			newprop.put("Endpoint-x", (double) secondPoint.x);
			newprop.put("Endpoint-y", (double) secondPoint.y);
			this.setProperties(newprop);
			this.setPosition(new Point(
					firstPoint.x, firstPoint.y));
		}
	}

	/**
	 * Checks the slope.
	 * 
	 * @param p1
	 *            point.
	 * @param p2
	 *            point.
	 * @return boolean.
	 */
	public boolean sameSlope(Point p1, Point p2) {
		Double xdiff = (double) p2.x - p1.x;
		Double ydiff = (double) p2.y - p1.y;
		if (Double.compare((ydiff / xdiff),
				this.slope) == ZERO) {
			return true;
		} else {
			return false;
		}
	}
	
}
