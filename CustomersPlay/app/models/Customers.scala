package models

import play.api._
import play.api.mvc._
import play.api.db._
import anorm._
import play.api.Play._

import play.api.db.DB

/**
 * @author danecek
 *
 *
 */
case class Customer(username: String, age: Int)

object Customers {

  //  var customers = Map[String, Customer]()
  //
  //  add(Customer("smith@gmail.com", 1))

  //  def add(c: Customer) {
  //    customers = customers + (c.username -> c)
  //  }
  def add(c: Customer) {
    DB.withConnection { implicit connection =>
      val insertCustomer = SQL("INSERT INTO CUSTOMERS VALUES({username}, {age})").on("username" -> c.username,
        "age" -> c.age)
      insertCustomer.executeUpdate() //(connection)
    }

  }

  def all: List[Customer] = {
    DB.withConnection { implicit connection =>
      val selCustomers = SQL("select * from  CUSTOMERS")
      val qresult = selCustomers.executeQuery() //(connection)
      qresult() //(connection)
        .map(row =>
          Customer(row[String]("username"), row[Int]("age"))).toList
    }
  }

  def find(username: String): Customer = {
    DB.withConnection { implicit connection =>
      val selCustomers = SQL("select * from  CUSTOMERS WHERE username = {username}").on("username" -> username)
      val row = selCustomers.single() //(connection)
      Customer(row[String]("username"), row[Int]("age"))
    }
  }
}