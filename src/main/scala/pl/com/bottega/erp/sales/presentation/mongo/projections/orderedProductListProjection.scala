package pl.com.bottega.erp.sales.presentation.mongo.projections

import pl.com.bottega.cqrs.EventProjection
import pl.com.bottega.erp.sales.domain.events.{ProductAddedToOrder, OrderCreated}
import pl.com.bottega.erp.sales.presentation.{OrderProductDto, ProductListItemDto, ClientOrderDto}
import pl.com.bottega.ddd.domain.sharedkernel.Money
import com.mongodb.casbah.MongoConnection
import pl.com.bottega.erp.sales.presentation.mongo.{MongoProductFinder, MongoConsts}
import com.mongodb.casbah.MongoConnection
import com.novus.salat._
import com.novus.salat.global._
import com.mongodb.casbah.Imports._
import pl.com.bottega.erp.sales.infrastructure.repo.mongo.MongoProductRepository

object orderedProductListCreationProjection extends EventProjection[OrderCreated] {

  override def apply(event: OrderCreated) {
     val data = ClientOrderDto(event.id, event.clientId, Money(0), None)
     val mongoCollection = MongoConnection()(MongoConsts.DB_READMODEL)(classOf[ClientOrderDto].getSimpleName)
     mongoCollection += grater[ClientOrderDto].asDBObject(data)
  }
}

object addProductToOrderProjection extends EventProjection[ProductAddedToOrder] {

  override def apply(event: ProductAddedToOrder) {
     val productRepo = new MongoProductRepository
     val product = productRepo.findOneById(event.productid).get
    //OrderProductDto(event.orderId, event.productid, 1l, event.)
  }
}
