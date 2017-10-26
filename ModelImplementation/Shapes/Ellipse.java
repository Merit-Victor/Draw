package Shapes;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Ellipse extends ShapeImp {

    private Map<String, Double> prop;

    public Ellipse() {
        prop = new HashMap<>();
        prop.put("Semi-major axis", 0.0);
        prop.put("Semi-minor axis", 0.0);

    }

    @Override
    public void draw(Graphics canvas) {

        if (canvas != null) {
            ((Graphics2D) canvas).setColor(this.getFillColor());

            canvas.fillArc(this.getPosition().x, this.getPosition().y, (this.getProperties().get("Semi-major axis")).intValue(), (this.getProperties().get("Semi-minor axis")).intValue(), 0, 360);
            ((Graphics2D) canvas).setStroke(new BasicStroke(2));
            ((Graphics2D) canvas).setColor(this.getColor());
            canvas.drawArc(this.getPosition().x, this.getPosition().y, (this.getProperties().get("Semi-major axis")).intValue(), (this.getProperties().get("Semi-minor axis")).intValue(), 0, 360);
        }

    }
}
