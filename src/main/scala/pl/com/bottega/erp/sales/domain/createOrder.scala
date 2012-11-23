package pl.com.bottega.erp.sales.domain

import pl.com.bottega.erp.sales.domain.errors.OrderCreationException
import pl.com.bottega.ddd.EntityStatus
import policies.rebate.Rebates._
import com.novus.salat.json.TimestampDateStrategy
import pl.com.bottega.ddd.domain.sharedkernel.Money
import java.sql.Timestamp

object createOrder extends ((Client, Long) => RebatePolicy => Order) {

  private def checkIfClientCanPerformPurchase(client: Client) {
    val msg: String = "Can not perform purchase, because of the Client status: " + client.entityStatus
    if (client.entityStatus != EntityStatus.Active) throw new OrderCreationException(msg, client.id)
  }

  override def apply(client: Client, id: Long) = {
    checkIfClientCanPerformPurchase(client)
    (rebatePolicy) => {
      null//Order(id, OrderStatus.Draft, Money(0), List.empty, new Timestamp(1l))
    }
  }
}
