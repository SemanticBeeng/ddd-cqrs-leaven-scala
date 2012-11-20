package pl.com.bottega.erp.sales.restfacade

import org.scalatra.swagger.{SwaggerSupport, Swagger}
import pl.com.bottega.erp.sales.presentation.ProductFinder
import org.scalatra.ScalatraServlet
import org.scalatra.databinding.JacksonJsonParsing
import org.scalatra.json.{JValueResult, JacksonJsonSupport}
import org.json4s.{DefaultFormats, Formats}
import pl.com.bottega.erp.sales.application.commands.HelloCommand
import pl.com.bottega.cqrs.command.CommandSender

class OrderFacade (implicit val swagger: Swagger, implicit val env: { val commandSender: CommandSender})
  extends ScalatraServlet with JacksonJsonParsing with JacksonJsonSupport with JValueResult with SwaggerSupport {

  // implicit value for json serialization format
  protected implicit val jsonFormats: Formats = DefaultFormats
  override protected val applicationName = Some("DDD CQRS Leaven")
  protected val applicationDescription = "DDD CQRS Leaven"

  before() {
    contentType = formats("json")
  }

  post("/create")
  {
    // TODO implementation
  }

  post("/hello")
  {
    val command = HelloCommand("command")
    env.commandSender.send(command)
  }

}

