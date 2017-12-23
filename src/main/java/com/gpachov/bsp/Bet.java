package com.gpachov.bsp;

public class Bet {
    private static int BASE;

    private double money;
    private int priceIndex;
    private int jump;
    private int id;

    public Bet(double money, int priceIndex, int jump) {
        this.money = money;
        this.priceIndex = priceIndex;
        this.jump = jump;
        this.id = BASE++;
    }

    public double getMoney() {
        return money;
    }

    public int getPriceIndex() {
        return priceIndex;
    }

    public int getJump() {
        return jump;
    }

    @Override
    public String toString() {
        return "com.gpachov.bsp.Bet{" +
                "money=" + money +
                ", priceIndex=" + priceIndex +
                ", jump=" + jump +
                ", id=" + id +
                '}';
    }

    public int getId() {
        return id;
    }
}
