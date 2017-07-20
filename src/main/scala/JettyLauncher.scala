import org.eclipse.jetty.server.Server
import org.eclipse.jetty.webapp.WebAppContext

object JettyLauncher {
  def main(args: Array[String]) {
    val port =
      if (System.getProperty("http.port") != null)
        System.getProperty("http.port").toInt
      else 8080

    val server = new Server(port)
    val context = new WebAppContext()
    context.setContextPath("/")
    context.setResourceBase("src/main/webapp")
    server.setHandler(context)

    server.start
    server.join
  }
}
