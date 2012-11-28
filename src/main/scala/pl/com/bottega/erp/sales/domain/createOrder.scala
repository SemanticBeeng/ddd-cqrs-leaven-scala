package pl.com.bottega.erp.sales.domain

import pl.com.bottega.erp.sales.domain.errors.OrderCreationException
import pl.com.bottega.ddd.EntityStatus
import pl.com.bottega.cqrs.Events
import pl.com.bottega.erp.sales.domain.Order._
import pl.com.bottega.erp.sales.api.events.OrderCreated

object createOrder extends (Events.EventPublisher => OrderFactory) {

  val UndefinedTimestamp = None

  private def checkIfClientCanPerformPurchase(client: Client) {
    val msg: String = "Can not perform purchase, because of the Client status: " + client.entityStatus
    if (client.entityStatus != EntityStatus.Active) throw new OrderCreationException(msg, client.id)
  }

  override def apply(publishEvent: Events.EventPublisher) = {
    (client, id) => {
      checkIfClientCanPerformPurchase(client)
      val created = OrderCreated(id, client.id)
      publishEvent(created)
      Order.apply(created)
    }
  }
}
