package pl.com.bottega.erp.sales.domain

import policies.rebate.decorators.vipRebate
import policies.rebate.Rebates._
import pl.com.bottega.ddd.domain.sharedkernel.Money
import policies.rebate.standardRebate


class RebatePolicyFactory {

  private def isVip: Boolean = true // TODO

  def createPolicy: RebatePolicy = {

    val vipPolicy = vipRebate(Money(1000), Money(100))
    val standard = standardRebate(10, 1)

    if (isVip)
      vipPolicy(Some(standard))
    else
      standard
  }
}
