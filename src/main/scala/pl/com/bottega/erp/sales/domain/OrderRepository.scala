package pl.com.bottega.erp.sales.domain

import pl.com.bottega.ddd.infrastructure.repo.GenericRepository

trait OrderRepository extends GenericRepository[Order, Long]
