package pl.com.bottega.erp.sales.application.commands

import pl.com.bottega.cqrs.command.Command

case class CommandOrderItem(id: Long, count: Int)
case class CreateOrderCommand(newOrderId: Long, productIdsWithCounts: List[CommandOrderItem]) extends Command