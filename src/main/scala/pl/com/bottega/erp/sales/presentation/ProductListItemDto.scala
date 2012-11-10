package pl.com.bottega.erp.sales.presentation

import pl.com.bottega.ddd.domain.sharedkernel.Money


case class ProductListItemDto(id: Long, name: String, price: Money)