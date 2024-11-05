package org.example;



import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class DroneTest {

    @Test
    public void testMove(){

        Drone drone = new Drone(0, 0, Orientation.N);
        drone.move(new String[]{"M", "M", "M"});
        assertEquals(0, drone.getX());
        assertEquals(3, drone.getY());
        assertEquals(Orientation.N, drone.getOrientation());
    }

    @Test
    public void testMoveDifferentOrientation(){

        Drone drone = new Drone(0, 0, Orientation.E);
        drone.move(new String[]{"M", "M", "M"});
        assertEquals(3, drone.getX());
        assertEquals(0, drone.getY());
        assertEquals(Orientation.E, drone.getOrientation());
    }

    @Test
    public void testMoveDifferentOrientation2(){

        Drone drone = new Drone(0, 3, Orientation.S);
        drone.move(new String[]{"M", "M", "M"});
        assertEquals(0, drone.getX());
        assertEquals(0, drone.getY());
        assertEquals(Orientation.S, drone.getOrientation());
    }

    @Test
    public void testMoveDifferentOrientation3(){

        Drone drone = new Drone(3, 0, Orientation.W);
        drone.move(new String[]{"M", "M", "M"});
        assertEquals(0, drone.getX());
        assertEquals(0, drone.getY());
        assertEquals(Orientation.W, drone.getOrientation());
    }

     @Test
     public void testMoveInvalidMovement() {
         Drone drone = new Drone(0, 0, Orientation.N);
         assertThrows(IllegalArgumentException.class, () -> drone.move(new String[]{"H"}));
     }
    }
