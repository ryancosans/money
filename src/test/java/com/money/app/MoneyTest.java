package com.money.app;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

/**
 * MoneyTest
 */
public class MoneyTest {

  @Test
  public void shouldSumTwoAmountsOfMoney() throws Exception {
    Money purse = new Money(1.0, "GBP");
    Money funds = new Money(1.0, "GBP");

    Money result = purse.sum(funds);

    assertEquals(result.value, 2.0, 0.0001);
  }

  @Test(expected = Exception.class)
  public void shouldNotSumTwoAmountsOfMoneyOfDifferentCurrencies() throws Exception {
    Money purse = new Money(1.0, "GBP");
    Money funds = new Money(1.0, "EUR");

    purse.sum(funds);
  }

  @Test
  public void shouldSumTwoAmountsOfMoneyWithDifferentCurrenciesUsingADefaultExchangeRate() {
    Money purse = new Money(1.0, "GBP");
    Money funds = new Money(1.0, "EUR");

    Double defaultExchangeRate = 1.0;

    Money result = purse.sum(funds, defaultExchangeRate);

    assertEquals(result.value, 2.0, 0.0001);
  }

  @Test
  public void shouldSumTwoAmountsOfMoneyWithDifferentCurrenciesUsingAnExchangeRateLookup() {
    Money purse = new Money(1.0, "GBP");
    Money funds = new Money(1.0, "EUR");

    Double exchangeRate = new Exchange().getRate("GBP", "EUR");

    Money result = purse.sum(funds, exchangeRate);

    assertEquals(result.value, 2.1, 0.0001);
  }

  @Test
  public void shouldSumThreeAmountsOfMoneyWithTheSameCurrency() throws Exception {
    List<Money> fundsToSum = new ArrayList<Money>();
    Money purse = new Money(1.0, "GBP");
    Money funds = new Money(1.0, "GBP");
    fundsToSum.add(purse);
    fundsToSum.add(funds);
    fundsToSum.add(funds);

    Money result = Money.sum(fundsToSum);

    assertEquals(result.value, 3.0, 0.0001);
  }

  @Test
  public void shouldSumThreeAmountsOfMoneyWithTwoDifferentCurrencies() throws Exception {
    List<Money> fundsToSum = new ArrayList<Money>();
    Money purse = new Money(1.0, "GBP");
    Money funds = new Money(1.0, "EUR");
    fundsToSum.add(purse);
    fundsToSum.add(funds);
    fundsToSum.add(funds);

    Money result = Money.sum(fundsToSum);

    assertEquals(result.value, 2.8, 0.0001);
  }

  @Test
  public void shouldSumThreeAmountsOfMoneyWithThreeDifferentCurrencies() throws Exception {
    List<Money> fundsToSum = new ArrayList<Money>();
    Money purse = new Money(1.0, "GBP");
    Money euros = new Money(1.0, "EUR");
    Money dollars = new Money(1.0, "USD");
    fundsToSum.add(purse);
    fundsToSum.add(euros);
    fundsToSum.add(dollars);

    Money result = Money.sum(fundsToSum);

    /**
     * At this point of writing the tests I began to think about
     * what unit testing means in the context of this library.
     * It reminded me of a question I had a while back so I figured I'd include the link here:
     * https://goo.gl/hezBe4 (the original url was quite long).
     * It's not exactly the same but it asks the question is it ok to use a method that is similar
     * or part of another method to test the method in question.
     */

    assertEquals(result.value, 2.61, 0.0001);
  }
}