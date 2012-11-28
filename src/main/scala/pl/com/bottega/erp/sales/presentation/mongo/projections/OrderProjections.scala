package pl.com.bottega.erp.sales.presentation.mongo.projections

import pl.com.bottega.cqrs.EventProjection
import pl.com.bottega.erp.sales.presentation.{OrderProductDto, ProductListItemDto, ClientOrderDto}
import pl.com.bottega.ddd.domain.sharedkernel.Money
import com.mongodb.casbah.MongoConnection
import pl.com.bottega.erp.sales.presentation.mongo.{MongoProductFinder, MongoConsts}
import com.mongodb.casbah.MongoConnection
import com.novus.salat._
import com.novus.salat.global._
import com.mongodb.casbah.Imports._
import pl.com.bottega.erp.sales.infrastructure.repo.mongo.MongoProductRepository
import akka.actor.Actor
import pl.com.bottega.erp.sales.api.events.{ProductAddedToOrder, OrderCreated}

class OrderProjections extends Actor {

  def receive = {

    case (OrderCreated(id, clientId)) =>

      val data = ClientOrderDto(id, clientId, Money(0), None)
      val mongoCollection = MongoConnection()(MongoConsts.DB_READMODEL)(classOf[ClientOrderDto].getSimpleName)
      mongoCollection += grater[ClientOrderDto].asDBObject(data)

    case (ProductAddedToOrder(productId, orderId, productType, price, quantity)) =>
      val productRepo = new MongoProductRepository
      val product = productRepo.findOneById(productId).get
    //OrderProductDto(event.orderId, event.productid, 1l, event.)
  }
}
