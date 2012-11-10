import org.scalatra._


import javax.servlet.ServletContext
import pl.com.bottega.cqrs.{ResourcesApp, LeavenSwagger}
import pl.com.bottega.erp.sales.presentation.mongo.initMongoShowcaseContent
import pl.com.bottega.erp.sales.presentation.ProductFinderFacade

/**
 * This is the Scalatra bootstrap file. You can use it to mount servlets or
 * filters. It's also a good place to put initialization code which needs to
 * run at application start (e.g. database configurations), and init params.
 */
class Scalatra extends LifeCycle {

  implicit val swagger = new LeavenSwagger

  override def init(context: ServletContext) {

    // Intialize DI Config
    implicit val config = ContextConfiguration
    initMongoShowcaseContent()

    // Mount one or more servlets
    context.mount(new ProductFinderFacade, "/products")
    context.mount(new ResourcesApp, "/api-docs")


  }
}
