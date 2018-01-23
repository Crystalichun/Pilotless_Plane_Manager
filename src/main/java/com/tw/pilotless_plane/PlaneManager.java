package com.tw.pilotless_plane;

import com.tw.pilotless_plane.models.Plane;
import com.tw.pilotless_plane.models.Signal;
import com.tw.pilotless_plane.utils.SignalParser;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class PlaneManager {
    public static Plane plane;

    public static String scanFilePath(Scanner scanner) {
        System.out.println("Please input the signal filePath.");
        return scanner.nextLine();
    }


    public static void initPlane(String filePath) throws IOException {
        List<Signal> signalList = SignalParser.parse(filePath);
        creatPlane(signalList.remove(0));
        loadPlaneSignals(signalList);
    }


    public static int scanMsgId(Scanner scanner) {
        System.out.println("Please input message id.");
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("The message id must be number ! Check please. ");
            }
        }
    }

    public static String getPlaneMsg(int msgId) {
        if (msgId >= plane.getSignalList().size()) {
            return "Can't find " + msgId;
        } else {
            Signal signal=plane.getSignalList().get(msgId);
            return signal.isInvalid() ? "Error:" + msgId :
                    plane.getId() + " " + msgId + " "+ signal.calculateCurrCoordinate().toString();
        }
    }

    private static void creatPlane(Signal initSignal) {
        correctInitSignal(initSignal);
        plane=new Plane();
        plane.setId(initSignal.getPlaneId());
        plane.setAtFault(initSignal.isInvalid());
        plane.addSignal(initSignal);
    }

    private static void correctInitSignal(Signal initSignal) {
        if (initSignal.getPreCoordinate() == null) {
            initSignal.setInvalid(true);
        }
    }

    private static void loadPlaneSignals(List<Signal> signalList) {
        signalList.forEach(signal -> {
            correctMovementSignal(signal);
            if(!plane.isAtFault()&& signal.isInvalid()){
                plane.setAtFault(true);
            }
            plane.addSignal(signal);
        });
    }

    private static void correctMovementSignal(Signal signal) {
        if (plane.isAtFault()|| signal.calculateCurrCoordinate() == null || !signal.getPreCoordinate().equals(plane.getCurrCoordinate())) {
            signal.setInvalid(true);
        }
    }
}
