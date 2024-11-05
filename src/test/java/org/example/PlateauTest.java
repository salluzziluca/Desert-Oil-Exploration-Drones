package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlateauTest {

    @Test
    void isWithinBounds() {
        Plateau plateau = new Plateau(5, 5);
        assertTrue(plateau.isWithinBounds(0, 0));
        assertTrue(plateau.isWithinBounds(5, 5));
        assertTrue(plateau.isWithinBounds(3, 3));
        assertFalse(plateau.isWithinBounds(6, 6));
        assertFalse(plateau.isWithinBounds(-1, -1));
    }
}