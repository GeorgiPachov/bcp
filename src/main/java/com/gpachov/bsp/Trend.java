package com.gpachov.bsp;

import java.util.List;

public class Trend {
    private static int BASE = 0;
    private final int id;
    private int jumpStep;
    private List<Integer> trend;

    public Trend(List<Integer> trend, int jumpStep) {
        this.trend = trend;
        this.jumpStep = jumpStep;
        this.id = BASE++;
    }

    public List<Integer> getTrend() {
        return trend;
    }

    public int getJumpStep() {
        return jumpStep;
    }

    @Override
    public String toString() {
        return "com.gpachov.bsp.Trend{" +
                "id=" + id +
                ", jumpStep=" + jumpStep +
                ", trend=" + trend +
                '}';
    }

    public int lastPriceIndex() {
        return trend.get(trend.size() - 1);
    }
}
