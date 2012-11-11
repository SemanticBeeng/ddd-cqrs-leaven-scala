package pl.com.bottega.ddd.domain.sharedkernel

import org.specs2.mutable._

class MoneyTest extends Specification {

  "Sum of money 2 + 2" should {
    "equal 4" in {
      val result = Money(2) + Money(2)
      result.value === 4
    }
  }
}
