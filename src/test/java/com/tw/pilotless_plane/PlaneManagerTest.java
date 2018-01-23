package com.tw.pilotless_plane;

import com.tw.pilotless_plane.models.Plane;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import static org.junit.Assert.*;

public class PlaneManagerTest {

    private String filePath = "./signal.txt";
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setup() throws IOException {
        System.setOut(new PrintStream(outContent));
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath))) {
            writer.write("plane1 1 1 1");
            writer.newLine();
            writer.write("plane1 1 1 1 2 3 4");
            writer.newLine();
            writer.write("plane1 3 4 5 1 1 1");
            writer.newLine();
            writer.write("plane1 1 1 1 1 1 1");
            writer.newLine();
            writer.write("plane1 1 1 -1 1 2 2");
            writer.newLine();
            writer.write("plane1 1 1 1 1 2 3 ");
            writer.newLine();
        }
    }

    @After
    public void destory() throws IOException {
        if (Files.exists(Paths.get(filePath))) {
            Files.delete(Paths.get(filePath));
        }
        System.setOut(System.out);
        System.setIn(System.in);

    }


    @Test
    public void testScanFilePath() {
        String  expectedOutput = "Please input the signal filePath." + System.lineSeparator();

        System.setIn(new ByteArrayInputStream(("signal.txt" + System.lineSeparator()).getBytes()));
        Assert.assertEquals("signal.txt",PlaneManager.scanFilePath(new Scanner(System.in))); //???
        Assert.assertEquals(expectedOutput,outContent.toString());
    }

   @Test
    public void testInitPlane() throws IOException {
        PlaneManager.initPlane(filePath);
        Plane plane = PlaneManager.plane;

        Assert.assertEquals("plane1",plane.getId());
        Assert.assertEquals(6,plane.getSignalList().size());
        Assert.assertFalse(plane.getSignalList().get(0).isInvalid());
        Assert.assertFalse(plane.getSignalList().get(1).isInvalid());
        Assert.assertFalse(plane.getSignalList().get(2).isInvalid());
        Assert.assertTrue(plane.getSignalList().get(3).isInvalid());
        Assert.assertTrue(plane.isAtFault());

    }

    @Test
    public void testScanMsgId() {
        String  expectedOutput = "Please input message id." + System.lineSeparator()
                +"The message id must be number ! Check please. " + System.lineSeparator();

        System.setIn(new ByteArrayInputStream(("ss" + System.lineSeparator() + "7" + System.lineSeparator()).getBytes()));
        Assert.assertEquals(7,PlaneManager.scanMsgId(new Scanner(System.in)));
        Assert.assertEquals(expectedOutput,outContent.toString());
    }

    @Test
    public void testGetPlaneMsg() throws IOException {
        PlaneManager.initPlane(filePath);

        Assert.assertEquals("plane1 0 1 1 1",PlaneManager.getPlaneMsg(0));
        Assert.assertEquals("plane1 1 3 4 5",PlaneManager.getPlaneMsg(1));
        Assert.assertEquals("Error:3",PlaneManager.getPlaneMsg(3));
        Assert.assertEquals("Can't find 7",PlaneManager.getPlaneMsg(7));
    }
}