package com.tw.pilotless_plane.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;

@RunWith(PowerMockRunner.class)
@PrepareForTest({File.class,FileUtils.class})
public class FileUtilsTest {
    private String filePath = "test.txt";

    @Mock
    private File mockFile;


    @Test
    public void testCheckFile() throws Exception {
        PowerMockito.whenNew(File.class).withArguments(filePath).thenReturn(mockFile);
        PowerMockito.when(mockFile.exists()).thenReturn(true);
        PowerMockito.when(mockFile.length()).thenReturn(new Long(1));
        FileUtils.checkFile(filePath);
    }

    @Test(expected = FileNotFoundException.class)
    public void testCheckFileNotExist() throws Exception {
        PowerMockito.whenNew(File.class).withArguments(filePath).thenReturn(mockFile);
        PowerMockito.when(mockFile.exists()).thenReturn(false);
        FileUtils.checkFile(filePath);
    }

    @Test(expected = NoSuchElementException.class)
    public void testCheckFileEmpty() throws Exception {
        PowerMockito.whenNew(File.class).withArguments(filePath).thenReturn(mockFile);
        PowerMockito.when(mockFile.exists()).thenReturn(true);
        PowerMockito.when(mockFile.length()).thenReturn((long) 0);
        FileUtils.checkFile(filePath);
    }



}