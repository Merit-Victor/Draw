package Memento;

public class Memento {

    private Action action;

    public Memento(Action action) {
        this.action = action;
    }

    public Action getState() {
        return this.action;
    }

}
