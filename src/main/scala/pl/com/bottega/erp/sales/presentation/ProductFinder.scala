package pl.com.bottega.erp.sales.presentation

import mongo.MongoConsts
import pl.com.bottega.cqrs.query.PaginatedResult
import com.mongodb.casbah.MongoConnection

trait ProductFinder {
  def findProducts(criteria: ProductSearchCriteria): PaginatedResult[ProductListItemDto]
  val mongoCollection = MongoConnection()(MongoConsts.DB_READMODEL)(classOf[ProductListItemDto].getSimpleName)

}
