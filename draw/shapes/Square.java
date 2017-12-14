package eg.edu.alexu.csd.oop.draw.shapes;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import eg.edu.alexu.csd.oop.draw.cs68.ShapeImp;

/**
 * @author Merit Victor
 *
 */
public class Square extends ShapeImp {

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
	 * Constructor.
	 */
	public Square() {
		prop = new HashMap<>();
		prop.put("Length", (double) ZERO);
		this.setProperties(prop);
	}

	@Override
	public void draw(Graphics canvas) {
		if (canvas != null) {
			((Graphics2D) canvas).setColor(this.getFillColor());

			canvas.fillRect(this.getPosition().x, 
					this.getPosition().y,
					(this.getProperties().get("Length")).intValue(),
					(this.getProperties().get("Length")).intValue());
			((Graphics2D) canvas).setStroke(new BasicStroke(TWO));
			((Graphics2D) canvas).setColor(this.getColor());
			canvas.drawRect(this.getPosition().x, this.getPosition().y,
					(this.getProperties().get("Length")).intValue(),
					(this.getProperties().get("Length")).intValue());
		}
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Square square = new Square();
		square.setColor(this.getColor());
		square.setFillColor(this.getFillColor());
		square.setPosition(this.getPosition());
		Map<String, Double> newprop = new HashMap<>();
		newprop.put("Length", this.getProperties().get("Length"));
		square.setProperties(newprop);
		return square;
	}

	@Override
	public boolean containsPoint(Point p) {
		return (p.x >= getPosition().x
				&& p.x <= (getPosition().x 
						+ getProperties().get("Length")))
				&& (p.y <= (getPosition().y 
						+ getProperties().get("Length"))
				&& p.y >= getPosition().y);
	}
}
