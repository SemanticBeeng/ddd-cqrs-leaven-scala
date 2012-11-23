package pl.com.bottega.erp.sales.domain.policies.rebate

import pl.com.bottega.erp.sales.domain.{OrderProduct, Product}
import pl.com.bottega.ddd.domain.sharedkernel.Money


object Rebates {
  type Quantity = Int
  type InitialCost = Money
  type RebatedAmount = Money

  type RebatePolicy = (OrderProduct, Quantity, InitialCost) => RebatedAmount
}