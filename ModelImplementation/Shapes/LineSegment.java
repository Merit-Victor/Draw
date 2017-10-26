package Shapes;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class LineSegment extends ShapeImp {

	Map<String, Double> prop;
	
    public LineSegment() {
		prop = new HashMap<>();
		prop.put("End point-x", 0.0);
		prop.put("End point-y", 0.0);

	}

    @Override
    public void draw(Graphics canvas) {
		if (canvas != null) {
			((Graphics2D) canvas).setColor(this.getFillColor());
			canvas.drawLine(this.getPosition().x, this.getPosition().y, this.getProperties().get("End point-x").intValue(), this.getProperties().get("End point-y").intValue());
		}
	}
}
