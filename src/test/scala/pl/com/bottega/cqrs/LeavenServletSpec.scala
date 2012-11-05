package pl.com.bottega.cqrs

import org.scalatra.test.specs2.ScalatraSpec

// For more on Specs2, see http://etorreborre.github.com/specs2/guide/org.specs2.guide.QuickStart.html
class LeavenServletSpec extends ScalatraSpec { def is =
  "GET /test on LeavenServlet"                     ^
    "should return status 200"                  ! root200^
                                                end

  addServlet(classOf[LeavenServlet], "/*")

  def root200 = get("/test") {
    status must_== 200
  }
}
