package com.tw.pilotless_plane;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ApplicationTest {

    private String filePath = "./signal.txt";
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setup() throws IOException {
        System.setOut(new PrintStream(outContent));
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath))) {
            writer.write("plane1 1 1 1");
            writer.newLine();
            writer.write("plane1 1 1 1 2 3 4 ");
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
    public void testSuccess() throws IOException {
        String expectedOutput = "Please input the signal filePath." + System.lineSeparator() +
                "Please input message id." + System.lineSeparator() + "plane1 0 1 1 1" + System.lineSeparator();
        System.setIn(new ByteArrayInputStream((filePath + System.lineSeparator() + "0" + System.lineSeparator()).getBytes()));
        Application.main(null);
        Assert.assertEquals(expectedOutput, outContent.toString());

    }

    @Test
    public void testFileNotFound() throws IOException {
        String expectedOutput = "Please input the signal filePath." + System.lineSeparator() +
                "File not Found,check Please!" + System.lineSeparator();
        System.setIn(new ByteArrayInputStream(("notCreateFile.txt" + System.lineSeparator()).getBytes()));

        Application.main(null);

        Assert.assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testFileIsEmpty() throws IOException {
        String expectedOutput = "Please input the signal filePath." + System.lineSeparator() +
                "File is empty,check Please!" + System.lineSeparator();
        System.setIn(new ByteArrayInputStream(("signal.txt" + System.lineSeparator()).getBytes()));
        clearFile();

        Application.main(null);

        Assert.assertEquals(expectedOutput, outContent.toString());    }

    private void clearFile() throws IOException {
        FileWriter fileWriter = new FileWriter(new File(filePath));
        fileWriter.write("");
        fileWriter.close();
    }


}