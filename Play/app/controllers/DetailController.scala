package controllers

import javax.inject.Inject

import play.api.mvc.{Action, Controller}

/**
  * Created by knoldus on 11/3/17.
  */
class DetailController @Inject() extends Controller {

  def detail = Action {implicit request=>

    Ok(views.html.detail())
  }
}
