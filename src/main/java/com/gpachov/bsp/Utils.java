package com.gpachov.bsp;

public class Utils {
    public static void logD(String message) {
        if (Constants.DEBUG) {
            System.out.println(message);
        }
    }
}
