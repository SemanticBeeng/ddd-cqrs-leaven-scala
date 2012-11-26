package pl.com.bottega.cqrs

import pl.com.bottega.ddd.DomainEvent

trait EventProjection[T <: DomainEvent] extends (T => Unit)