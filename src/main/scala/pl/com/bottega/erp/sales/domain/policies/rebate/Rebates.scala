package pl.com.bottega.erp.sales.domain.policies.rebate

import pl.com.bottega.erp.sales.domain.Product
import pl.com.bottega.ddd.domain.sharedkernel.Money


object Rebates {

  type RebatePolicy = (Product, Int, Money) => Money
  def standardRebatePolicy(product: Product, quantity: Int, regularCost: Money): Money = { regularCost }
}