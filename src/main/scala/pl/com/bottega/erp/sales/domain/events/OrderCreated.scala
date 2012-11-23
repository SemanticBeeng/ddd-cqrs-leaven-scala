package pl.com.bottega.erp.sales.domain.events

import pl.com.bottega.erp.sales.domain.{OrderLine, OrderStatus}
import pl.com.bottega.ddd.domain.sharedkernel.Money
import pl.com.bottega.ddd.DomainEvent

case class OrderCreated(id: Long, status: OrderStatus.Value, totalCost: Money, items: List[OrderLine]) extends DomainEvent
