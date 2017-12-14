package eg.edu.alexu.csd.oop.draw.saveAndLoad;

import java.awt.Color;
import java.awt.Point;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import eg.edu.alexu.csd.oop.draw.Shape;
import eg.edu.alexu.csd.oop.draw.controller.ShapeFactory;
import eg.edu.alexu.csd.oop.draw.cs68.DrawingEngineImp;
import eg.edu.alexu.csd.oop.draw.cs68.ShapeImp;
import eg.edu.alexu.csd.oop.draw.shapes.Circle;

/**
 * @author Merit Victor
 *
 */
public class Xml implements IHistory {
	
	/**
	 * 
	 */
	private DrawingEngineImp draw;

	/**
	 * Constructor.
	 * 
	 * @param drawingEngineImp given.
	 */
	public Xml(DrawingEngineImp drawingEngineImp) {
		this.draw = drawingEngineImp;
	}

	public void save(String path) {
		DocumentBuilderFactory dbFactory =
				DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.newDocument();
	
			Element rootElement = doc.createElement("shapes");
			doc.appendChild(rootElement);

			for (Shape shape : draw.getShapes()) {
				ShapeImp circle = new Circle();
				if (!draw.getSupportedShapes().contains(shape.getClass())){
					shape=circle;
				}
				
				Element shapeEle = doc.createElement("shape");
				rootElement.appendChild(shapeEle);
	
				Attr attr = doc.createAttribute("type");
				attr.setValue(shape.getClass().getSimpleName());
				shapeEle.setAttributeNode(attr);
				if ((shape.getPosition() != null)) {
				Element positionX = doc.createElement("positionX");
				positionX.appendChild(doc.createTextNode(Integer
						.toString((int) shape.getPosition().getX())));
				shapeEle.appendChild(positionX);

				Element positionY = doc.createElement("positionY");
				positionY.appendChild(doc.createTextNode(Integer
						.toString((int) shape.getPosition().getY())));
				shapeEle.appendChild(positionY);
				}else{
					Element positionX = doc.createElement("positionX");
					positionX.appendChild(doc.createTextNode(Integer
							.toString((int) circle.getPosition().getX())));
					shapeEle.appendChild(positionX);

					Element positionY = doc.createElement("positionY");
					positionY.appendChild(doc.createTextNode(Integer
							.toString((int) circle.getPosition().getY())));
					shapeEle.appendChild(positionY);
				}
				if ((shape.getColor()) != null){
				Element outlineColor = doc.createElement("outlineColor");
				outlineColor.appendChild(doc.createTextNode(Integer
						.toString(shape.getColor().getRGB())));
				shapeEle.appendChild(outlineColor);
				}else{
					Element outlineColor = doc.createElement("outlineColor");
					outlineColor.appendChild(doc.createTextNode(Integer
							.toString(circle.getColor().getRGB())));
					shapeEle.appendChild(outlineColor);
				}
				if ((shape.getFillColor()) != null) {
				Element fillColor = doc.createElement("fillColor");
				fillColor.appendChild(
						doc.createTextNode(Integer.toString(shape
						.getFillColor().getRGB())));
				shapeEle.appendChild(fillColor);
				}else{
					Element fillColor = doc.createElement("fillColor");
					fillColor.appendChild(
							doc.createTextNode(Integer.toString(circle
							.getFillColor().getRGB())));
					shapeEle.appendChild(fillColor);
				}
				Element properties = doc.createElement("properties");
				Set<String> keys;
				if(shape.getProperties()!=null){
				keys = shape.getProperties().keySet();
				@SuppressWarnings("rawtypes")
				Iterator iter = keys.iterator();
				while (iter.hasNext()) {
					String keyName = (String) iter.next();
					Element element = doc.createElement(keyName);
					element.appendChild(doc.createTextNode(Double
							.toString(shape
									.getProperties().get(keyName))));
					properties.appendChild(element);
				}
				shapeEle.appendChild(properties);
				
				}else{
					
					keys = circle.getProperties().keySet();
					@SuppressWarnings("rawtypes")
					Iterator iter = keys.iterator();
					while (iter.hasNext()) {
						String keyName = (String) iter.next();
						Element element = doc.createElement(keyName);
						element.appendChild(doc.createTextNode(Double
								.toString(circle
										.getProperties().get(keyName))));
						properties.appendChild(element);
					}
					shapeEle.appendChild(properties);
				}
				
			}
		
			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(path));
			transformer.transform(source, result);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}

	public void load(String path) {
		try {
			List<Shape> list = new ArrayList<>();
			File inputFile = new File(path);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("shape");
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				ShapeFactory shapeFactory = new ShapeFactory();
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					Shape shape = shapeFactory.getShape(eElement
							.getAttribute("type"));
					Point point = new Point();
					point.x = Integer.parseInt(eElement
							.getElementsByTagName("positionX").item(0)
							.getTextContent());
					point.y = Integer.parseInt(eElement
							.getElementsByTagName("positionY").item(0)
							.getTextContent());

					shape.setPosition(point);
					Color outLineColor = new Color(Integer.parseInt(eElement
							.getElementsByTagName("outlineColor").item(0)
							.getTextContent()));
					shape.setColor(outLineColor);
					Color fillColor = new Color(Integer.parseInt(eElement
							.getElementsByTagName("fillColor").item(0)
							.getTextContent()));

					shape.setFillColor(fillColor);
					Map<String, Double> properties = new HashMap<>();
					
					NodeList shapeProp = eElement
							.getElementsByTagName("properties");
					Node node = shapeProp.item(0);
					NodeList prop = node.getChildNodes();
					for (int j = 0; j < prop.getLength(); j++) {
						properties.put(
								prop.item(j).getNodeName(),
								Double.parseDouble(
										prop.item(j).getFirstChild()
										.getTextContent()));
					}
					shape.setProperties(properties);
					list.add(shape);
				}
			}
			Iterator<Shape> iterator = list.iterator();
			while (iterator.hasNext()) {
				draw.addShape(iterator.next());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		draw.clearRedoStack();
		draw.clearUndoStack();
	}
}
