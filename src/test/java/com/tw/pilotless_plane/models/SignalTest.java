package com.tw.pilotless_plane.models;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SignalTest {
    private Signal signal;

    @Before
    public void setup(){
        signal = new Signal();
    }

    @Test
    public void testNewSignal(){
        Assert.assertFalse(signal.isInvalid());
    }

    @Test
    public void calculateCurrCoordinateSuccess() {
        Coordinate a = new Coordinate(1,2,3);
        Offset b = new Offset(1,1,1);
        this.signal.setPreCoordinate(a);
        this.signal.setOffset(b);

        Assert.assertEquals(new Coordinate(2,3,4),signal.calculateCurrCoordinate());
    }

    @Test
    public void calculateCurrCoordinateFail() {
        Assert.assertNull(signal.calculateCurrCoordinate());
    }

}