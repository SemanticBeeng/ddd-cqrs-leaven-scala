package pl.com.bottega.cqrs

import org.scalatra.swagger._
import org.scalatra.swagger.{JacksonSwaggerBase, Swagger}
import org.scalatra.ScalatraServlet
import com.fasterxml.jackson.databind._
import org.json4s.jackson.Json4sScalaModule
import org.json4s.{DefaultFormats, Formats}

class ResourcesApp(implicit val swagger: Swagger) extends ScalatraServlet with JacksonSwaggerBase


class LeavenSwagger extends Swagger("1.0", "1")