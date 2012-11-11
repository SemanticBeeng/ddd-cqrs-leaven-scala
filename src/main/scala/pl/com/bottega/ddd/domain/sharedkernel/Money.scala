package pl.com.bottega.ddd.domain.sharedkernel

import java.util.Currency
import java.lang.String


case class Money(value: BigDecimal, currencyCode: String = Currency.getInstance("EUR").getCurrencyCode) {

  def this(value: BigDecimal, currency: Currency) = {
    this(value, currency.getCurrencyCode)
  }

  def isZero(decimal: BigDecimal): Boolean =
  {
     decimal == 0
  }

  private def compatibleCurrency(money: Money): Boolean =
  {
    isZero(value) || isZero(money.value) || (currencyCode == money.currencyCode)
  }

  override def equals(obj: Any) = {

    if (obj.isInstanceOf[Money]) {
      val money: Money = obj.asInstanceOf[Money]
      compatibleCurrency(money) && (value == money.value)
    }
      else
    false
  }

  def determineCurrencyCode(money: Money): Currency ={
    val resultingCurrenctCode: String = if (isZero(value)) money.currencyCode else currencyCode
    Currency.getInstance(resultingCurrenctCode)
  }

  def -(money: Money): Money = {
    this.+(Money(-money.value, money.currencyCode))
  }

  def +(money: Money): Money = {
    if (!compatibleCurrency(money)) {
      throw new IllegalArgumentException("Currency mismatch")
    }
    new Money(value + money.value, determineCurrencyCode(money))
  }


  def *(multiplier: BigDecimal) = Money(value * multiplier, currencyCode)

  def getCurrency : Currency = Currency.getInstance(currencyCode)

  override def toString = {
    String.format("%0$.2f %s", value, getCurrency.getSymbol)
  }
}