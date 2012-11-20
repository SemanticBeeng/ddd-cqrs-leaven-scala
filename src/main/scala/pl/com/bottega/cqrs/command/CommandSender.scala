package pl.com.bottega.cqrs.command

trait CommandSender {
  def send[T <: Command](command: T)
}
