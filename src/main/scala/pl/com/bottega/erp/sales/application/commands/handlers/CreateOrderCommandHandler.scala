package pl.com.bottega.erp.sales.application.commands.handlers

import pl.com.bottega.cqrs.command.CommandHandler
import pl.com.bottega.erp.sales.application.commands.{CommandOrderItem, CreateOrderCommand}
import pl.com.bottega.erp.sales.domain.Order._
import pl.com.bottega.erp.sales.domain.Product
import pl.com.bottega.erp.sales.domain._
import pl.com.bottega.cqrs.Events
import pl.com.bottega.cqrs.Events.EventPublisher
import pl.com.bottega.erp.sales.domain.policies.rebate.Rebates
import pl.com.bottega.erp.sales.application.commands.CommandOrderItem
import pl.com.bottega.erp.sales.application.commands.CreateOrderCommand

class CreateOrderCommandHandler(
                                 val env: {
                                   val productRepository: ProductRepository
                                   val clientRepository: ClientRepository
                                   val createOrder: OrderFactory
                                   val orderRepository: OrderRepository
                                   val createRebatePolicy: Rebates.RebatePolicyFactory
                                   val eventsPublisher: EventPublisher
                                 })

  extends CommandHandler[CreateOrderCommand] {

  override def handle(command: CreateOrderCommand) {
    implicit val client = env.clientRepository.findOneById(1).get // TODO get client id from request context
    implicit val eventsPublisher = env.eventsPublisher
    implicit val rebatePolicy = env.createRebatePolicy(client)
    val emptyOrder = env.createOrder(client, command.newOrderId)
    val domainProducts = command.productIdsWithCounts.map(toDomainProductProduct)
    val resultOrder = domainProducts.foldLeft(emptyOrder)(addProductToOrder)
    env.orderRepository.save(resultOrder)
  }

  private def toDomainProductProduct(item: CommandOrderItem): (pl.com.bottega.erp.sales.domain.Product, Int) =
    (env.productRepository.findOneById(item.id).get, item.count)

  private def addProductToOrder(order: Order, productInfo: (Product, Int))
                               (implicit client: Client, policy: Rebates.RebatePolicy, eventBus: EventPublisher): Order =
    order.addProduct(productInfo._1, productInfo._2)
}
