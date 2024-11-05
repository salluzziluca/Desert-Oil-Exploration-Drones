package org.example;

public enum Orientation {
    N, E, S, W;

    /**
     * @return Devuelve la orientacion que se encuentra a la izquierda de la orientacion actual
     */
    public Orientation left() {
        return switch (this) {
            case N -> W;
            case E -> N;
            case S -> E;
            case W -> S;
        };
    }

    /**
     * @return Devuelve la orientacion que se encuentra a la derecha de la orientacion actual
     */
    public Orientation right() {
        return switch (this) {
            case N -> E;
            case E -> S;
            case S -> W;
            case W -> N;
        };
    }
}
