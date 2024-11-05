package org.example;



import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class ControllerTest {

    @Test
    public void testConsigna(){
        String input = """
5 5
1 2 N
LMLMLMLMM
3 3 E
MMRMMRMRRM
""";

        String output = "1 3 N 5 1 E";
        Controller controller = new Controller();
        String[] args = input.split("\n");
        String result = controller.processInput(args);
        assertEquals(output, result);

    }

    @Test
    public void testMovementOutOfBounds(){
        String input = """
1 1
0 0 N
MMM
""";

        Controller controller = new Controller();
        String[] args = input.split("\n");
        assertThrows(IllegalArgumentException.class, () -> controller.processInput(args));
    }

    @Test
    public void testDroneCreationOutOfBounds(){
        String input = """
1 1
2 2 N
""";

            Controller controller = new Controller();
            String[] args = input.split("\n");
            assertThrows(IllegalArgumentException.class, () -> controller.processInput(args));
        }

        @Test
        public void testNoInput(){
            String input = "";
            Controller controller = new Controller();
            String[] args = input.split("\n");
            assertThrows(IllegalArgumentException.class, () -> controller.processInput(args));
        }

        @Test
        public void testOnlyPlateau(){
            String input = """
5 5
""";
            String output = "";
            Controller controller = new Controller();
            String[] args = input.split("\n");
            String result = controller.processInput(args);
            assertEquals(output, result);
        }


}