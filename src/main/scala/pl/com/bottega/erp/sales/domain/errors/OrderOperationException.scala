package pl.com.bottega.erp.sales.domain.errors

case class OrderOperationException(message: String, clientId: Long, orderId: Long) extends RuntimeException(message)