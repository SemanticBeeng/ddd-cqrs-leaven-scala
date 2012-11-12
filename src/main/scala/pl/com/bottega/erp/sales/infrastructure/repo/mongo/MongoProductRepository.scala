package pl.com.bottega.erp.sales.infrastructure.repo.mongo

import pl.com.bottega.erp.sales.domain.{ProductId, ProductRepository, Product}
import com.novus.salat.dao.SalatDAO
import com.novus.salat._
import com.novus.salat.global._
import com.novus.salat.annotations._
import com.novus.salat.dao._
import com.mongodb.casbah.Imports._
import com.mongodb.casbah.MongoConnection
import pl.com.bottega.erp.sales.presentation.mongo.MongoConsts
import pl.com.bottega.ddd.infrastructure.repo.mongo.GenericMongoRepository

class MongoProductRepository extends GenericMongoRepository[Product, Long](MongoConsts.DB) with ProductRepository
