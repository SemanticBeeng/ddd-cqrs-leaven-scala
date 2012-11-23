package pl.com.bottega.erp.sales.domain

import pl.com.bottega.ddd.domain.sharedkernel.Money
import com.novus.salat.annotations.raw.{Persist, Salat}
import pl.com.bottega.ddd.DomainEntity

case class Product(override val id: Long, name: String, productType: ProductType.Value, price: Money) extends DomainEntity(id)
