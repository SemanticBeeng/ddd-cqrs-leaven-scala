package pl.com.bottega.erp.sales.presentation

trait ProductFinder {
  def findProducts(): List[ProductListItemDto]
}
