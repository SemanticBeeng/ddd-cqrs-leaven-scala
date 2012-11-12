package pl.com.bottega.ddd.infrastructure.repo.mongo

import com.mongodb.casbah.MongoConnection
import pl.com.bottega.ddd.infrastructure.repo.GenericRepository
import com.novus.salat._
import com.mongodb.casbah.Imports._
import pl.com.bottega.ddd.DomainEntity
import pl.com.bottega.erp.sales.presentation.mongo.MongoConsts

class GenericMongoRepository[E <: DomainEntity, K](mongoBase: String = MongoConsts.DB)(implicit mot: Manifest[E], mid: Manifest[K], ctx: Context)
 extends GenericRepository[E, K] {

  val graterInstance = grater[E](ctx, mot)

  val mongoCollection = MongoConnection()(mongoBase)(mot.erasure.getSimpleName)

  def findOneById(id: K): Option[E] = {
    val query = MongoDBObject("id" -> id)
    val dbo = mongoCollection.findOne(query)
    dbo.map(graterInstance.asObject(_))

  }
}
