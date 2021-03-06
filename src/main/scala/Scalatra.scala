import akka.actor.{Props, ActorSystem}
import org.scalatra._


import javax.servlet.ServletContext
import pl.com.bottega.cqrs.{FakeBus, FakeBusContextConfiguration, ResourcesApp, LeavenSwagger}
import pl.com.bottega.erp.ContextConfiguration
import pl.com.bottega.erp.sales.api.events.{ProductAddedToOrder, OrderCreated}
import pl.com.bottega.erp.sales.application.commands.{CreateOrderCommand, HelloCommand}
import pl.com.bottega.erp.sales.presentation.mongo.initMongoShowcaseContent
import pl.com.bottega.erp.sales.presentation.mongo.projections.OrderProjections
import pl.com.bottega.erp.sales.presentation.ProductFinderFacade
import pl.com.bottega.erp.sales.restfacade.OrderFacade
import pl.com.bottega.erp.sales.application.commands.handlers.HelloCommandHandler
import pl.com.bottega.ddd.infrastructure.akkabus._
/**
 * This is the Scalatra bootstrap file. You can use it to mount servlets or
 * filters. It's also a good place to put initialization code which needs to
 * run at application start (e.g. database configurations), and init params.
 */
class Scalatra extends LifeCycle {

  implicit val swagger = new LeavenSwagger

  override def init(context: ServletContext) {

    // Initialize eventing
    val actorSystem = prepareAkkaEventBus()

    // Initialize DI Config
     lazy implicit val config = new ContextConfiguration(actorSystem) with FakeBusContextConfiguration {

     lazy val commandSender = new FakeBus().registerHandler(classOf[HelloCommand], new HelloCommandHandler).
     registerHandler(classOf[CreateOrderCommand], createOrderHandler)
    }

    initMongoShowcaseContent()

    // Mount one or more servlets
    context.mount(new ProductFinderFacade, "/products")
    context.mount(new ResourcesApp, "/api-docs")
    context.mount(new OrderFacade, "/orders")
  }
}
