package pl.com.bottega.erp.sales.domain

import pl.com.bottega.ddd.DomainEntity

case class Client(override val id: Long) extends DomainEntity(id: Long)