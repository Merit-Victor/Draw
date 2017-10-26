package Shapes;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Rectangle extends ShapeImp {

    private Map<String, Double> prop;

    public Rectangle() {
        prop = new HashMap<>();
        prop.put("Width", 0.0);
        prop.put("Height", 0.0);
        super.setProperties(prop);
    }

    @Override
    public void draw(Graphics canvas) {

    }
}
