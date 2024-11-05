package org.example;

import java.util.ArrayList;

public class Drone {
    private int x;
    private int y;
    private Orientation orientation;

    private final ArrayList<OutOfBoundsObserver> observers;

    public Drone (int x, int y, Orientation orientation) {
        this.x = x;
        this.y = y;
        this.orientation = orientation;
       observers = new ArrayList<>();
    }

    /**
     * Mueve el drone segun los comandos de movimiento (M, L, R) -> Movement, Rotate Left, Rotate Right
     * @param droneMovement Un string con los diferentes comandos de movimiento
     */
    public void move(String[] droneMovement) {

        for (String movement : droneMovement) {
            switch (movement) {
                case "L":
                    orientation = orientation.left();
                    break;
                case "R":
                    orientation = orientation.right();
                    break;
                case "M":
                    moveForward();
                    break;
                default:
                    throw new IllegalArgumentException("Invalid movement command: " + movement);
            }
        }
    }

    /**
     * Mueve el drone hacia adelante segun direccion a la que este mirando en ese momento
     */
    private void moveForward() {
        switch (orientation) {
            case N:
                y++;
                break;
            case E:
                x++;
                break;
            case S:
                y--;
                break;
            case W:
                x--;
                break;
        }
        notifyObservers(x, y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getDirection() {
        return orientation.toString();
    }

    public void addObserver(OutOfBoundsObserver observer) {
        observers.add(observer);
    }

    /**
     * Notifica a sus observers de su posicion actual
     * @param x
     * @param y
     */
    public void notifyObservers(int x, int y) {
        for (OutOfBoundsObserver observer : observers) {
            observer.checkOutOfBounds(x, y);
        }
    }
}
