package models

/**
 * @author danecek
 *
 */
case class Customer(username: String, age: Int)

object Customers {

  var customers = Map[String, Customer]()
  
  add(Customer("smith@gmail.com", 1))
  
  def add(c: Customer) {
    customers = customers + (c.username -> c)
  }

  def all = customers.values
}