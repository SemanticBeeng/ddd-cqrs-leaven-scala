import pl.com.bottega.erp.sales.infrastructure.repo.mongo.MongoProductRepository
import pl.com.bottega.erp.sales.presentation.mongo.MongoProductFinder

/**
 * Represents configuration of context dependencies.
 */
object ContextConfiguration {

      lazy val productFinder = new MongoProductFinder
      lazy val productRepository = new MongoProductRepository
}
