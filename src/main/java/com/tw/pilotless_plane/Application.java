package com.tw.pilotless_plane;

import com.tw.pilotless_plane.utils.FileUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String filePath = null;

        while (true) {
            try {
                filePath = PlaneManager.scanFilePath(scanner);
                FileUtils.checkFile(filePath);
                break;
            } catch (FileNotFoundException e) {
                System.out.println("File not Found,check Please!");
            } catch (NoSuchElementException e) {
                System.out.println("File is empty,check Please!");
            }
        }

        PlaneManager.initPlane(filePath);

        while (true) {
            String msgId = PlaneManager.scanMsgId(scanner);
            if (!msgId.equals("stop")) {
                System.out.println(PlaneManager.getPlaneMsg(Integer.parseInt(msgId)));
            } else {
                break;
            }
        }
    }
}

