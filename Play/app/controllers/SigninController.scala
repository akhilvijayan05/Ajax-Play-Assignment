package controllers

import javax.inject.Inject

import play.api.data._
import play.api.data.Forms._
import play.api.mvc.{Action, Controller}
import services._


class SigninController @Inject() extends Controller {

  val userForm = Form(
    mapping(
      "username" -> text,
      "password" -> text
    )(UserAuthentication.apply)(UserAuthentication.unapply)
  )
  def default = Action {implicit request=>
    Console.println(Service.list)
    Ok(views.html.signin(""))
  }
  def check(username:String,password:String) = Action{implicit request=>
    Console.println("Hi")
    val cacheUser: List[UserDetails] = services.Service.list.toList

    def iterate(ls:List[UserDetails]):UserDetails= {
      ls match {
        case head :: tail if (username.equals(head.username) && password.equals(head.password)) => head
        case head :: Nil if (username.equals(head.username) && password.equals(head.password)) => head
        case head :: tail=>iterate(tail)
        case Nil=>null
      }
    }
    val result=iterate(cacheUser)

    if (result!=null) {

      Redirect(routes.ProfileController.default).withSession("username" -> result.username).flashing("success" -> "Successfull logged in. Your details are...")
    }
    else
      Ok(views.html.signin("Incorrect Username or password !!"))

  }
}
