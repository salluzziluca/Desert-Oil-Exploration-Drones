package org.example;
import java.util.ArrayList;
public class Controller  implements OutOfBoundsObserver{
    Plateau plateau;
    ArrayList<Drone> drones;

    public Controller() {

        drones = new ArrayList<>();
    }

    /**
     * Crea la plateau segun su coordenada final
     * Crea los drones inicializandolos en su posicion y direccion inicial correspondiente
     * Mueve los drones segun los comandos de movimiento (M, L, R) -> Movement, Rotate Left, Rotate Right
     * @param args un string con los diferentes comandos de creacion de plateu y drone
     * @return un string con las posiciones finales de los drones
     */
    public String processInput(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("No input provided.");
        }
        // read the first line
        String[] plateauCoordinates = args[0].split(" ");
        plateau = new Plateau(Integer.parseInt(plateauCoordinates[0]), Integer.parseInt(plateauCoordinates[1]));
        StringBuilder result = new StringBuilder();

        for (int i = 1; i < args.length; i += 2) {
            String[] droneData = args[i].split(" ");
            int droneX = Integer.parseInt(droneData[0]);
            int droneY = Integer.parseInt(droneData[1]);
            checkOutOfBounds(droneX, droneY);
            Drone drone = new Drone(droneX, droneY, Direction.valueOf(droneData[2]));
            drones.add(drone);
            drone.addObserver(this);
            String[] droneMovement = args[i + 1].split("");
            drone.move(droneMovement);
            result.append(drone.getX()).append(" ").append(drone.getY()).append(" ").append(drone.getDirection()).append(" ");
    }
    return result.toString().trim();
}

    @Override
    public void checkOutOfBounds(int x, int y) {
        if (!plateau.isWithinBounds(x, y)) {
            throw new IllegalArgumentException("Drone out of bounds: " + x + " " + y);
        }
    }
}
