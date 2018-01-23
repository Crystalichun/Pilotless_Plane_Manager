package com.tw.pilotless_plane.utils;

import com.tw.pilotless_plane.models.Coordinate;
import com.tw.pilotless_plane.models.Offset;
import com.tw.pilotless_plane.models.Signal;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SignalParser {
    private static final String INITSIGNALPATTEN = "^([A-Za-z0-9]+)\\s+(-?\\d+)\\s+(-?\\d+)\\s+(-?\\d+)";
    private static final String MOVEMENTSIGNALPATTEN = "^([A-Za-z0-9]+)\\s+(-?\\d+)\\s+(-?\\d+)\\s+(-?\\d+)\\s+(-?\\d+)\\s+(-?\\d+)\\s+(-?\\d+)$";


    public static List<Signal> parse(String filePath) throws IOException {
        List<Signal> signalList = new ArrayList<Signal>();
        signalList.add(parseInitSignal(filePath));
        signalList.addAll(parseMovementSignals(filePath));
        return  signalList;
    }

    private static Signal parseInitSignal(String filePath) throws IOException {
        Pattern pattern = Pattern.compile(INITSIGNALPATTEN);
        Stream<String> stringStream = Files.lines(Paths.get(filePath));
        Matcher matcher = pattern.matcher(stringStream.findFirst().get());
        Signal signal = new Signal();
        if(matcher.find()){
            signal.setPlaneId(matcher.group(1));
            signal.setPreCoordinate(new Coordinate(Integer.parseInt(matcher.group(2)),
                    Integer.parseInt(matcher.group(3)),Integer.parseInt(matcher.group(4))));
        }
        return signal;
    }

    private static List<Signal> parseMovementSignals(String filePath) throws IOException {
        Stream<String> stringStream = Files.lines(Paths.get(filePath));
        return stringStream.skip(1).map(str -> {
            return parseMovementSignal(str); }).collect(Collectors.toList());
    }

    private static Signal parseMovementSignal(String str)  {
        Pattern pattern = Pattern.compile(MOVEMENTSIGNALPATTEN);
        Matcher matcher = pattern.matcher(str);
        Signal signal = new Signal();
        if (matcher.find()){
            signal.setPlaneId(matcher.group(1));
            signal.setPreCoordinate(new Coordinate(Integer.parseInt(matcher.group(2)),
                    Integer.parseInt(matcher.group(3)),Integer.parseInt(matcher.group(4))));
            signal.setOffset(new Offset(Integer.parseInt(matcher.group(5)),
                    Integer.parseInt(matcher.group(6)),Integer.parseInt(matcher.group(7))));
        }
        return signal;
    }
}
