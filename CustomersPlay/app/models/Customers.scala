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
      insertCustomer.executeUpdate//()//(connection)
    }
  }

  def all: List[Customer] = {
    DB.withConnection { implicit connection =>
      val selCustomers = SQL("SELECT * FROM  CUSTOMERS")
      val qresult = selCustomers.executeQuery// ()(connection)
      qresult()//.apply()(connection)
        .map(row =>
          Customer(row[String]("username")//(Column.columnToString)
              , row[Int]("age"))).toList
    }
  }

  def find(username: String): Customer = {
    DB.withConnection { implicit connection =>
      val selCustomers = SQL("select * from  CUSTOMERS WHERE username = {username}").on("username" -> username)
      val row = selCustomers.single() //(connection)
      Customer(row[String]("username"), row[Int]("age"))
    }
  }

  def delete(username: String) {
    DB.withConnection { implicit connection =>
      val selCustomers = SQL("DELETE FROM CUSTOMERS WHERE USERNAME = {username}").on("username" -> username)
      selCustomers.executeUpdate      
    }
  }
  def update(customer: Customer) {
    DB.withConnection { implicit connection =>
      val selCustomers = SQL("UPDATE CUSTOMERS SET AGE={age} WHERE USERNAME= {username}").
      on("username" -> customer.username, "age"->customer.age)
      selCustomers.executeUpdate      
    }
  }
}