package org.example;

public class Plateau {
    private static int X_INICIAL = 0;
    private int x_final;

    private static int Y_INICIAL = 0;
    private int y_final;

    public Plateau(int x, int y) {
        this.x_final = x;
        this.y_final = y;
    }

    public boolean isWithinBounds(int x, int y) {
        return x >= X_INICIAL && x <= this.x_final && y >= Y_INICIAL && y <= this.y_final;
    }
}
