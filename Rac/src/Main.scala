/**
 * Created by danecek on 3/3/15.
 */
object Main {
  def main(args: Array[String]) {

    import Rational._

    val r = Rational(30, 2)

    println(r)
    println(2 / r)
    println(r / Rational(3, 4))
    println(r / 3)
    println(r == Rational(15, 1))
    val rc = r.copy()
    println(rc == r)
    val rc2 = r.copy(n = 10)
    println(rc2 == r)

    //**********************************************
    println(30 ## 2 / 3 ## 4)
    println(30 ## 2 * 3 ## 4)
  }

}
