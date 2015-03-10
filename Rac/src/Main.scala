/**
 * Created by danecek on 3/3/15.
 */
object Main {
  def main(args: Array[String]) {

    import Rac._

    val r = (3 %% 2)
    println(3 %% 2 + 4 )
    println(r.copy())

  }

}

case class Rac( x: Int,  y: Int) {

   def%%(x: Int) = Rac(x, y)
   def +(x2: Int) = Rac(x + x2, y)
}

object Rac {

  implicit def toRac(i: Int) : Rac = new Rac(i, 1)


}


