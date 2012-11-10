package pl.com.bottega.erp.sales.domain

import pl.com.bottega.ddd.domain.sharedkernel.Money

case class Product(id: Long, name: String, productType: ProductType, price: Money) {


}
