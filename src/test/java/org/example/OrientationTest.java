package org.example;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OrientationTest {

    @Test
    public void left() {
        Orientation orientation = Orientation.N;
        assertEquals(Orientation.W, orientation.left());
    }

    @Test
    public void right() {
        Orientation orientation = Orientation.N;
        assertEquals(Orientation.E, orientation.right());
    }
}