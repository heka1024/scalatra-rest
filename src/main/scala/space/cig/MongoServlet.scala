package space.cig

import scala.util.{Try, Success, Failure}
import org.scalatra._
import org.mongodb.scala._
import org.json4s.jackson.JsonMethods._

import space.cig.AuthHelper
import space.cig.User
import space.cig.ImplicitJson._

class MongoServlet(collection: MongoCollection[Document]) 
  extends MongoHelper 
  with AuthHelper {

  /**
   * Insert a new object into the database. You can use the following from your console to try it out:
   * curl -i -H "Accept: application/json" -X POST -d "key=super&value=duper" http://localhost:8080/insert
   */
  post("/insert") {
    scala.util.Try { 
      parse(request.body).extract[User] 
    } foreach { user =>
      val pnew = Document(
        "username" -> user.username,
        "password" -> user.password,
      )
      collection insertOne pnew foreach println
    }
  }
    
  post("/login") {
    val reqUser = scala.util.Try { 
      parse(request.body).extract[User] 
    }.toOption
    reqUser flatMap gen match {
      case Some(token) => {
        Map(
          "message" -> "login success",
          "token" -> token
        ).toJson
      }
      case None => Map("message" -> "login failed").toJson
    }
  }

  /**
   * Retrieve everything in the MongoDb collection we're currently using.
   */
  get("/") {
    collection.find().results().map(doc => doc.toJson)
  }

  /**
   * Query for the first object which matches the values given. If you copy/pasted the insert example above,
   * try http://localhost:8080/query/super/duper in your browser.
   */
  get("/query/:key/:value") {
    val q = Document(params("key") -> params("value"))
    collection.find(q).first().headResult().toJson()
  }

}