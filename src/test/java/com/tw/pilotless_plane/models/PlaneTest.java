package com.tw.pilotless_plane.models;

import com.tw.pilotless_plane.PlaneManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Signal.class,Coordinate.class, Plane.class})
public class PlaneTest {
    private Plane plane;

    @Before
    public void setup(){
        plane = new Plane();
    }

    @Test
    public void testNewPlane(){
        Assert.assertFalse(plane.isAtFault());
    }

    @Test
    public void testGetCurrCoordinate() {
        Signal signal1 = PowerMockito.mock(Signal.class);
        Signal signal2 = PowerMockito.mock(Signal.class);
        Coordinate coordinate = PowerMockito.mock(Coordinate.class);
        PowerMockito.when(signal2.calculateCurrCoordinate()).thenReturn(coordinate);
        this.plane.getSignalList().add(signal1);
        this.plane.getSignalList().add(signal2);

        Assert.assertEquals(coordinate,signal2.calculateCurrCoordinate());

    }

    @Test
    public void testAddSignal() {
        Signal signal = new Signal();
        plane.addSignal(signal);

        Assert.assertEquals(1,plane.getSignalList().size());

    }
}