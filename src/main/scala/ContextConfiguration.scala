import pl.com.bottega.erp.sales.presentation.mongo.MongoProductFinder

/**
 * Represents configuration of context dependencies.
 */
object ContextConfiguration {

      lazy val productFinder = new MongoProductFinder

}
