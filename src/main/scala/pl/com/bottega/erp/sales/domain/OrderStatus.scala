package pl.com.bottega.erp.sales.domain

object OrderStatus extends Enumeration("Draft", "Submitted", "Archived") {
  val Draft, Submitted, Archived = Value
}
