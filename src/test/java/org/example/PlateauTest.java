package org.example;


import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PlateauTest {

    @Test
    public void isWithinBounds() {
        Plateau plateau = new Plateau(5, 5);
        assertTrue(plateau.isWithinBounds(0, 0));
        assertTrue(plateau.isWithinBounds(5, 5));
        assertTrue(plateau.isWithinBounds(3, 3));
        assertFalse(plateau.isWithinBounds(6, 6));
        assertFalse(plateau.isWithinBounds(-1, -1));
    }
}