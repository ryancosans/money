package com.money.app;

/**
 * CurrencyKey
 * Used as a composite key for the data source Exchange.
 */
public class CurrencyKey {

  /**
   * Given more time I would make this something storngly typed instead of strings,
   * even when writing these I made the mistake EUR / EURO at least once.
   * I would have included a test for that
   * but I felt that that fell into the category of testing exchange.
   */
  private final String primaryCurrency;
  private final String secondaryCurrency;

  public CurrencyKey(String primaryCurrency, String secondaryCurrency) {
    this.primaryCurrency = primaryCurrency;
    this.secondaryCurrency = secondaryCurrency;
  }

  /**
   * @return the primaryCurrency
   */
  public String getPrimaryCurrency() {
    return primaryCurrency;
  }

  /**
   * @return the secondaryCurrency
   */
  public String getSecondaryCurrency() {
    return secondaryCurrency;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj != null && obj instanceof CurrencyKey) {
      CurrencyKey currencyKey = (CurrencyKey) obj;
      return primaryCurrency.equals(currencyKey.primaryCurrency) && secondaryCurrency.equals(currencyKey.secondaryCurrency);
    }
    return false;
  }

  @Override
  public int hashCode() {
    return (primaryCurrency + secondaryCurrency).hashCode();
  }
}