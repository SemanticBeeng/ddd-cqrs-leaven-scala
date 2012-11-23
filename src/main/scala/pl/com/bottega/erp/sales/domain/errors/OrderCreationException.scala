package pl.com.bottega.erp.sales.domain.errors

case class OrderCreationException(message: String, clientId: Long) extends RuntimeException(message)