package pl.com.bottega.erp.sales.presentation.mongo

import pl.com.bottega.erp.sales.presentation.{ProductSearchOrder, ProductSearchCriteria, ProductListItemDto, ProductFinder}
import com.mongodb.casbah.MongoConnection
import com.novus.salat._
import com.novus.salat.global._
import com.mongodb.casbah.Imports._
import pl.com.bottega.cqrs.query.PaginatedResult

class MongoProductFinder extends ProductFinder {

  private def queryForText(text: String): DBObject = {
    val regex = (".*" + text + ".*").r
    MongoDBObject("name" -> regex)
  }

  private def preparePriceCriteria(double: Double): DBObject = {
    "price.doubleValue" $lte double
  }

  private def prepareSorting(orderBy: ProductSearchOrder.Value, ascending: Boolean): DBObject = {
    val fieldName = orderBy match {
      case ProductSearchOrder.Name => "name"
      case ProductSearchOrder.Price => "price"
      case _=> throw new UnsupportedOperationException("Unknown sorting order type " + orderBy)
    }
    val direction = if (ascending) 1 else -1

    MongoDBObject(fieldName -> direction)
  }

  override def findProducts(criteria: ProductSearchCriteria) = {

    val mongoCollection = MongoConnection()(MongoConsts.DB_READMODEL)(classOf[ProductListItemDto].getSimpleName)
    val queryAll = DBObject()
    val queryWithName = criteria.containsText.map(queryForText(_)) getOrElse queryAll
    val queryWithPrice = criteria.maxPrice.map(queryWithName ++ preparePriceCriteria(_)) getOrElse queryWithName
    val pageNumber = if (criteria.pageNumber > 0) criteria.pageNumber else 1
    val pageSize = if (criteria.itemsPerPage > 0) criteria.itemsPerPage else 10
    val finalQuery = mongoCollection.find(queryWithPrice).sort(prepareSorting(criteria.orderBy, criteria.ascending))
    val allResultCount = finalQuery.count
    val result = finalQuery.skip((pageNumber - 1) * pageSize).limit(pageSize)
    val resultList = result.map(dbo => grater[ProductListItemDto].asObject(dbo)).toList
    PaginatedResult(resultList, pageNumber, pageSize, allResultCount)
  }
}
