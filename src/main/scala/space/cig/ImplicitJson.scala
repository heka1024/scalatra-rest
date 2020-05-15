package space.cig

import org.json4s.jackson.Serialization.write

object ImplicitJson {
  implicit val formats = org.json4s.DefaultFormats

  implicit class RichMap(m: Map[String, Any]) {
    def toJson = write(m)
  }
}