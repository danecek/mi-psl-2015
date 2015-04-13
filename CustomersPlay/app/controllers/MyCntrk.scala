package controllers

import play.api.mvc.{Controller, Result, Action, ResponseHeader}
import play.api.libs.iteratee.Enumerator

object MyCntrk extends Controller {
   def index = Action {
   Result(header = ResponseHeader(200, 
                         Map(CONTENT_TYPE -> "text/plain")),
          body = Enumerator("Hello world!".getBytes())
  )
 }

}