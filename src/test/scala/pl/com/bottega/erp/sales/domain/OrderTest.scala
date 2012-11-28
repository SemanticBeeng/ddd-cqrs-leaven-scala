package pl.com.bottega.erp.sales.domain

import org.specs2.mutable.Specification
import pl.com.bottega.ddd.domain.sharedkernel.Money
import pl.com.bottega.ddd.EntityStatus

class OrderTest extends Specification {

  "Applying archiving event" should  {
  "change state to archived" in {

    val sutOrder = Order.apply(OrderCreated(1, OrderStatus.Draft, Money(1), List.empty))
    // when
    val newOrder = sutOrder.apply(OrderArchived(1))
    // then
    newOrder.status === OrderStatus.Archived
  }
  }
}
