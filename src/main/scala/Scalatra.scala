import org.scalatra._
import javax.servlet.ServletContext
import pl.com.bottega.cqrs.LeavenServlet
import pl.com.bottega.erp.sales.view.ProductFinder

/**
 * This is the Scalatra bootstrap file. You can use it to mount servlets or
 * filters. It's also a good place to put initialization code which needs to
 * run at application start (e.g. database configurations), and init params.
 */
class Scalatra extends LifeCycle {
  override def init(context: ServletContext) {

    // Mount one or more servlets
    context.mount(new LeavenServlet, "/*")
    context.mount(new ProductFinder, "/p/*")
  }
}
