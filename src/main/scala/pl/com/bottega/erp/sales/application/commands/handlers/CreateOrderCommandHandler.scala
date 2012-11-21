package pl.com.bottega.erp.sales.application.commands.handlers

import pl.com.bottega.cqrs.command.CommandHandler
import pl.com.bottega.erp.sales.application.commands.CreateOrderCommand

class CreateOrderCommandHandler extends CommandHandler[CreateOrderCommand]{

  override def handle(command: CreateOrderCommand) {
  }
}
