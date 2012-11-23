package pl.com.bottega.ddd

object EntityStatus extends Enumeration("Active", "Archive") {
  val Active, Archive = Value
}

// This "var" is on purpose!
abstract class DomainEntity(val id: Long, var entityStatus: EntityStatus.Value = EntityStatus.Active)
