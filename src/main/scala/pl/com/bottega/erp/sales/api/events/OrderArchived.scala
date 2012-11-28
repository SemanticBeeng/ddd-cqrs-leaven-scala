package pl.com.bottega.erp.sales.api.events

import pl.com.bottega.ddd.DomainEvent

case class OrderArchived(id: Long) extends DomainEvent
