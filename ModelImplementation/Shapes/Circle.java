package Shapes;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Circle extends ShapeImp {

    private Map<String, Double> prop;

    public Circle() {
        prop = new HashMap<>();
        prop.put("Radius", 0.0);
        super.setProperties(prop);
    }

    @Override
    public void draw(Graphics canvas) {

    }
}
