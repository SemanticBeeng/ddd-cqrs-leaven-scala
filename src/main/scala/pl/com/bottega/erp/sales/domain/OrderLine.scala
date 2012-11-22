package pl.com.bottega.erp.sales.domain

import pl.com.bottega.ddd.DomainEntity
import pl.com.bottega.ddd.domain.sharedkernel.Money
import policies.rebate.decorators.vipRebate

case class OrderLine(override val id: Long, product: Product, quantity: Int, regularCost: Money, effectiveCost: Money)
  extends DomainEntity(id)
{

  def doodle() {


  }
}