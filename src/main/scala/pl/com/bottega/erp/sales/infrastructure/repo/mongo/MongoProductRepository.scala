package pl.com.bottega.erp.sales.infrastructure.repo.mongo

import pl.com.bottega.erp.sales.domain.{ProductRepository, Product}
import pl.com.bottega.ddd.infrastructure.repo.mongo.GenericMongoRepository
import com.novus.salat.global._

class MongoProductRepository extends GenericMongoRepository[Product, Long] with ProductRepository
