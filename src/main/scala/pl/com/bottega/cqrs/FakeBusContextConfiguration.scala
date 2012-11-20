package pl.com.bottega.cqrs

import command.CommandSender


trait FakeBusContextConfiguration {

  val commandSender: CommandSender

}
