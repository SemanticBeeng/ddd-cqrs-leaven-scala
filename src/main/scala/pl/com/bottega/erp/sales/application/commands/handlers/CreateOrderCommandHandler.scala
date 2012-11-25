package pl.com.bottega.erp.sales.application.commands.handlers

import pl.com.bottega.cqrs.command.CommandHandler
import pl.com.bottega.erp.sales.application.commands.CreateOrderCommand
import pl.com.bottega.erp.sales.domain.Order._
import pl.com.bottega.erp.sales.domain.{OrderRepository, ClientRepository}
import pl.com.bottega.cqrs.Events

class CreateOrderCommandHandler(
                                val env: {
                                  val clientRepository: ClientRepository
                                  val createOrder: OrderFactory
                                  val orderRepository: OrderRepository
                                })
  extends CommandHandler[CreateOrderCommand] {

  override def handle(command: CreateOrderCommand) {
    val client = env.clientRepository.findOneById(1).get // TODO get client id from request context
    val order = env.createOrder(client, command.newOrderId)
    env.orderRepository.save(order)
  }
}
