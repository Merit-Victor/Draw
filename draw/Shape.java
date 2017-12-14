package eg.edu.alexu.csd.oop.draw;

/**
 * @author Merit Victor Shape interface.
 */
public interface Shape {

	/**
	 * Sets the called shape's position to the given point.
	 * 
	 * @param position
	 *            point.
	 */
	public void setPosition(java.awt.Point position);

	/**
	 * @return the shape's position in a point.
	 */
	public java.awt.Point getPosition();

	/* update shape specific properties (e.g., radius) */
	public void setProperties(java.util.Map<String, Double> properties);

	/**
	 * @return the shape's properties.
	 */
	public java.util.Map<String, Double> getProperties();

	/**
	 * Sets the shape's outline color to the given color.
	 * 
	 * @param color
	 */
	public void setColor(java.awt.Color color);

	/**
	 * @return the shape's outline color.
	 */
	public java.awt.Color getColor();

	/**
	 * Sets the shape's fill color to the given color.
	 * 
	 * @param color
	 */
	public void setFillColor(java.awt.Color color);

	/**
	 * @return the shape's fill color.
	 */
	public java.awt.Color getFillColor();

	/* redraw the shape on the canvas */
	public void draw(java.awt.Graphics canvas);

	/* create a deep clone of the shape */
	public Object clone() throws CloneNotSupportedException;

}
