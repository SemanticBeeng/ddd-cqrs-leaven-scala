package pl.com.bottega.erp.sales.presentation

object ProductSearchOrder extends Enumeration("Name", "Price")
{
  val Name, Price = Value
}

case class ProductSearchCriteria(containsText: Option[String], maxPrice: Option[Double], orderBy: ProductSearchOrder.Value =
                                  ProductSearchOrder.Name, ascending: Boolean, pageNumber: Int = 1, itemsPerPage: Int = 10)