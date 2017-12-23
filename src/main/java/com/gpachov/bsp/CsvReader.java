package com.gpachov.bsp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {
    public static List<Double> readCsv(String location) {
        String csvFile = location;
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {
            br = new BufferedReader(new FileReader(csvFile));
            int count = 0;
            List<Double> prices = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                if (count > 0) {
                    // use comma as separator
                    String[] country = line.split(cvsSplitBy);
                    String price = country[1];
                    prices.add(Double.parseDouble(price));
                }
                count++;


            }
            return prices;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
