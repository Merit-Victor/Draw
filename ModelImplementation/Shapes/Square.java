package Shapes;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Square extends ShapeImp {

    private Map<String, Double> prop;

    public Square() {
        prop = new HashMap<>();
        prop.put("Length", 0.0);
        super.setProperties(prop);
    }

    @Override
    public void draw(Graphics canvas) {

    }
}
