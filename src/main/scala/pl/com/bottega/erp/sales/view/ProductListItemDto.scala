package pl.com.bottega.erp.sales.view

import pl.com.bottega.ddd.domain.sharedkernel.Money


case class ProductListItemDto(productId: Long, displayName: String, price: Money)