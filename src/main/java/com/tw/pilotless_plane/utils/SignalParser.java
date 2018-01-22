package com.tw.pilotless_plane.utils;

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
    private static final String InitSignalPatten = "^([A-Za-z0-9]+)\\s+(-?\\d+)\\s+(-?\\d+)\\s+(-?\\d+)";
    private static final String MomentSignalPatten = "^([A-Za-z0-9]+)\\s+(-?\\d+)\\s+(-?\\d+)\\s+(-?\\d+)\\s+(-?\\d+)\\s+(-?\\d+)\\s+(-?\\d+)$";


    public static List<Signal> parse(String filePath) throws IOException {
        List<Signal> signalList = new ArrayList<Signal>();
        signalList.add(parseInitSignal(filePath));
        signalList.addAll(parseMomentSignals(filePath));
        return  signalList;
    }

    private static Signal parseInitSignal(String filePath) throws IOException {
        Pattern pattern = Pattern.compile(InitSignalPatten);
        Stream<String> stringStream = Files.lines(Paths.get(filePath));
        Matcher matcher = pattern.matcher(stringStream.findFirst().get());
        Signal signal = new Signal();
        if(matcher.find()){

        }
        return signal;
    }

    private static List<Signal> parseMomentSignals(String filePath) throws IOException {
        Stream<String> stringStream = Files.lines(Paths.get(filePath));
        return stringStream.skip(1).map(str -> {
            return parseMomentSignal(str); }).collect(Collectors.toList());
    }

    private static Signal parseMomentSignal(String str)  {
        Pattern pattern = Pattern.compile(MomentSignalPatten);
        Matcher matcher = pattern.matcher(str);
        Signal signal = new Signal();
        if (matcher.find()){

        }
        return signal;
    }
}
