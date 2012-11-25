package pl.com.bottega.erp.sales.domain

import errors.OrderOperationException
import events.{ProductAddedToOrder, OrderArchived, OrderCreated}
import pl.com.bottega.ddd.{EntityStatus, DomainEntity}
import pl.com.bottega.ddd.EntityStatus._
import pl.com.bottega.ddd.domain.sharedkernel.{newId, Money}
import java.sql.Timestamp
import policies.rebate.Rebates._
import pl.com.bottega.cqrs.Events

object Order {
  type OrderFactory = ((Client, Long) => Order)

  def apply(created: OrderCreated): Order = Order(created.id, OrderStatus.Draft, Money(0), List.empty, None)
}

case class Order(override val id: Long, status: OrderStatus.Value,
                 totalCost: Money, items: List[OrderLine], submitDate: Option[Timestamp]) extends DomainEntity(id: Long) {

  def apply(archived: OrderArchived): Order = new Order(id, OrderStatus.Archived, totalCost, items, submitDate)

  def addProduct(product: Product, quantity: Int)(implicit policy: RebatePolicy, client: Client, publishEvent: Events.EventPublisher): Order = {
    checkIfDraft(client)
    val event = ProductAddedToOrder(product.id, id, product.productType, product.price, quantity)
    publishEvent(event)
    apply(event)
  }

  def apply(productAdded: ProductAddedToOrder)(implicit client: Client, policy: RebatePolicy): Order = {
    checkIfDraft(client)
    val lineOption = items.find(item => item.id == productAdded.productid)
    val newItems = lineOption match {
      case Some(orderLine) => {
        val index = items.indexOf(orderLine)
        items.updated(index, orderLine.increaseQuantity(productAdded.quantity, policy))
      }
      case None => OrderLine(newId(), OrderProduct(productAdded), productAdded.quantity, policy) :: items
    }
    Order(id, status, totalCost, newItems, submitDate).recalculate(policy)
  }
  private def recalculate(policy: RebatePolicy): Order = {
    val itemsWithRebate = items.map(_.applyPolicy(policy))
    val newTotalCost = items.foldLeft(Money(0))((accumulator, item) => accumulator + item.effectiveCost)
    Order(id, status, newTotalCost, itemsWithRebate, submitDate)
  }

  private def checkIfDraft(client: Client) {
    if (status ne OrderStatus.Draft) throw new OrderOperationException("Operation allowed only in DRAFT status", client.id, id)
  }
}

object OrderLine {
  def apply(id: Long, product: OrderProduct, quantity: Int, rebatePolicy: RebatePolicy): OrderLine = {
    OrderLine(id, product, quantity, Money(0), Money(0)).applyPolicy(rebatePolicy)
  }
}

case class OrderLine(override val id: Long, product: OrderProduct, quantity: Int, regularCost: Money, effectiveCost: Money)
  extends DomainEntity(id) {
  def applyPolicy(policy: RebatePolicy): OrderLine = {

    val regularCost = product.price * quantity
    val rebate = policy(product, quantity, regularCost)
    val newEffectiveCost = regularCost - rebate
    OrderLine(id, product, quantity, regularCost, newEffectiveCost)
  }

  def increaseQuantity(addedQuantity: Int, policy: RebatePolicy): OrderLine =
    OrderLine(id, product, quantity + addedQuantity, regularCost, effectiveCost).applyPolicy(policy)
}

object OrderProduct {

  def apply(event: ProductAddedToOrder): OrderProduct =
    new OrderProduct(event.productid, event.productType, event.price)

}

case class OrderProduct(override val id: Long, productType: ProductType.Value, price: Money) extends DomainEntity(id)