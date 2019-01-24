package com.money.app;

import java.util.HashMap;

/**
 * Exchange
 * Used as a data source.
 */
public class Exchange {

  private static final HashMap<CurrencyKey, Double> conversionTable = new HashMap<CurrencyKey, Double>();

  public Exchange() {
    /**
     * This could potentially be simplified since the EUR -> GBP
     * could use the division of the GBP -> EUR rate on the value of the EUR.
     * Other than that it feels like a lookup table to me.
     */
    conversionTable.put(new CurrencyKey("GBP", "EUR"), 1.1);
    conversionTable.put(new CurrencyKey("EUR", "GBP"), 0.9);
    conversionTable.put(new CurrencyKey("GBP", "USD"), 1.4);
    conversionTable.put(new CurrencyKey("USD", "GBP"), 0.71);
  }

  public Double getRate(String from, String to) {
    return conversionTable.get(new CurrencyKey(from, to));
  }
}

