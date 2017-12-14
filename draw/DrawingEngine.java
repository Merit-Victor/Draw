package eg.edu.alexu.csd.oop.draw;

/**
 * @author Merit Victor The drawing engine interface.
 */
public interface DrawingEngine {

	/* redraw all shapes on the canvas */
	public void refresh(java.awt.Graphics canvas);

	/**
	 * @param shape
	 *            to be added.
	 */
	public void addShape(Shape shape);

	/**
	 * @param shape
	 *            to be removed.
	 */
	public void removeShape(Shape shape);

	/**
	 * @param oldShape
	 *            to be replaced.
	 * @param newShape
	 *            to replace the oldshape with.
	 */
	public void updateShape(Shape oldShape, Shape newShape);

	/* return the created shapes objects */
	public Shape[] getShapes();

	/*
	 * return the classes (types) of supported 
	 * shapes that can be dynamically
	 * loaded at runtime (see Part 3)
	 */
	public java.util.List<Class<? extends Shape>> getSupportedShapes();

	/*
	 * limited to 20 steps. You consider these actions 
	 * in undo & redo: addShape,
	 * removeShape, updateShape
	 */
	public void undo();

	/**
	 * Redo method.
	 */
	public void redo();

	/*
	 * use the file extension to determine the type,
	 *  or throw runtime exception
	 * when unexpected extension
	 */
	public void save(String path);

	/**
	 * @param path
	 *            of the file to be loaded.
	 */
	public void load(String path);

}
