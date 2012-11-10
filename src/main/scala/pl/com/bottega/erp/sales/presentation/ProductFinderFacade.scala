package pl.com.bottega.erp.sales.presentation


import grizzled.slf4j.Logger
import mongo.MongoProductFinder
import org.scalatra._
import json.{JacksonJsonSupport, JValueResult}
import swagger._
import pl.com.bottega.ddd.domain.sharedkernel.Money
import scala.Some
import org.json4s.{Formats, DefaultFormats}

class ProductFinderFacade(implicit val swagger: Swagger,
                          implicit val env: {val productFinder : ProductFinder})
  extends ScalatraServlet with JacksonJsonSupport with JValueResult with SwaggerSupport{

  // implicit value for json serialization format
  protected implicit val jsonFormats: Formats = DefaultFormats
  override protected val applicationName = Some("DDD CQRS Leaven")
  protected val applicationDescription = "DDD CQRS Leaven"

  before() {
    contentType = formats("json")
  }
  get("/",
  summary("Gets products for criteria"),
    nickname("products"),
    responseClass("List[ProductListItemDto]"),
    notes("Additional notes")) {
    env.productFinder.findProducts()
  }

}
