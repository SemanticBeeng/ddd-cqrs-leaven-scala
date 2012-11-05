package pl.com.bottega.cqrs

import org.scalatra._

class LeavenServlet extends ScalatraServlet  {

  get("/test") {
    <html>
      <body>
        <h1>Hello!</h1>
      </body>
    </html>
  }
}
