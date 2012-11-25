package pl.com.bottega.erp.sales.domain.events

import pl.com.bottega.ddd.DomainEvent

case class OrderCreated(id: Long) extends DomainEvent
