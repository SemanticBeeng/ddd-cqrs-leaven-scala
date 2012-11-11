package pl.com.bottega.erp.sales.presentation.mongo

import com.mongodb.casbah.{MongoCollection, MongoDB, MongoConnection}
import pl.com.bottega.ddd.domain.sharedkernel.Money
import pl.com.bottega.erp.sales.presentation.ProductListItemDto
import pl.com.bottega.erp.sales.domain._
import com.mongodb.DBObject

import com.novus.salat._
import com.novus.salat.global._
import com.mongodb.casbah.Imports._
import pl.com.bottega.erp.sales.domain.Product
import pl.com.bottega.ddd.domain.sharedkernel.Money
import org.slf4j.LoggerFactory

/**
 * Responsible for initializing the database content in Mongo.
 */
object initMongoShowcaseContent extends (() => Unit) {

  def apply() {

    val db = MongoConnection()(MongoConsts.DB)
    val dbReadModel = MongoConnection()(MongoConsts.DB_READMODEL)
    db.dropDatabase()
    dbReadModel.dropDatabase()
    init(db, dbReadModel)
  }

  private def product(id: Int, name: String, pType: ProductType, price: Money): (DBObject, DBObject) = {
    (
      grater[Product].asDBObject(new Product(id, name, pType, price)),
      grater[ProductListItemDto].asDBObject(ProductListItemDto(id, name, price))
      )
  }

  private def init(db: MongoDB, presentationDB: MongoDB) {
    val collection = db(MongoConsts.PRODUCTS)
    val readCollection = presentationDB(MongoConsts.READMODEL_PRODUCTLIST)

    for (i <- 1 to 20) {
      val p1: (DBObject, DBObject) = product(i * 10 + 1, "Electronic Gizmo " + i, Standard, Money(0.99))
      collection += p1._1
      readCollection += p1._2

      val p2 = product(i * 10 + 2, "Cell Phone with 32GB flash memory " + i, Standard, Money(299.99))
      collection += p2._1
      readCollection += p2._2

      val p3 = product(i * 10 + 3, "Software Engineering Audiobook" + i, Food, Money(17.5))
      collection += p3._1
      readCollection += p3._2

      val p4 = product(i * 10 + 4, "PC Game including Zombies Part " + i, Drug, Money(39.89))
      collection += p4._1
      readCollection += p4._2

      val p5 = product(i * 10 + 5, "Tablet with keyboard" + i, Standard, Money(459.99))
      collection += p5._1
      readCollection += p5._2

    }


  }

}
