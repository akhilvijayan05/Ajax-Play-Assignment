package controllers

import javax.inject.Inject

import play.api.data._
import play.api.data.Forms._
import play.api.data.validation.{Constraint, Invalid, Valid, ValidationError}
import play.api.mvc.{Action, Controller}
import services.UserDetails
import services.Service

/**
  * Created by knoldus on 4/3/17.
  */
class SignupController @Inject() extends Controller {
 /* val Numbers = """^(0|[1-9][0-9]*)$""".r
  val passwordCheckConstraint: Constraint[String] = Constraint("constraints.passwordcheck")({
    plainText =>

      val errors = plainText match {
        case Numbers() => Seq(ValidationError("Password is all numbers"))
        case _ => Nil
      }
      if (errors.isEmpty) {
        Valid
      } else {
        Invalid
      }
  })*/
  //val mobileCheck: Mapping[String] = nonEmptyText(minLength = 10) .verifying("^(0|[1-9][0-9]*)$")
  val userForm = Form(
    mapping(
      "firstname" -> nonEmptyText,
      "middlename" -> text,
      "lastname" -> nonEmptyText,
      "username" -> text,
      "password" -> text,
      "repassword" -> text,
      "mobile" -> longNumber,
      "gender" -> text,
      "age" -> number(min=18 ,max=75),
      "hobbies" -> text
    )(UserDetails.apply)(UserDetails.unapply)
  )

  def default = Action {
    Console.println(Service.list)
  Ok(views.html.signup(""))
  }
  def store = Action {implicit request =>

    //val mobile=userForm.bindFromRequest.get

    //mobile.age.toS
    userForm.bindFromRequest.fold(
      formWithErrors => {

        Ok(views.html.signup("Please check you input !!"))
      },
        value => {
          if(value.mobile.toString.length==10 && value.password.equals(value.repassword))
            {
              def iterate(ls:List[UserDetails]):UserDetails= {
                ls match {
                  case head :: tail if (value.username.equals(head.username)) => head
                  case head :: Nil if (value.username.equals(head.username)) => head
                  case head :: tail=>iterate(tail)
                  case Nil=>null
                }
              }
              val result=iterate(Service.list.toList)
              if(result==null) {
                Service.list.append(value)
                Console.println(Service.list)
                Redirect(routes.ProfileController.default).withSession("username" -> value.username).flashing("success"->"Successfull logged in. Your details are...")
              }
              else
                {
                  Ok(views.html.signup("username already exist !!"))
                }
            }
          else
            {
              Ok(views.html.signup("Please check you input !!"))
            }
        }
    )
  }
}
