package controllers

import models.{ Customer, Customers }
import play.api.data.Form
import play.api.data.Forms.{ email, mapping, number }
import play.api.mvc.{ Action, Controller, Results }
import play.api.Logger
import play.api.db.DB
import anorm._
import play.api.Play.current

object Application extends Controller {

  DB.withConnection { implicit connection =>
    try {
      SQL(
        //   "DROP TABLE IF EXISTS CUSTOMERS;"+
        "CREATE TABLE CUSTOMERS(USERNAME VARCHAR(255) PRIMARY KEY, AGE INT);"
          + "INSERT INTO CUSTOMERS VALUES('john@fit.cvut.cz', 1);"
          + "INSERT INTO CUSTOMERS VALUES('mary@fit.cvut.cz', 2);").executeUpdate()
    } catch { case _ : Exception => () }

  }(play.api.Play.current)

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

  def deleteCustomerView(username : String) = Action {
    val customer = Customers.find(username)
    Ok(views.html.deleteCustomer(customer))
  }
  
  def addCustomerSubmit =
    Action { implicit request =>
      customerForm.bindFromRequest.fold(
        errorsCustomerForm => BadRequest(views.html.addCustomer(errorsCustomerForm)),
        customer => {
          Customers.add(customer)
          Results.Redirect(routes.Application.customersView)
        })
    }
  
   def deleteCustomerSubmit(username : String) =
    Action {
     Customers.delete(customer)
     Results.Redirect(routes.Application.customersView)
    }
}

