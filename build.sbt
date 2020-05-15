val ScalatraVersion = "2.7.0"

organization := "snu"

name := "rest"

version := "0.1.0-SNAPSHOT"

scalaVersion := "2.13.1"

resolvers += Classpaths.typesafeReleases

libraryDependencies ++= Seq(
  "org.scalatra" %% "scalatra" % ScalatraVersion,
  "org.scalatra" %% "scalatra-scalatest" % ScalatraVersion % "test",
  "org.scalatra" %% "scalatra-json" % "2.7.0",
  "org.scalatra" %% "scalatra-auth" % "2.7.0",
  "org.json4s" %% "json4s-native" % "3.7.0-M4",
  "org.json4s" %% "json4s-jackson" % "3.7.0-M3",
  "ch.qos.logback" % "logback-classic" % "1.2.3" % "runtime",
  "org.eclipse.jetty" % "jetty-webapp" % "9.4.28.v20200408" % "container",
  "javax.servlet" % "javax.servlet-api" % "3.1.0" % "provided",
  "org.mongodb.scala" %% "mongo-scala-driver" % "2.8.0",
  "org.mongodb.scala" %% "mongo-scala-bson" % "2.8.0",
  "org.mongodb" % "bson" % "3.12.0",
  "org.mongodb" % "mongodb-driver-core" % "3.12.0",
  "org.mongodb" % "mongodb-driver-async" % "3.12.0",
  "com.pauldijou" %% "jwt-json4s-jackson" % "4.2.0"
)

enablePlugins(SbtTwirl)
enablePlugins(ScalatraPlugin)
