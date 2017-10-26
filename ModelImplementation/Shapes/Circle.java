package Shapes;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Circle extends ShapeImp {

    private Map<String, Double> prop;

    public Circle() {
        prop = new HashMap<>();
        prop.put("Radius", 0.0);

    }

    @Override
    public void draw(Graphics canvas) {
        if (canvas != null) {
            ((Graphics2D) canvas).setColor(this.getFillColor());

            canvas.fillArc(this.getPosition().x, this.getPosition().y, (this.getProperties().get("Radius")).intValue(), (this.getProperties().get("Radius")).intValue(), 0, 360);
            ((Graphics2D) canvas).setStroke(new BasicStroke(2));
            ((Graphics2D) canvas).setColor(this.getColor());
            canvas.drawArc(this.getPosition().x, this.getPosition().y, (this.getProperties().get("Radius")).intValue(), (this.getProperties().get("Radius")).intValue(), 0, 360);
        }
    }
}
