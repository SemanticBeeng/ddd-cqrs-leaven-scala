package pl.com.bottega.erp.sales.infrastructure.repo.mongo

import pl.com.bottega.ddd.infrastructure.repo.mongo.GenericMongoRepository
import pl.com.bottega.erp.sales.domain.{Order, OrderRepository}
import com.novus.salat.global._

class MongoOrderRepository extends GenericMongoRepository[Order, Long] with OrderRepository {

}
