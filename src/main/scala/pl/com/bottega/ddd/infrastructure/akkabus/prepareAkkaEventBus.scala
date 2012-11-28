package pl.com.bottega.ddd.infrastructure.akkabus

import akka.actor.{Props, ActorSystem}
import pl.com.bottega.erp.sales.presentation.mongo.projections.OrderProjections
import pl.com.bottega.erp.sales.api.events.{ProductAddedToOrder, OrderCreated}

object prepareAkkaEventBus extends (() => ActorSystem) {

  def apply(): ActorSystem = {

    val actorSystem = ActorSystem()
    // can following stuff be automatic with some kind of classpath scanning plus annotations or marker traits?
    val orderProjections = actorSystem.actorOf(Props(new OrderProjections))
    actorSystem.eventStream.subscribe(orderProjections, classOf[OrderCreated])
    actorSystem.eventStream.subscribe(orderProjections, classOf[ProductAddedToOrder])
    actorSystem
  }
}
