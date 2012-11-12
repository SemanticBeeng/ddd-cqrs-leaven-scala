package pl.com.bottega.erp.sales.domain


object ProductType extends Enumeration("Standard", "Food", "Drug") {

  val Standard, Food, Drug = Value
}

