package Command;

import eg.edu.alexu.csd.oop.draw.DrawingEngine;
import eg.edu.alexu.csd.oop.draw.Shape;

public class RemoveCommand implements Command {

    @Override
    public void execute(DrawingEngine engine, Shape newShape) {
        engine.removeShape(newShape);
    }

}
