package org.example;
import java.util.ArrayList;
public class Controller {
    Plateau plateau;
    ArrayList<Drone> drones;

    public Controller() {

        drones = new ArrayList<>();
    }

    public String processInput(String[] args) {
        // read the first line
        String[] plateauCoordinates = args[0].split(" ");
        plateau = new Plateau(Integer.parseInt(plateauCoordinates[0]), Integer.parseInt(plateauCoordinates[1]));
        StringBuilder result = new StringBuilder();

        for (int i = 1; i < args.length; i += 2) {
            String[] droneData = args[i].split(" ");
            Drone drone = new Drone(Integer.parseInt(droneData[0]), Integer.parseInt(droneData[1]), Direction.valueOf(droneData[2]));
            drones.add(drone);
            String[] droneMovement = args[i + 1].split("");
            drone.move(droneMovement);
            result.append(drone.getX()).append(" ").append(drone.getY()).append(" ").append(drone.getDirection()).append("\n");
        }
        return result.toString().trim();
    }
}
