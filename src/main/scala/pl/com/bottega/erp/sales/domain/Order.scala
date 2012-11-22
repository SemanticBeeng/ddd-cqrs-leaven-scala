package pl.com.bottega.erp.sales.domain

import pl.com.bottega.ddd.DomainEntity
import pl.com.bottega.ddd.domain.sharedkernel.Money
import java.sql.Timestamp

case class Order(override val id: Long, status: OrderStatus.Value,
                  totalCost: Money, items: List[OrderLine], submitDate: Timestamp) extends DomainEntity(id: Long) {

}
