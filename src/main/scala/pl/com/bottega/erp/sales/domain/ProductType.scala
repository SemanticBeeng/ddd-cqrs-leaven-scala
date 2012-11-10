package pl.com.bottega.erp.sales.domain

sealed  abstract class ProductType
case object Drug extends ProductType
case object Food extends ProductType
case object Standard extends ProductType

