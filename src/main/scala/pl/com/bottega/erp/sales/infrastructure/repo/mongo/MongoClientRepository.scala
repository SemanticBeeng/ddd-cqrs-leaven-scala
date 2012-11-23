package pl.com.bottega.erp.sales.infrastructure.repo.mongo

import pl.com.bottega.erp.sales.domain.{Client, ClientRepository}
import pl.com.bottega.ddd.infrastructure.repo.mongo.GenericMongoRepository
import com.novus.salat.global._

class MongoClientRepository extends GenericMongoRepository[Client, Long] with ClientRepository