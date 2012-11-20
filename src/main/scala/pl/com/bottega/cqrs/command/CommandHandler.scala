package pl.com.bottega.cqrs.command

trait CommandHandler[T <: Command] {
  def handle(command: T)
}
