package Memento;

import Command.*;
import eg.edu.alexu.csd.oop.draw.DrawingEngine;

public class RemoteController {

    private Caretaker mCaretaker;
    private DrawingEngine mEngine;
    private CommandFactory factory;

    public RemoteController(DrawingEngine engine, Caretaker caretaker) {
        this.mEngine = engine;
        this.mCaretaker = caretaker;
        factory = new CommandFactory();
    }

//    public void doAction(Action action) {
//        //State = added, removed, or updated
//        factory.execute(mEngine, action);
//        mCaretaker.addMemento(new Memento(action));
//    }

    public void rollBack() {
        //state = undo
        Memento lastMemento = mCaretaker.rollback();
        factory.execute(mEngine, lastMemento.getState());
    }

    public void stepForward() {
        //state = redo
        Memento lastMemento = mCaretaker.stepForward();
        factory.execute(mEngine, lastMemento.getState());
    }
}
