package org.example;

public class Plateau {
    private final static int X_INICIAL = 0;
    private final int x_final;

    private final static int Y_INICIAL = 0;
    private final int y_final;

    public Plateau(int x, int y) {
        this.x_final = x;
        this.y_final = y;
    }

    /**
     * @param x posicion x
     * @param y posicion y
     * @return Devuelve si el punto (x, y) se encuentra dentro de los limites de la plateau
     */
    public boolean isWithinBounds(int x, int y) {
        return x >= X_INICIAL && x <= this.x_final && y >= Y_INICIAL && y <= this.y_final;
    }
}
