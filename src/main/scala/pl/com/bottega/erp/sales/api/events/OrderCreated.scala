package pl.com.bottega.erp.sales.api.events

import pl.com.bottega.ddd.DomainEvent

case class OrderCreated(id: Long, clientId: Long) extends DomainEvent
