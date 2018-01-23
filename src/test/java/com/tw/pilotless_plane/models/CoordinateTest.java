package com.tw.pilotless_plane.models;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class CoordinateTest {

    @Test
    public void testEquals() {
        Coordinate a = new Coordinate(1,2 ,4);
        Coordinate b = new Coordinate(1,2 ,4);

        Assert.assertTrue(a.equals(b));
        Assert.assertFalse(! a.equals(b));
    }

    @Test
    public void testToString() {
        Coordinate a = new Coordinate(1,2 ,3);

        Assert.assertEquals("1 2 3",a.toString());
    }
}