package eg.edu.alexu.csd.oop.draw.saveAndLoad;

import java.awt.Color;
import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import eg.edu.alexu.csd.oop.draw.Shape;
import eg.edu.alexu.csd.oop.draw.controller.ShapeFactory;
import eg.edu.alexu.csd.oop.draw.cs68.DrawingEngineImp;
import eg.edu.alexu.csd.oop.draw.cs68.ShapeImp;


import eg.edu.alexu.csd.oop.draw.json.JSONArray;
import eg.edu.alexu.csd.oop.draw.json.JSONObject;
import eg.edu.alexu.csd.oop.draw.json.parser.JSONParser;
import eg.edu.alexu.csd.oop.draw.json.parser.ParseException;
import eg.edu.alexu.csd.oop.draw.shapes.Circle;


/**
 * @author Merit Victor
 *
 */
public class Json implements IHistory{
	
	/**
	 * 
	 */
	private DrawingEngineImp draw;

	/**
	 * Constructor.
	 * 
	 * @param drawingEngineImp given.
	 */
	public Json(DrawingEngineImp drawingEngineImp) {
		this.draw = drawingEngineImp;
	}

	@SuppressWarnings("unchecked")
	public void save(String path) {
		JSONArray jsonArray = new JSONArray();
		for (Shape shape : draw.getShapes()) {
			ShapeImp circle = new Circle();
			
			
			if (!draw.getSupportedShapes()
					.contains(shape.getClass())){
				
				shape=circle;
			}
			JSONObject obj = new JSONObject();
			obj.put("type", shape.getClass().getSimpleName());
			if ((shape.getPosition() != null)) {
				obj.put("positionX", (int) shape.getPosition().getX());
				obj.put("positionY", (int) shape.getPosition().getY());
			}else{
				obj.put("positionX",(int) circle.getPosition().getX());
				obj.put("positionY", (int) circle.getPosition().getY());
			}
			if ((shape.getColor()) != null){
				obj.put("outlineColor", shape.getColor().getRGB());
			}else{
				obj.put("outlineColor", circle.getColor().getRGB());
			}
				if ((shape.getFillColor()) != null) {
					obj.put("fillColor", shape.getFillColor().getRGB());
				}else{
					obj.put("fillColor",circle.getFillColor().getRGB());
				}
				if(shape.getProperties()!=null){
				obj.put("properties", shape.getProperties());
				}else{
					obj.put("properties", circle.getProperties());
				}
				jsonArray.add(obj);
			
			try {
				File file = new File(path);
				file.createNewFile();
				FileWriter fileWriter = new FileWriter(file);
				fileWriter.write(jsonArray.toJSONString());
				fileWriter.flush();
				fileWriter.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void load(String path) {
		JSONParser parser = new JSONParser();
		try {
			List<Shape> list = new ArrayList<>();
			Object obj = parser.parse(new FileReader(path));
			JSONArray shapes = (JSONArray) obj;
			for (int i = 0; i < shapes.size(); i++) {
				JSONObject shapeObj = (JSONObject) shapes.get(i);
				list.add(addJsonShape(shapeObj));
			}
			Iterator<Shape> iterator = list.iterator();
			while (iterator.hasNext()) {
				draw.addShape(iterator.next());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		draw.clearRedoStack();
		draw.clearUndoStack();
	}

	@SuppressWarnings("unchecked")
	private Shape addJsonShape(JSONObject shapeObj)
			throws InstantiationException, IllegalAccessException {
		ShapeFactory shapeFactory = new ShapeFactory();
		Shape shape;
		shape = shapeFactory.getShape((String) shapeObj.get("type"));
		Point point = new Point();
		
		point.x = ((Long) shapeObj.get("positionX")).intValue();
		point.y = ((Long) shapeObj.get("positionY")).intValue();
		
		shape.setPosition(point);
		shape.setProperties((Map<String, Double>) 
				shapeObj.get("properties"));
		Color outLineColor = new Color(
				((Long) shapeObj.get("outlineColor")).intValue());
		shape.setColor(outLineColor);
		Color fillColor = new Color(
				((Long) shapeObj.get("fillColor")).intValue());
		shape.setFillColor(fillColor);

		return shape;
	}
}
