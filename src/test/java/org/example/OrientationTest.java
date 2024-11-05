package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrientationTest {

    @Test
    void left() {
        Orientation orientation = Orientation.N;
        assertEquals(Orientation.W, orientation.left());
    }

    @Test
    void right() {
        Orientation orientation = Orientation.N;
        assertEquals(Orientation.E, orientation.right());
    }
}