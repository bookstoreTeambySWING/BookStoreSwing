package com.bookstoreswing.utils;

/**
 * Formats price values - placeholder for future localization
 */
public class PriceFormatter {
    public static String format(double price){
        return String.format("%.2f DA", price);
    }
}
