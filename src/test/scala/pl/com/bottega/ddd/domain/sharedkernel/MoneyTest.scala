package pl.com.bottega.ddd.domain.sharedkernel

import org.specs2.mutable._

class MoneyTest extends Specification {

  "Sum of money" should {
    "equal 4 for 2 + 2" in {
      Money(2) + Money(2) === Money(4)
    }
    "equal 4.95 for 2.75 + 2.2" in {
      Money(2.75) + Money(2.2) === Money(4.95)
    }
  }

  "Sustraction of money" should {
    "equal 4.8 for 12.5 - 7.7" in {
      Money(12.5) - Money(7.7) === Money(4.8)
    }
    "equal 4.73 for 12.5 - 7.771" in {
      Money(12.5) - Money(7.771) === Money(4.73)
    }
    "equal 0.1 for 2.0 - 1.9 ;)" in {
      Money(2.00) - Money(1.9) === Money(0.1)
    }
  }

  "Sum of money objects with different currencies" should {
    "throw exception" in {
      Money(2, "USD") + Money(2, "EUR") must throwA[Exception]
    }
  }

  "Sustraction of money objects with different currencies" should {
    "throw exception" in {
      Money(2, "USD") - Money(2, "EUR") must throwA[Exception]
    }
  }

  "Money value of 2.5 multiplied by 3" should {
    "equal 7.5" in {
      Money(2.5) * 3 === Money(7.5)
    }
  }

  "Money value initialization rounding" should {
    "round 2.505 to 2.50" in {
      Money(2.505) === Money(2.50)
    }
    "round 2.506 to 2.51" in {
      Money(2.506) === Money(2.51)
    }
  }

  "Money value 5.75 in USD" should {
    "be correctly formatted as a String with symbol" in {
      new Money(5.75, "USD").toString === "5.75 $"
    }
  }


}
