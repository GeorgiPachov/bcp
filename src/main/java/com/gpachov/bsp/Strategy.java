package com.gpachov.bsp;

import java.util.List;

public interface Strategy {
    double bet(List<Double> bitcoinPrices, double N);

    default String getName() {
        return Strategy.class.getSimpleName();
    }
}
