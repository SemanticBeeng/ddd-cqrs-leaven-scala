package pl.com.bottega.erp.sales.presentation


import grizzled.slf4j.Logger
import mongo.{MongoConsts, MongoProductFinder}
import org.scalatra._
import databinding.JacksonJsonParsing
import org.scalatra.json.{JacksonJsonSupport, JValueResult}
import swagger._
import pl.com.bottega.ddd.domain.sharedkernel.Money
import scala.Some
import org.json4s.{Formats, DefaultFormats}
import pl.com.bottega.erp.sales.domain.ProductRepository
import com.mongodb.casbah.MongoConnection
import com.novus.salat._
import scala.Some
import com.novus.salat.dao.SalatDAO
import com.novus.salat._

import com.novus.salat.global._
import com.novus.salat.annotations._
import com.novus.salat.dao._
import com.mongodb.casbah.Imports._
import com.mongodb.casbah.MongoConnection
import pl.com.bottega.erp.sales.presentation.mongo.MongoConsts
import pl.com.bottega.ddd.infrastructure.repo.mongo.GenericMongoRepository

class ProductFinderFacade(implicit val swagger: Swagger,
                          implicit val env: {
                            val productFinder : ProductFinder
                            val productRepository: ProductRepository })
  extends ScalatraServlet with JacksonJsonParsing with JacksonJsonSupport with JValueResult with SwaggerSupport{

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

  get("/test")
  {
    env.productRepository.findOneById(11)
  }
}
