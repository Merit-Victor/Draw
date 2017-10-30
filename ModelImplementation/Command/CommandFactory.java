package Command;

import Memento.Action;
import Memento.State;
import eg.edu.alexu.csd.oop.draw.DrawingEngine;

public class CommandFactory {

    public void execute(DrawingEngine engine, Action action) {
        State state = action.getState();
        if (state.equals(State.added)) {
            new AddCommand().execute(engine, action.getNewShape());
        } else if (state.equals(State.removed)) {
            new RemoveCommand().execute(engine, action.getNewShape());
        } else if (state.equals(State.updated)) {
            new UpdateCommand().execute(engine, action.getOldShape(), action.getNewShape());
        }
    }
}
