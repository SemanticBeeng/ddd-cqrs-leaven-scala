package pl.com.bottega.erp.sales.presentation


import mongo.{MongoConsts, MongoProductFinder}
import org.scalatra._
import databinding.{FieldDescriptor, JsonCommand, ModelCommand, JacksonJsonParsing}
import org.scalatra.json.{JacksonJsonSupport, JValueResult}
import swagger._
import scala.Some
import org.json4s.{Formats, DefaultFormats}
import com.novus.salat._
import com.novus.salat.global._

class ProductFinderFacade(implicit val swagger: Swagger,
                          implicit val env: {
                            val productFinder: ProductFinder})
  extends ScalatraServlet with JacksonJsonParsing with JacksonJsonSupport with JValueResult with SwaggerSupport {

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

    // Is there any way to extract this automatically to a case class?

    val containsText = params get "containsText"
    val maxPrice = params get "maxPrice" map (_.toDouble)
    val orderBy = (params get "orderBy" map (mapSearchOrder(_))).getOrElse(ProductSearchOrder.Name)
    val ascending = params get "ascending" map (_.toBoolean) getOrElse true
    val pageNumber = params get "pageNumber" map (_.toInt) getOrElse 1
    val itemsPerPage = params get "itemsPerPage" map (_.toInt) getOrElse (10)

    val criteria = ProductSearchCriteria(containsText, maxPrice, orderBy, ascending, pageNumber, itemsPerPage)
    env.productFinder.findProducts(criteria)
  }

  private def mapSearchOrder(value: String): ProductSearchOrder.Value = {
    ProductSearchOrder.values.find(value2 => value2.toString == value)
      .getOrElse(ProductSearchOrder.Name)
  }

  get("/test") {

  }
}
