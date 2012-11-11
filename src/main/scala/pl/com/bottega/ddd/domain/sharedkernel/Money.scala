package pl.com.bottega.ddd.domain.sharedkernel

import java.util.Currency
import java.lang.String
import pl.com.bottega.ddd.domain.sharedkernel.Money
import java.math.RoundingMode

object Money{
  def apply(valueParam: BigDecimal, currencyCodeParam: String = Currency.getInstance("EUR").getCurrencyCode) = {
    new Money(valueParam, currencyCodeParam)
  }
}
class Money(valueParam: BigDecimal, currencyCodeParam: String) {

  val currencyCode = currencyCodeParam
  val value = valueParam.setScale(2, BigDecimal.RoundingMode.HALF_EVEN)


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
    this.+(new Money(-money.value, money.currencyCode))
  }

  def +(money: Money): Money = {
    if (!compatibleCurrency(money)) {
      throw new IllegalArgumentException("Currency mismatch")
    }
    new Money(value + money.value, determineCurrencyCode(money))
  }


  def *(multiplier: BigDecimal) = new Money(value * multiplier, currencyCode)

  def getCurrency : Currency = Currency.getInstance(currencyCode)

  override def toString = {
    "%0$.2f %s".format(value, getCurrency.getSymbol)
  }
}