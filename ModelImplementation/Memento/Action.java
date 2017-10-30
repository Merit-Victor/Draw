package Memento;

import eg.edu.alexu.csd.oop.draw.Shape;

public class Action {

    private State mState;
    private Shape oldShape;
    private Shape newShape;

    public Action(State state, Shape newShape) {
        this.mState = state;
        this.newShape = newShape;
    }

    public Action(State state, Shape oldShape, Shape newShape) {
        this.mState = state;
        this.oldShape = oldShape;
        this.newShape = newShape;
    }

    public State getState() {
        return mState;
    }

    public Shape getNewShape() {
        return newShape;
    }

    public Shape getOldShape() {
        return oldShape;
    }
}
