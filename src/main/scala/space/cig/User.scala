package space.cig

case class User(username: String, password: String) {
  def toJson = s"""{"username": "${username}"}"""
  def toMap = Map("username" -> username)
}