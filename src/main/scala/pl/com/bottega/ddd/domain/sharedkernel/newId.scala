package pl.com.bottega.ddd.domain.sharedkernel

import util.Random

object newId extends (() => Long) {

  override def apply() = Random.nextLong()
}
