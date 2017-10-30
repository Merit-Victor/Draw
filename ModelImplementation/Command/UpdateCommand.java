package Command;

import eg.edu.alexu.csd.oop.draw.DrawingEngine;
import eg.edu.alexu.csd.oop.draw.Shape;

public class UpdateCommand implements Command {

    @Override
    public void execute(DrawingEngine engine, Shape newShape) {

    }

    public void execute(DrawingEngine engine, Shape oldShape, Shape newShape) {
        engine.updateShape(oldShape, newShape);
    }
}
