package controllers

import play.api.data
import play.api.mvc.{Action, Controller}
import play.api.routing.JavaScriptReverseRouter

/**
  * Created by knoldus on 9/3/17.
  */
class JavascriptRoute extends Controller {

  def javascriptRoutes = Action {implicit request =>

    Ok(
      JavaScriptReverseRouter("jsRoutes")(
        routes.javascript.FrontController.front,
        routes.javascript.FrontController.signin,
        routes.javascript.FrontController.signup,
        routes.javascript.DetailController.detail,
        routes.javascript.SigninController.check,
        routes.javascript.SignupController.store

      )
    ).as("text/javascript")
  }
}