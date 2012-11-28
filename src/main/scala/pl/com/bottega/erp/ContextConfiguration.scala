package pl.com.bottega.erp

import sales.application.commands.handlers.CreateOrderCommandHandler
import sales.domain.Order
import sales.infrastructure.repo.mongo.{MongoClientRepository, MongoOrderRepository, MongoProductRepository}
import pl.com.bottega.erp.sales.presentation.mongo.MongoProductFinder
import akka.actor.ActorSystem

/**
 * Represents configuration of context dependencies.
 */
class ContextConfiguration(actorSystem: ActorSystem) {

      lazy val eventsPublisher = pl.com.bottega.cqrs.Events.publishToAkkaBus(actorSystem)(_)

      lazy val productFinder = new MongoProductFinder
      lazy val productRepository = new MongoProductRepository
      lazy val clientRepository = new MongoClientRepository
      lazy val createOrder: Order.OrderFactory = pl.com.bottega.erp.sales.domain.createOrder(eventsPublisher)
      lazy val orderRepository = new MongoOrderRepository
      lazy val createOrderHandler = new CreateOrderCommandHandler(this)
      lazy val createRebatePolicy = pl.com.bottega.erp.sales.domain.createRebatePolicy
}
