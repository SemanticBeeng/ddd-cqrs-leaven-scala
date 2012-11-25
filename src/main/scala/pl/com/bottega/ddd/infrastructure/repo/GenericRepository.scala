package pl.com.bottega.ddd.infrastructure.repo

import pl.com.bottega.ddd.DomainEntity

trait GenericRepository[E <: DomainEntity, K <: Any] {

  def findOneById(id: K): Option[E]
  def save(entity: E)
}
