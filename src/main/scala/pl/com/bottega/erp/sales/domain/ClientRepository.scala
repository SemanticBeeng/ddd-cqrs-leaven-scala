package pl.com.bottega.erp.sales.domain

import pl.com.bottega.ddd.infrastructure.repo.GenericRepository

trait ClientRepository extends GenericRepository[Client, Long]