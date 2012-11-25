package pl.com.bottega.erp.sales.domain

import policies.rebate.decorators.vipRebate
import policies.rebate.Rebates._
import pl.com.bottega.ddd.domain.sharedkernel.Money
import policies.rebate.{Rebates, standardRebate}

object createRebatePolicy extends Rebates.RebatePolicyFactory {

  private def isVip(client: Client): Boolean = true

  override def apply(client: Client) = {
      val vipPolicy = vipRebate(Money(1000), Money(100))
      val standard = standardRebate(10, 1)

      if (isVip(client))
        vipPolicy(Some(standard))
      else
        standard
  }
}
