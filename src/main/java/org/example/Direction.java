package org.example;

public enum Direction {
    N, E, S, W;

    /**
     *  Returns the direction to the left of the current direction.
     * @return
     */
    public Direction left() {
        return switch (this) {
            case N -> W;
            case E -> N;
            case S -> E;
            case W -> S;
        };
    }

    public Direction right() {
        return switch (this) {
            case N -> E;
            case E -> S;
            case S -> W;
            case W -> N;
        };
    }
}
