package pl.com.bottega.cqrs

import command.{CommandHandler, Command, CommandSender}


class FakeBus(private val handlers: Map[Class[_ <: Command], CommandHandler[_ <: Command]] = Map.empty) extends CommandSender {

  override def send[T <: Command](command: T) {
    val handler: CommandHandler[T] = handlers.get(command.getClass).get.asInstanceOf[CommandHandler[T]]
    handler.handle(command)
  }

  def  registerHandler[T <: Command](command: Class[T], handler: CommandHandler[T]): FakeBus =
    new FakeBus(handlers ++ Map(command -> handler))
}
