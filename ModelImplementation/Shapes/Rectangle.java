package Shapes;

import Memento.State;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Rectangle extends ShapeImp {

    private Map<String, Double> prop;

    public Rectangle() {
        prop = new HashMap<>();
        prop.put("Width", 0.0);
        prop.put("Height", 0.0);
        this.mState = State.added;
        this.mUndoCommand = new UndoCommand();
        this.mRedoCommand = new RedoCommand();
    }

    @Override
    public void draw(Graphics canvas) {
        if (canvas != null) {
            ((Graphics2D) canvas).setColor(this.getFillColor());

            canvas.fillRect(this.getPosition().x, this.getPosition().y,
                    (this.getProperties().get("Width")).intValue(),
                    (this.getProperties().get("Height")).intValue());
            ((Graphics2D) canvas).setStroke(new BasicStroke(2));
            ((Graphics2D) canvas).setColor(this.getColor());
            canvas.drawRect(this.getPosition().x, this.getPosition().y,
                    (this.getProperties().get("Width")).intValue(),
                    (this.getProperties().get("Height")).intValue());
        }
    }
}
