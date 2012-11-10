package pl.com.bottega.erp.sales.presentation.mongo

import pl.com.bottega.erp.sales.presentation.{ProductListItemDto, ProductFinder}
import pl.com.bottega.ddd.domain.sharedkernel.Money
import com.mongodb.casbah.{MongoConnection, MongoDB}
import com.novus.salat._
import com.novus.salat.global._
import com.mongodb.casbah.Imports._

class MongoProductFinder extends ProductFinder {

  override def findProducts() = {
    val mongoCollection = MongoConnection()(MongoConsts.DB_READMODEL)(MongoConsts.READMODEL_PRODUCTLIST)
    val result = mongoCollection.find()
    result.map(dbo => grater[ProductListItemDto].asObject(dbo)).toList
  }
}
