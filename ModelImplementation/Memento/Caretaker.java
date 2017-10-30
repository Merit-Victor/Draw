package Memento;

import java.util.Stack;

public class Caretaker {
    private Stack<Memento> mementoesStack = new Stack<>();
    private Stack<Memento> redoStack = new Stack<Memento>();

    public void addMemento(Memento m) {
        mementoesStack.push(m);
    }

    public Memento rollback() {
        Memento memento = mementoesStack.pop();
        redoStack.push(memento);
        return memento;
    }

    public Memento stepForward() {
        Memento memento = redoStack.pop();
        mementoesStack.push(memento);
        return memento;
    }
}
