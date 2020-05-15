package space.cig
import pdi.jwt.{Jwt, JwtAlgorithm, JwtHeader, JwtClaim, JwtOptions}
import org.json4s._
import org.json4s.JsonDSL._
import org.json4s.jackson.JsonMethods._
import org.json4s.jackson.Serialization.write
import space.cig.User


trait AuthHelper {
  implicit val formats = org.json4s.DefaultFormats
  var db = List(
    User("heka1024", "password"),
    User("foo", "bar")
  )

  val options = Map(
    "nbf" -> 1431520421
  )
  val key = "secretKey"
  val algo = JwtAlgorithm.HS256
  
  def addUser(user: User) = {
    val tried = util.Try {
      db = user :: db
      gen(user)
    } 
    tried.toOption 
  }
  
  def gen(user: User): Option[String] = {
    if (db contains user) {
      val payload = write(options ++ user.toMap)
      Some( Jwt.encode(payload, key, algo) )
    } else {
      None
    }
  }
  def validate(token: Option[String]) = token flatMap { t =>
    Jwt.decodeRawAll(t, key, Seq(algo))
      .toOption
      .collect { case (_, v, _) => v }
  }
}