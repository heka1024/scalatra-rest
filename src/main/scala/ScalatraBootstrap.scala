import space.cig._
import org.scalatra._
import org.mongodb.scala.MongoClient
import javax.servlet.ServletContext

class ScalatraBootstrap extends LifeCycle {
  override def init(context: ServletContext) {
    val mongoClient =  MongoClient()
    val mongoColl = mongoClient.getDatabase("mydb").getCollection("test_data")
    
    context.mount(new MyScalatraServlet, "/future/*")
    context.mount(new MongoServlet(mongoColl), "/mongo/*")
  }
}
