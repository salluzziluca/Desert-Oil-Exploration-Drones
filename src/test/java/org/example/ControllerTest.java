package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    @Test
    void testConsigna(){
        String input = """
5 5
1 2 N
LMLMLMLMM
3 3 E
MMRMMRMRRM
""";

        String output = "1 3 N\n" +
                "5 1 E";
        Controller controller = new Controller();
        String[] args = input.split("\n");
        String result = controller.processInput(args);
        assertEquals(output, result);

    }

    @Test
    void testOutOfBounds(){
        String input = """
1 1
0 0 N
MMM
""";

        Controller controller = new Controller();
        String[] args = input.split("\n");
        assertThrows(IllegalArgumentException.class, () -> controller.processInput(args));
    }

}