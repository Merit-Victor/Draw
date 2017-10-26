package Shapes;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Square extends ShapeImp {

    private Map<String, Double> prop;

    public Square() {
        prop = new HashMap<>();
        prop.put("Length", 0.0);

    }

    @Override
    public void draw(Graphics canvas) {
        if (canvas != null) {
            ((Graphics2D) canvas).setColor(this.getFillColor());

            canvas.fillRect(this.getPosition().x, this.getPosition().y, (this.getProperties().get("Length")).intValue(), (this.getProperties().get("Length")).intValue());
            ((Graphics2D) canvas).setStroke(new BasicStroke(2));
            ((Graphics2D) canvas).setColor(this.getColor());
            canvas.drawRect(this.getPosition().x, this.getPosition().y, (this.getProperties().get("Length")).intValue(), (this.getProperties().get("Length")).intValue());
        }
    }
}
