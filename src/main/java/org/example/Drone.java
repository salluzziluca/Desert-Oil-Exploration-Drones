package org.example;

public class Drone {
    private int x;
    private int y;
    private Direction direction;

    public Drone (int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
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
}
