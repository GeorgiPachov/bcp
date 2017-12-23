package com.gpachov.bsp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Simulator {
    private static String basepath = "/home/pachov/Dev/bcp/data";
    private static final List<Path> testPaths = fetchData();

    private static final double N = 100;

    public static void main(String[] args) {
        double bottomLine = 0;
        for (Path file : testPaths) {
            Strategy strategy = new UpwardTrendStrategy();
            List<Double> bitcoinPrices = CsvReader.readCsv(file.toAbsolutePath().toString());
            double returnedMoney = strategy.bet(bitcoinPrices, N);
            double finalResult = returnedMoney - N;
            bottomLine += finalResult;
            System.out.println("Strategy " + strategy.getName() + " returned " + finalResult + " for simulationSet " + file);
        }
        System.out.println("After " +  testPaths.size() + " days of trading, the result was " + bottomLine);
    }


    private static List<Path> fetchData() {
        try {
            return Files.list(Paths.get(basepath)).collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException("Could not read data");
        }
    }
}
