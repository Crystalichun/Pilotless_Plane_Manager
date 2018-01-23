package com.tw.pilotless_plane.utils;

import com.tw.pilotless_plane.models.Signal;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.*;

public class SignalParserTest {

    private String filePath = "./signal.txt";

    @Before
    public void setup() throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath))) {
            writer.write("plane1 1 1 1");
            writer.newLine();
            writer.write("plane1 1 1 1 2 3 4");
            writer.newLine();
            writer.write("plane1 3 4 5 1 1 1");
            writer.newLine();
            writer.write("plane1 1 1 1");
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
    }


    @Test
    public void parse() throws IOException {
        List<Signal> signalList = SignalParser.parse(filePath);

        Assert.assertEquals(6,signalList.size());
        Assert.assertEquals("1 1 1",signalList.get(0).getPreCoordinate().toString());
        Assert.assertNull(signalList.get(0).getOffset());
        Assert.assertEquals("2 3 4",signalList.get(1).getOffset().toString());
    }
}