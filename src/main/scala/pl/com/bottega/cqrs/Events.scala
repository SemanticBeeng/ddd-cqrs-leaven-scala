package pl.com.bottega.cqrs

import pl.com.bottega.ddd.DomainEvent
import akka.actor.{ActorSystem, ActorSelection}

object Events {

  type EventPublisher = DomainEvent => Unit

  def publishToConsole(event: DomainEvent) { println("Event: " + event)}

  def publishToAkkaBus(actorSystem: ActorSystem)(event: DomainEvent) {
    actorSystem.eventStream.publish(event)
  }
}
