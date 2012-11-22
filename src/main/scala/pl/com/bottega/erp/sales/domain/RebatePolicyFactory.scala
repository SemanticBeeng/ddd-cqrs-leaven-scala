package pl.com.bottega.erp.sales.domain

import policies.rebate.decorators.VipRebate
import policies.rebate.Rebates._
import pl.com.bottega.ddd.domain.sharedkernel.Money


class RebatePolicyFactory {

  private def isVip: Boolean = true // TODO

  def createPolicy: RebatePolicy = {

    val vipPolicy = VipRebate(Money(1000), Money(100))
    if (isVip)
      vipPolicy(Some(standardRebatePolicy))
    else
      standardRebatePolicy
  }
}
