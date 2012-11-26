package pl.com.bottega.erp.sales.presentation

case class OrderProductDto(orderId: Long, clientId: Long, productId: Long, name: String, quantity: Int, regularCost: Double, effectiveCost: Double)
