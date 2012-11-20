package pl.com.bottega.erp.sales.application.commands

import pl.com.bottega.cqrs.command.Command

case class HelloCommand(message: String) extends Command
