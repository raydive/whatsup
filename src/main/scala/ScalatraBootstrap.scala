import javax.servlet.ServletContext

import com.github.raydive._
import org.scalatra._

class ScalatraBootstrap extends LifeCycle {
  override def init(context: ServletContext) {
    context.mount(new Abc, "/abc")
    context.mount(new WhatsUp, "/*")
  }
}
