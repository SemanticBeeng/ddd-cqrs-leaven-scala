package pl.com.bottega.erp.sales.domain.policies.rebate.decorators

import pl.com.bottega.ddd.domain.sharedkernel.Money
import pl.com.bottega.erp.sales.domain.policies.rebate.Rebates._

object vipRebate extends ((Money, Money) => Option[RebatePolicy] => RebatePolicy) {

  override def apply(minimalThreshold: Money, rebateValue: Money) =
    (innerPolicy) => {
      (product, quantity, regularCost) => {
        val baseValue = innerPolicy.map(_(product, quantity, regularCost)).getOrElse(regularCost)
        if (baseValue > minimalThreshold)
          (baseValue - rebateValue)
        else baseValue
      }
    }
}
