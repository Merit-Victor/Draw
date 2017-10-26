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
        super.setProperties(prop);
    }

    @Override
    public void draw(Graphics canvas) {

    }
}
