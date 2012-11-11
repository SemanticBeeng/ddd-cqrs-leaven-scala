package pl.com.bottega.ddd.domain.sharedkernel

import java.util.Currency
import java.lang.String
import pl.com.bottega.ddd.domain.sharedkernel.Money
import java.math.RoundingMode



object Money {

  val DEFAULT_CURRENCY_CODE: String = "EUR"

  def apply(value: Double, currency: Currency): Money = {
    Money(value, currency.getCurrencyCode)
  }

  def apply(value: BigDecimal, currency: Currency): Money = {
    Money(value.doubleValue(), currency.getCurrencyCode)
  }
}
case class Money(doubleValue: Double, currencyCode: String = Currency.getInstance(Money.DEFAULT_CURRENCY_CODE).getCurrencyCode) {

  val value = round(BigDecimal(doubleValue))

  def isZero(decimal: BigDecimal): Boolean = {
    decimal == 0
  }

  private def compatibleCurrency(money: Money): Boolean = {
    isZero(value) || isZero(money.value) || (currencyCode == money.currencyCode)
  }

  override def equals(obj: Any) = {

    if (obj.isInstanceOf[Money]) {
      val money: Money = obj.asInstanceOf[Money]
      compatibleCurrency(money) && (round(value) == round(money.value))
    }
    else
      false
  }

  def determineCurrencyCode(money: Money): Currency = {
    val resultingCurrencyCode: String = if (isZero(value)) money.currencyCode else currencyCode
    Currency.getInstance(resultingCurrencyCode)
  }

  def -(money: Money): Money = {
    this.+(Money(-money.doubleValue, money.currencyCode))
  }

  def +(money: Money): Money = {
    if (!compatibleCurrency(money)) {
      throw new IllegalArgumentException("Currency mismatch")
    }
    Money((value + money.value).doubleValue(), determineCurrencyCode(money))
  }


  private def round(decimal: BigDecimal): BigDecimal = decimal.setScale(2, BigDecimal.RoundingMode.HALF_EVEN)

  def *(multiplier: BigDecimal) = Money((value * multiplier).doubleValue(), currencyCode)
  def getCurrency: Currency = Currency.getInstance(currencyCode)

  override def toString = {
    "%0$.2f %s".format(round(value), getCurrency.getSymbol)
  }


}

