package controllers

import play.api._
import play.api.mvc._
import play.api.db._
import anorm._
import play.api.Play._

object Application extends Controller {

//  DB.withConnection { implicit c =>
    // Create an SQL query
//    val sql = SQL(
//      """
//            DROP TABLE IF EXISTS TEST;
//CREATE TABLE TEST(ID INT PRIMARY KEY, NAME VARCHAR(255));
//INSERT INTO TEST VALUES(1, 'John');
//INSERT INTO TEST VALUES(2, 'Mary');
//""").executeUpdate()
//  }

  def index = Action {
    DB.withConnection { implicit c =>
      val selectCountries = SQL("select * from  TEST")

      // Transform the resulting Stream[Row] to a List[(String,String)]
      val countries = selectCountries()
        .map(row =>
          (row[Int]("id"), row[String]("name"))).toList

      Ok(countries.toString)
    }
  }

}