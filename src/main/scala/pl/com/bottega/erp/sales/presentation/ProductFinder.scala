package pl.com.bottega.erp.sales.presentation

import pl.com.bottega.cqrs.query.PaginatedResult

trait ProductFinder {
  def findProducts(criteria: ProductSearchCriteria): PaginatedResult[ProductListItemDto]
}
