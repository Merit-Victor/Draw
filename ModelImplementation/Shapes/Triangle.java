package Shapes;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Triangle extends ShapeImp {


	private Map<String, Double> prop;

    public Triangle() {
		prop = new HashMap<>();
		prop.put("Firstx", 0.0);
		prop.put("Firsty", 0.0);
		prop.put("Secondx", 0.0);
		prop.put("Secondy", 0.0);


	}

    @Override
    public void draw(Graphics canvas) {
		if (canvas != null) {
			int[] xCoordinates = {this.getPosition().x, this.getProperties().get("Firstx").intValue(), this.getProperties().get("Secondx").intValue()};
			int[] yCoordinates = {this.getPosition().y, this.getProperties().get("Firsty").intValue(), this.getProperties().get("Secondy").intValue()};
			((Graphics2D) canvas).setColor(this.getFillColor());

			canvas.fillPolygon(xCoordinates, yCoordinates, 3);
			((Graphics2D) canvas).setStroke(new BasicStroke(2));
			((Graphics2D) canvas).setColor(this.getColor());
			canvas.fillPolygon(xCoordinates, yCoordinates, 3);
		}
	}
}
