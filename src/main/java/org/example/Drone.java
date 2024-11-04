package org.example;

import java.util.ArrayList;

public class Drone {
    private int x;
    private int y;
    private Direction direction;

    private ArrayList<OutOfBoundsObserver> observers;

    public Drone (int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
       observers = new ArrayList<>();
    }

    public void move(String[] droneMovement) {

        for (String movement : droneMovement) {
            switch (movement) {
                case "L":
                    direction = direction.left();
                    break;
                case "R":
                    direction = direction.right();
                    break;
                case "M":
                    moveForward();
                    break;
                default:
                    throw new IllegalArgumentException("Invalid movement command: " + movement);
            }
        }
    }

    private void moveForward() {
        switch (direction) {
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
        return direction.toString();
    }

    public void addObserver(OutOfBoundsObserver observer) {
        observers.add(observer);
    }

    public void notifyObservers(int x, int y) {
        for (OutOfBoundsObserver observer : observers) {
            observer.checkOutOfBounds(x, y);
        }
    }
}
