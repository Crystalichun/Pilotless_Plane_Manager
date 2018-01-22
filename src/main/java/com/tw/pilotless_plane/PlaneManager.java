package com.tw.pilotless_plane;

import com.tw.pilotless_plane.models.Signal;
import com.tw.pilotless_plane.utils.SignalParser;

import java.util.List;
import java.util.Scanner;

public class PlaneManager {
    public static String scanFilePath(Scanner scanner) {
        System.out.println("Please input signal filePath.");
        return scanner.nextLine();
    }


    public static void initPlane(String filePath) {
        List<Signal> signalList = SignalParser.parse(filePath);
        creatPlane(signalList.remove(0));
        loadPlane(signalList);
    }


    public static int scanMsgId(Scanner scanner) {
    }

    public static String getPlaneMsg(int msgId) {
    }

    private static void creatPlane(Signal remove) {
    }

    private static void loadPlane(List<Signal> signalList) {
    }
}
