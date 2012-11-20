package pl.com.bottega.erp.sales.application.commands.handlers

import pl.com.bottega.cqrs.command.CommandHandler
import pl.com.bottega.erp.sales.application.commands.HelloCommand

class HelloCommandHandler extends CommandHandler[HelloCommand] {

  def handle(command: HelloCommand) {
    println("Hello " + command.message)
  }
}
