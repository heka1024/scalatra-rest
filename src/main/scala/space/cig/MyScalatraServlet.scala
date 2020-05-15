package space.cig
import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success, Try}

import org.scalatra._
import org.scalatra.json._
import org.scalatra.FutureSupport

import org.json4s.{DefaultFormats, Formats}


case class Flower(slug: String, name: String)
object FlowerData {
  var all = List(
    Flower("yellow-tulip", "Yellow Tulip"),
    Flower("red-rose", "Red Rose"),
    Flower("black-rose", "Black Rose")
  )
}

class MyScalatraServlet 
  extends ScalatraServlet 
  with JacksonJsonSupport 
  with FutureSupport {
  protected implicit def executor = ExecutionContext.global
  protected implicit lazy val jsonFormats: Formats = DefaultFormats
  before() {
    contentType = formats("json")
  }
  get("/") { 
    params get "name" match {
      case Some(name) => FlowerData.all filter (_.name.toLowerCase contains name.toLowerCase())
      case None => FlowerData.all 
    }
  }
  get("/a") {
    new AsyncResult { 
      val is = Future {
        Flower("black-rose", "Black Rose")
      }
    }
  }
  get("/b") {
    Future {
        Flower("black-rose", "Black Rose")
      }
  }
}
