package pl.com.bottega.cqrs.query

import java.lang.Math

/**
 * A wrapper for a paginated collection of DTOs. Adds information about page number,
 * page count, etc.
 */
case class PaginatedResult[D](items: List[D], pageSize: Int, pageNumber: Int,
                              pageCount: Int, totalItemsCount: Int)

object PaginatedResult {

  def apply[D](items: List[D], pageNumber: Int, pageSize: Int, totalItemsCount: Int) = {
    new PaginatedResult(items, pageSize, pageNumber, countPages(totalItemsCount, pageSize), totalItemsCount)
  }

  private def countPages(totalItemsCount: Int, pageSize: Int): Int = {
    Math.ceil(totalItemsCount.toDouble / pageSize).toInt
  }

}
