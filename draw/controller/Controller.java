package eg.edu.alexu.csd.oop.draw.controller;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Point;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import javax.swing.JColorChooser;

import eg.edu.alexu.csd.oop.draw.Shape;
import eg.edu.alexu.csd.oop.draw.cs68.DrawingEngineImp;
import eg.edu.alexu.csd.oop.draw.cs68.ShapeImp;
import eg.edu.alexu.csd.oop.draw.shapes.FreeShape;
import eg.edu.alexu.csd.oop.draw.shapes.LineSegment;
import eg.edu.alexu.csd.oop.draw.shapes.Rectangle;
import eg.edu.alexu.csd.oop.draw.view.PaintGui;

/**
 * @author Merit Victor
 * The controller of the GUI.
 */
public class Controller {
	
	/**
	 * 
	 */
	public String mode = "draw";
	
	/**
	 * 
	 */
	final static float dash1[] = {9};
	
	/**
	 * 
	 */
	final static BasicStroke dashed = new BasicStroke(3,
		      BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, dash1, 0);
	
	/**
	 * 
	 */
	private Shape shape;
	
	/**
	 * 
	 */
	private ShapeFactory shapeFactory;
	
	/**
	 * 
	 */
	private Map<String, Double> properties;
	
	/**
	 * 
	 */
	private String shapeType;

	/**
	 * 
	 */
	private Point mPosition;
	
	/**
	 * 
	 */
	private Color outlineColor;
	
	/**
	 * 
	 */
	private Color fillColor;
	
	/**
	 * 
	 */
	final PaintGui paintGui;
	
	/**
	 * 
	 */
	public final DrawingEngineImp drawingEngine;
	
	/**
	 * 
	 */
	private Shape newShape;
	
	/**
	 * 
	 */
	private String filePath;

	/**
	 * 
	 */
	private Rectangle dummyRec;
	
	/**
	 * 
	 */
	private Point fixedPosition;
	
	/**
	 * 
	 */
	private String fileLoadPath = "";
	
	/**
	 * 
	 */
	public Shape selectedShape;
	
	/**
	 * 
	 */
	public FreeShape freeShape;
	
	/**
	 * 
	 */
	private static final int TEN = 10;
	
	/**
	 * 
	 */
	private static final int FIVE = 5;
	
	/**
	 * The constructor.
	 * @param paintgui
	 */
	public Controller(PaintGui paintgui){
		this.paintGui = paintgui;
		this.paintGui.AddListener(this);
		
		shapeFactory = new ShapeFactory();
		drawingEngine = new DrawingEngineImp();

		properties = new HashMap<String, Double>();
		mPosition = new Point();
		outlineColor = Color.BLACK;
		fillColor = Color.white;
		
		paintGui.mouseListener = new MouseAdapter() {
			
			public void mousePressed(MouseEvent e) {
				if (mode == "select") {
					select(e);
					paintGui.enableBoxButtons(true);
				} else if (mode == "draw") {
					draw(e);
					paintGui.enableBoxButtons(false);
				} else if (mode == "move") {
					Point p = e.getPoint();
					if(newShape instanceof LineSegment) {
						((LineSegment) newShape).findSecondPoint(p);
					} else {
						newShape.setPosition(new Point(p.x, p.y));	
					}
					drawingEngine.updateShape(selectedShape, newShape);
					paintGui.drawArea.clear();
					drawingEngine.refresh(paintGui.drawArea.getG2());
					paintGui.drawArea.repaint();
					mode = "select";
				}  else if(mode == "mSelect") {
					paintGui.move.setEnabled(false);
					paintGui.resize.setEnabled(false);
					select(e);
					if(freeShape == null) {
						paintGui.feedback.setText("No shape was selected");
					} else {
						freeShape.addShape(selectedShape);	
					}
				}
					
			}
			
			public void mouseReleased(MouseEvent m) {
				try {
					if(shape.getClass().getSimpleName().equals("LineSegment")
							&& mode == "draw"){
						properties.put("Endpoint-x", (double) m.getX());
						properties.put("Endpoint-y", (double) m.getY());
						shape.setProperties(properties);
						shape.setFillColor(Color.black);
						try {
							paintGui.drawArea.clear();
							drawingEngine.addShape((Shape) shape.clone());
						} catch (CloneNotSupportedException e1) {
							e1.printStackTrace();
						}
						drawingEngine.refresh(paintGui.drawArea.getG2());
						paintGui.drawArea.repaint();
					} else if (mode == "resizeLine") {
						Map<String, Double> newProp = new HashMap<>();
						newProp.put("Endpoint-x",(double) m.getX());
						newProp.put("Endpoint-y",(double) m.getY());
						newShape.setProperties(newProp);
						try {
							drawingEngine.updateShape(selectedShape,
									(Shape) newShape.clone());
						} catch (CloneNotSupportedException e) {
							e.printStackTrace();
						}
						paintGui.drawArea.clear();
						drawingEngine.refresh(paintGui.drawArea.getG2());
						paintGui.drawArea.repaint();
						mode = "select";
					}	
				} catch (NullPointerException e) {
					paintGui.feedback.setText(
							"Please selecte a shape to be drawn");
				}
				
			}
		};
		
		paintGui.mouseMotionListener = new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				paintGui.feedback.setText(e.getX() + ", " + e.getY());
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				if(mode == "resizeLine") {
					updateDummyRec(e.getPoint());
				}
			}
		};
	}

	/**
	 * @throws InstantiationException exception.
	 * @throws IllegalAccessException exception.
	 */
	public void actionOnShapesList() throws InstantiationException,
			IllegalAccessException {
		shapeType = (String) paintGui.shapeMenu.getSelectedItem();
		shape = shapeFactory.getShape(shapeType);
		if(!shape.getClass().getSimpleName().equals("LineSegment")){
			properties = TextBoxFactory.showTextBox(shape);
		}
	}

	/**
	 * @param e event
	 * @throws Exception.
	 */
	public void actionOnButtons(ActionEvent e) throws Exception {
		if (e.getSource() == paintGui.ClearBtn) {
			paintGui.drawArea.clear();
			drawingEngine.clear();
			
		} else if (e.getSource() == paintGui.ColorBtn) {
			Color initialColor = Color.black;
			outlineColor = JColorChooser.showDialog(paintGui.drawArea,
					"Select a color", initialColor);
			shape.setColor(outlineColor);

		} else if (e.getSource() == paintGui.BrushBtn) {
			Color initialColor = Color.white;
			fillColor = JColorChooser.showDialog(paintGui.drawArea,
					"Select a color", initialColor);
			shape.setFillColor(fillColor);
			
		} else if (e.getSource() == paintGui.openPlugin) {

			filePath = paintGui.showFileOpenChooser();
			if (filePath == null) {
				throw new NullPointerException();
			}

			this.loadingClass(filePath);
			
		} else if (e.getSource() == paintGui.save) {
			paintGui.saveOption.show(paintGui.save, 0,
					paintGui.save.getBounds().height);
			
		} else if (e.getSource() == paintGui.load) {
			fileLoadPath = paintGui.showFileOpenChooser();
			drawingEngine.load(fileLoadPath);
			paintGui.drawArea.clear();
			drawingEngine.refresh(paintGui.drawArea.getG2());
			paintGui.drawArea.repaint();
			
		} else if (e.getSource() == paintGui.undoBtn) {
			drawingEngine.undo();
			paintGui.drawArea.clear();
			drawingEngine.refresh(paintGui.drawArea.getG2());
			paintGui.drawArea.repaint();
			
		} else if (e.getSource() == paintGui.redoBtn) {
			drawingEngine.redo();
			paintGui.drawArea.clear();
			drawingEngine.refresh(paintGui.drawArea.getG2());
			paintGui.drawArea.repaint();
		}
	}

	/**
	 * Dynamic loading of a class.
	 * @param filePath path.
	 * @throws Exception e.
	 */
	private void loadingClass(String filePath) throws Exception {
		@SuppressWarnings("resource")
		JarFile jarFile = new JarFile(filePath);
		Enumeration<JarEntry> e = jarFile.entries();
		URL[] urls = { new URL("jar:file:" + filePath + "!/") };
		URLClassLoader cl = URLClassLoader.newInstance(urls);
		while (e.hasMoreElements()) {
			JarEntry je = e.nextElement();
			if (je.isDirectory() || !je.getName().endsWith(".class")) {
				continue;
			}
			// -6 because of .class
			String className = je.getName().substring(0,
					je.getName().length() - 6);
			className = className.replace('/', '.');
			@SuppressWarnings("unchecked")
			Class<? extends Shape> Loadedclass = (Class<? extends Shape>) cl
					.loadClass(className);
			drawingEngine.installPlugin(Loadedclass);
			paintGui.shapeMenu.addItem(Loadedclass.getSimpleName());
		}

	}
	
	/**
	 * @param e event
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws CloneNotSupportedException
	 */
	public void actionOnSelectOptions(ActionEvent e)
			throws InstantiationException, IllegalAccessException,
			CloneNotSupportedException {
		if(freeShape != null) {
			performOptionOnFreeShape(e);
		} else if(selectedShape != null) {
			newShape = (Shape)selectedShape.clone();
			performOption(e);
		} else {
			paintGui.feedback.setText("No shape was selected.");
		}
	}
	
	/**
	 *  Changes the mode.
	 */
	public void actionOnModesList() {
		mode = (String) paintGui.modeOptions.getSelectedItem();
		if(mode == "mSelect") {
			freeShape = new FreeShape();
			paintGui.enableBoxButtons(true);
		} else {
			freeShape = null;
		}
	}
	
	/**
	 * Performs the selection options.
	 * @param e
	 * @throws CloneNotSupportedException
	 */
	private void performOption(ActionEvent e) 
			throws CloneNotSupportedException {
		if(e.getSource().equals(paintGui.resize)) {
			if(selectedShape instanceof LineSegment) {
				mode = "resizeLine";
				drawDashedRectangle();
				paintGui.feedback.setText("Drag the end point to resize");
			} else {
				Map<String, Double> properties 
				= TextBoxFactory.showTextBox(newShape);
				newShape.setProperties(properties);
				drawingEngine.updateShape(selectedShape,
						(Shape)newShape.clone());
			}
			
		} else if(e.getSource().equals(paintGui.delete)) {
			drawingEngine.removeShape(selectedShape);
			
		} else if(e.getSource().equals(paintGui.duplicate)) {
			newShape.setPosition(new Point(selectedShape.getPosition().x 
					+ TEN, selectedShape.getPosition().y + TEN));
			drawingEngine.addShape(newShape);
			
		} else if(e.getSource().equals(paintGui.changeFillColor)) {
			if(selectedShape instanceof LineSegment) {
				paintGui.feedback.setText(
						"Cannot change fill color for line");
			} else {
				Color initialColor = Color.white;
				fillColor = JColorChooser.showDialog(paintGui.drawArea,
						"Select a color", initialColor);
				newShape.setFillColor(fillColor);		
				drawingEngine.updateShape(selectedShape, newShape);
			}
						
		} else if(e.getSource().equals(paintGui.move)) {
			mode = "move";
			
		} else if(e.getSource().equals(paintGui.changeOutlineColor)) {
			Color initialColor = Color.black;
			outlineColor = JColorChooser.showDialog(paintGui.drawArea, 
					"Select a color", initialColor);
			newShape.setColor(outlineColor);
			drawingEngine.updateShape(selectedShape, newShape);
		}
		paintGui.drawArea.clear();
		drawingEngine.refresh(paintGui.drawArea.getG2());
		paintGui.drawArea.repaint();

	}
	
	/**
	 * Draws the shape.
	 * @param e
	 */
	private void draw(MouseEvent e) {
		mPosition = e.getPoint();
		try {
			shape.setPosition(mPosition);
			if(!shape.getClass().getSimpleName().equals("LineSegment")){
				shape.setProperties(properties);
				try {
					drawingEngine.addShape((Shape) shape.clone());
				} catch (CloneNotSupportedException e1) {
					e1.printStackTrace();
				}
				paintGui.drawArea.clear();
				drawingEngine.refresh(paintGui.drawArea.getG2());
				paintGui.drawArea.repaint();
			}
		} catch (NullPointerException exception) {
			paintGui.feedback.setText("Please select a Shape to be drawn");
		}
	}

	/**
	 * Searches for the selected shape.
	 * @param e
	 */
	private void select(MouseEvent e) {
		Point currentPosition = e.getPoint();
		Shape[] shapes = drawingEngine.getShapes();
		for(int i = (shapes.length - 1) ; i >= 0; i--) {
			ShapeImp currentShape = (ShapeImp)shapes[i];
			if(currentShape.containsPoint(currentPosition)){
				selectedShape = currentShape;
				paintGui.feedback.setText("Shape is selected");
				break;
			}
		}
	}
	
	/**
	 * Draws a invisible rectangle.
	 */
	private void drawDashedRectangle() {
		dummyRec = new Rectangle();
		fixedPosition = new Point(selectedShape.getPosition().x,
				selectedShape.getPosition().y);
		dummyRec.setPosition(new Point(fixedPosition.x - FIVE,
				fixedPosition.y - FIVE));
		dummyRec.getProperties().put("Width",
				selectedShape.getProperties()
				.get("Endpoint-x") - fixedPosition.x + FIVE);
		dummyRec.getProperties().put("Height", selectedShape
				.getProperties().get("Endpoint-y") - fixedPosition.y + FIVE);
		dummyRec.drawDashed(paintGui.drawArea.getG2(), dashed);
	}
	
	/**
	 * Updates the invisible rectangle.
	 * @param newEndPoint
	 */
	private void updateDummyRec(Point newEndPoint) {
		dummyRec.getProperties().put("Width",
				(double) newEndPoint.x - fixedPosition.x + FIVE);
		dummyRec.getProperties().put("Height", 
				(double) newEndPoint.y - fixedPosition.y + FIVE);
		dummyRec.drawDashed(paintGui.drawArea.getG2(), dashed);
	}
	
	/**
	 * @param e Action Event.
	 */
	private void performOptionOnFreeShape(ActionEvent e) {
		if(e.getSource().equals(paintGui.delete)) {
			freeShape.delete();
			drawingEngine.addFreeShape(freeShape);
		} else if(e.getSource().equals(paintGui.duplicate)) {
			freeShape.duplicate();
			drawingEngine.addFreeShape(freeShape);
		} else if(e.getSource().equals(paintGui.changeFillColor)) {
			Color initialColor = Color.white;
			fillColor = JColorChooser.showDialog(paintGui.drawArea,
					"Select a color", initialColor);
			freeShape.setFillColor(fillColor);
			drawingEngine.addFreeShape(freeShape);
		} else if(e.getSource().equals(paintGui.changeOutlineColor)) {
			Color initialColor = Color.black;
			outlineColor = JColorChooser.showDialog(paintGui.drawArea, 
					"Select a color", initialColor);
			freeShape.setColor(outlineColor);
			drawingEngine.addFreeShape(freeShape);
		}
		paintGui.drawArea.clear();
		drawingEngine.refresh(paintGui.drawArea.getG2());
		paintGui.drawArea.repaint();
		freeShape = new FreeShape();
	}
}
