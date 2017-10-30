package Command;

import eg.edu.alexu.csd.oop.draw.DrawingEngine;
import eg.edu.alexu.csd.oop.draw.Shape;

public interface Command {

    public void execute(DrawingEngine engine, Shape newShape);
}
