/**
 * Created by danecek on 3/10/15.
 */
class Rational(nn: Int, dd: Int) {


  assert(nn >= 0, "Negative nominator")
  assert(dd >= 1, "Nonpositive denominater")

  private def gcd2(n: Int, d: Int): Int = {
    if (n == d) n
    else
    if (n > d) {
      val nd = n % d
      if (nd == 0) d
      else gcd2(nd, d)
    } else gcd2(d, n)

  }

  val n: Int = nn / gcd2(nn, dd)
  val d: Int = dd / gcd2(nn, dd)


  def *(that: Rational) = new Rational(n * that.n, d * that.d)

  def /(that: Rational) = new Rational(n * that.d, d * that.n)

  def ##(i: Int) = this / i

  def +(i: Int) = new Rational(n + d * i, d)

  def -(i: Int) = new Rational(n + d * i, d)

  override def toString = n.toString + "##" + d

  def copy(n: Int = this.n, d: Int = this.d) = new Rational(n, d);

  override def hashCode = n * d

  override def equals(that: Any): Boolean =
    if (!that.isInstanceOf[Rational]) false;
    else {
      val r = that.asInstanceOf[Rational]
      this.n == r.n && this.d == r.d
    }
}

object Rational {

  implicit def toRac(i: Int): Rational = new Rational(i, 1)

  def apply(n: Int, d: Int) = new Rational(n, d)


}
