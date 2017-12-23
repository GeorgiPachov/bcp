package com.gpachov.bsp;

import java.util.*;

public class Trends {

    public static Set<Trend> getTrending(List<Double> trainingSet) {
        Set<Trend> results = new LinkedHashSet<>();
        for (int jump = 1; jump < trainingSet.size()/2-1; jump++) {
            boolean isPositiveTrend = false;
            List<Integer> curTr = new ArrayList<>();
            curTr.add(0);

            for (int i = jump; i < trainingSet.size(); i+=jump) {
                double lastTPr = trainingSet.get(curTr.get(curTr.size() - 1));
                double curPr = trainingSet.get(i);
                if (curTr.size() == 1) {
                    curTr.add(i);
                    isPositiveTrend = curPr > lastTPr;
                } else {

                    if (isPositiveTrend) {
                        if (curPr > lastTPr) {
                            curTr.add(i);
                        } else {
                            // trend ends
                            Trend trend = new Trend(curTr, jump);
                            results.add(trend);
                            break;
                        }
                    } else {
                        if (curPr < lastTPr) {
                            curTr.add(i);
                        } else {
                            Trend trend = new Trend(curTr, jump);
                            results.add(trend);
                            break;
                        }
                    }
                }
            }
        }
        return results;
    }
}
