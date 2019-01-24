
package com.money.app;

import java.util.List;

/**
 * Money
 */
public class Money {

  public final Double value;
  public final String currency;

  public Money(Double value, String currency) {
    this.value = value;
    this.currency = currency;
  }

  public Money sum(Money money2) throws Exception {
    if (this.currency != money2.currency)
      throw new Exception("Currencies do not match.");

    return sum(money2, 1.0);
  }

  public Money sum(Money money2, Double exchangeRate) {
    return new Money(this.value + (money2.value * exchangeRate), this.currency);
  }

  public static Money sum(List<Money> fundsToSum) {

    Money baseMoney = fundsToSum.get(0);
    fundsToSum.remove(0);

    Exchange exchange = new Exchange();

    for (Money money : fundsToSum) {
      Double rate = 1.0;
      if (money.currency != baseMoney.currency)
        rate = exchange.getRate(money.currency, baseMoney.currency);
      baseMoney = baseMoney.sum(money, rate);
    }

    return baseMoney;
  }
}

