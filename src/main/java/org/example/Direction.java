package org.example;

public enum Direction {
    N, E, S, W;

    /**
     * @return Devuelve la direccion que se encuentra a la izquierda de la direccion actual
     */
    public Direction left() {
        return switch (this) {
            case N -> W;
            case E -> N;
            case S -> E;
            case W -> S;
        };
    }

    /**
     * @return Devuelve la direccion que se encuentra a la derecha de la direccion actual
     */
    public Direction right() {
        return switch (this) {
            case N -> E;
            case E -> S;
            case S -> W;
            case W -> N;
        };
    }
}
