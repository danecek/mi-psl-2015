package controllers

import play.api.mvc.{ Controller, Action }
import play.api.Logger

object Application2 extends Controller {
  def hello = Action {
    Logger.info("Hello world response");
    Ok("Hello world").as(HTML) // response
  }
}