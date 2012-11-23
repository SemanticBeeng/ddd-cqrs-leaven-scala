package pl.com.bottega.erp.sales.domain.events

import pl.com.bottega.ddd.DomainEvent

case class OrderArchived(id: Long) extends DomainEvent
