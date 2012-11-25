package pl.com.bottega.cqrs

import pl.com.bottega.ddd.DomainEvent

object Events {

  type EventPublisher = DomainEvent => Unit

  def publishToConsole(event: DomainEvent) { println("Event: " + event)}
}
