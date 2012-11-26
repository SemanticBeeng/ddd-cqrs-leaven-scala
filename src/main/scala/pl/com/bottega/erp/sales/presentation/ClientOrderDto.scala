package pl.com.bottega.erp.sales.presentation

import pl.com.bottega.ddd.domain.sharedkernel.Money
import java.sql.Timestamp

case class ClientOrderDto(orderId: Long, clientId: Long, totalCost: Money, submitDate: Option[Timestamp])