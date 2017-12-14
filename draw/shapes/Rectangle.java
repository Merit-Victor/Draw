package eg.edu.alexu.csd.oop.draw.shapes;

import java.awt.*;

import java.util.HashMap;
import java.util.Map;

import eg.edu.alexu.csd.oop.draw.cs68.ShapeImp;

/**
 * @author Merit Victor
 *
 */
public class Rectangle extends ShapeImp {

	/**
	 * 
	 */
	private Map<String, Double> prop;

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
	public Rectangle() {
		prop = new HashMap<>();
		prop.put("Width", (double) ZERO);
		prop.put("Height", (double) ZERO);
		this.setProperties(prop);
	}

	@Override
	public void draw(Graphics canvas) {
		if (canvas != null) {
			((Graphics2D) canvas).setColor(this.getFillColor());

			canvas.fillRect(this.getPosition().x,
					this.getPosition().y, 
					(this.getProperties().get("Width")).intValue(),
					(this.getProperties().get("Height")).intValue());
			((Graphics2D) canvas).setStroke(new BasicStroke(TWO));
			((Graphics2D) canvas).setColor(this.getColor());
			canvas.drawRect(this.getPosition().x, this.getPosition().y, 
					(this.getProperties().get("Width")).intValue(),
					(this.getProperties().get("Height")).intValue());
		}
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Rectangle rectangle = new Rectangle();
		rectangle.setColor(this.getColor());
		rectangle.setFillColor(this.getFillColor());
		rectangle.setPosition(this.getPosition());
		Map<String, Double> newprop = new HashMap<>();
		newprop.put("Width", this.getProperties().get("Width"));
		newprop.put("Height", this.getProperties().get("Height"));
		rectangle.setProperties(newprop);
		return rectangle;
	}

	@Override
	public boolean containsPoint(Point p) {
		return (p.x >= getPosition().x && p.x
				<= (getPosition().x + getProperties().get("Width")))
				&& (p.y <= (getPosition().y 
						+ getProperties().get("Height")) 
				&& p.y >= getPosition().y);
	}

	/**
	 * Custom version.
	 * 
	 * @param canvas
	 *            to draw on.
	 * @param dashed
	 *            given stroke.
	 */
	public void drawDashed(Graphics canvas, BasicStroke dashed) {
		if (canvas != null) {
			((Graphics2D) canvas).setRenderingHint(
					RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			((Graphics2D) canvas).setPaint(Color.GRAY);
			((Graphics2D) canvas).setStroke(dashed);
			((Graphics2D) canvas).drawRect(this.getPosition().x,
					this.getPosition().y,
					(this.getProperties().get("Width")).intValue(), 
					(this.getProperties().get("Height")).intValue());
		}
	}
}
