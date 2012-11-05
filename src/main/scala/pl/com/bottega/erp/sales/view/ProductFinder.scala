package pl.com.bottega.erp.sales.view


import grizzled.slf4j.Logger
import org.scalatra._
import scalate.ScalateSupport
import net.liftweb.json.compact
import net.liftweb.json.render
import net.liftweb.json.JsonDSL._
import net.liftweb.json.Serialization.{read, write}
import net.liftweb.json.Serialization
import net.liftweb.json.NoTypeHints
import org.scalatra.Ok
import org.scalatra.NotFound
import org.scalatra.Created
import scala.collection.immutable.Map
import pl.com.bottega.ddd.domain.sharedkernel.Money

class ProductFinder extends ScalatraServlet {

  val logger = Logger(classOf[ProductFinder])

  // repo stores our items
  //val itemRepo = new ItemRepository
  //val bidRepo = new BidRepository

  // implicit value for json serialization format
  implicit val formats = Serialization.formats(NoTypeHints)

  get("/products/") {

    // set the result content type
    contentType = "application/vnd.smartbid.item+json"
    val m = Money(BigDecimal(3.0))
    Ok(write(ProductListItemDto(1l,"product",m)))
  }

}
