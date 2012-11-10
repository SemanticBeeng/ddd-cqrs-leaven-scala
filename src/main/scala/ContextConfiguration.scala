import pl.com.bottega.erp.sales.presentation.mongo.MongoProductFinder

object ContextConfiguration {

      lazy val productFinder = new MongoProductFinder

}
