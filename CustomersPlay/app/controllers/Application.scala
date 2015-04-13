package controllers

import models.{Customer, Customers}
import play.api.data.Form
import play.api.data.Forms.{email, mapping, number}
import play.api.mvc.{Action, Controller, Results}
import play.api.Logger

object Application extends Controller {
  
  def indexView = Action {
      Ok(views.html.index("customers.manager")) // empty parentheses must be here
  }

  def customersView = Action {
    val body = views.html.customers()
    Logger.info(body.getClass.getName)
    Ok(body)
  }

  private val customerForm = Form(
    mapping(
      "username" -> email,
      "age" -> number(min = 1, max = 120))(Customer.apply)(Customer.unapply))

  def addCustomerView = Action {
    Ok(views.html.addCustomer(customerForm))
  }

  def addCustomerSubmit  =
    Action { implicit request =>
      customerForm.bindFromRequest.fold(
        errorsCustomerForm => BadRequest(views.html.addCustomer(errorsCustomerForm)),
        customer => {
          Customers.add(customer)
          Results.Redirect(routes.Application.customersView)
        })
    }
}

