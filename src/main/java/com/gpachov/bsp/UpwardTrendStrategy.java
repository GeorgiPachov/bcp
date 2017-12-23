package com.gpachov.bsp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.gpachov.bsp.Utils.logD;

public class UpwardTrendStrategy implements Strategy {
    /**
     * The minimal points needed to say something is in trend
     */
    public static final int MINIMAL_TREND_POINTS = 8;
    private List<Double> bitcoinPrices;
    private Map<Integer, Bet> bets = new HashMap<>();

    @Override
    public double bet(List<Double> bitcoinPrices, double N) {
        this.bitcoinPrices = bitcoinPrices;
        Set<Trend> trends = Trends.getTrending(bitcoinPrices);

        // make bets
        List<Trend> filteredList = trends.stream().filter(t -> isStable(t) && isPositive(t)).collect(Collectors.toList());
        int Tsize = filteredList.size();
        for (Trend trend: filteredList) {
            double betPrice = (double) N / Tsize;
            bet(betPrice, trend);
            N-=betPrice;
        }

        // withdraw bets
        double sum = 0;
        for (Map.Entry<Integer, Bet> e : bets.entrySet()) {
            Double money = e.getValue().getMoney(); //pair.first
            int jump = e.getValue().getJump(); //pair.second
            Integer betIndex = e.getKey();
            Double on = bitcoinPrices.get(betIndex);

            double holdings = money/on;
            double newPrice = bitcoinPrices.get(betIndex + jump);

            double withdrawValue = holdings * newPrice;
            logD("Withdrawing :#" + e.getValue().getId() + " " + money + " on " + newPrice);
            sum += withdrawValue;
        }

        return N + sum;
    }

    private static boolean isPositive(Trend trend) {
        List<Integer> realTrend = trend.getTrend();

        if (realTrend.size() > 1) {
            return realTrend.get(1) - realTrend.get(0) > 0;
        }
        return false;

    }


    private static boolean isStable(Trend trend) {
        return trend.getTrend().size() >= MINIMAL_TREND_POINTS;
    }

    private void bet(double price, Trend trend) {
        Bet bet = new Bet(price, trend.lastPriceIndex(), trend.getJumpStep());
        List<Integer> realTrend = trend.getTrend();
        Integer index = realTrend.get(realTrend.size() - 1);
        bets.put(index, bet);
        logD("Placed id:#" + bet.getId() + " " + bet.getMoney() + "  on " + bitcoinPrices.get(index));
    }
}
