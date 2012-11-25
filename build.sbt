organization := "pl.com.bottega"

name := "cqrs-web-scala"

version := "0.1.0-SNAPSHOT"

scalaVersion := "2.9.2"

seq(webSettings :_*)

classpathTypes ~= (_ + "orbit")

libraryDependencies ++= Seq(
  "org.scalatra" % "scalatra-json" % "2.2.0-RC1",
    "tv.cntt" %% "sclasner" % "1.1",
  "org.scalatra" % "scalatra-data-binding" % "2.2.0-RC1",
  "org.json4s" % "json4s-jackson_2.9.1-1" % "3.0.0",
  "com.wordnik" % "swagger-core_2.9.1" % "1.1.0",
  "com.novus" %% "salat" % "1.9.1",
  "org.scalatra" % "scalatra-swagger" % "2.2.0-RC1",
  "org.scalatra" % "scalatra" % "2.1.1",
  "org.scalatra" % "scalatra-scalate" % "2.1.1",
  "org.scalatra" % "scalatra-specs2" % "2.1.1" % "test",
  "ch.qos.logback" % "logback-classic" % "1.0.6" % "runtime",
  "org.eclipse.jetty" % "jetty-webapp" % "8.1.7.v20120910" % "container",
  "org.eclipse.jetty.orbit" % "javax.servlet" % "3.0.0.v201112011016" % "container;provided;test" artifacts (Artifact("javax.servlet", "jar", "jar"))
)
