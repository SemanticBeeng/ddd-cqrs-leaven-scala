package pl.com.bottega.erp.sales.api.events

import pl.com.bottega.erp.sales.domain.ProductType
import pl.com.bottega.ddd.domain.sharedkernel.Money
import pl.com.bottega.ddd.DomainEvent

case class ProductAddedToOrder(productid: Long, orderId: Long, productType: ProductType.Value, price: Money, quantity: Int) extends DomainEvent
