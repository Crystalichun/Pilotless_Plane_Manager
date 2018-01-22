package com.tw.pilotless_plane;

import com.tw.pilotless_plane.utils.FileUtils;

import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Application {
    public static void  main(String[] args)  {
        Scanner scanner = new Scanner(System.in);
        try{
            String filePath = PlaneManager.scanFilePath(scanner);
            FileUtils.checkFile(filePath);
            PlaneManager.initPlane(filePath);
            int msgId = PlaneManager.scanMsgId(scanner);
            System.out.println(PlaneManager.getPlaneMsg(msgId));
        }catch (FileNotFoundException e){
            System.out.println("File not Found,check Please!");
        }catch (NoSuchElementException e){
            System.out.println("File is empty,check Please!");
        }

    }
}
