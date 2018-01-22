package com.tw.pilotless_plane.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;

public class FileUtils {
    public static void checkFile(String filePath) throws FileNotFoundException,NoSuchElementException{
        File file = new File(filePath);
        if(file.exists()){
            throw new FileNotFoundException();
        }
        if(file.length() == 0){
            throw new NoSuchElementException();
        }
    }
}
