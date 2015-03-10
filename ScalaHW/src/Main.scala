
/**
 * Created by danecek on 2/17/15.
 */
object Main {

  def main(args: Array[String]): Unit = {
    val now = java.lang.System.currentTimeMillis()
    println(selectSort2(List(1, 4, 2, 6, 22, 56)).mkString("[", ", ", "]"))
    println(java.lang.System.currentTimeMillis() - now)
  }


  def selectSort(data: List[Int]): List[Int] =


    if (data.length <= 1) data
    else {
      val min = data.min
      val fdata = data.filter(_ > min)
      val sdata = selectSort(fdata)
      min +: sdata // sdata.+:(min)
      //   sdata :+ min

    }


  def selectSort2(data: List[Int]): List[Int] =

    if (data.length <= 1) data
    else {
      val min = data.min

      def m(n: Int) = n > min

      val fdata = data.filter(m)
      val sdata = selectSort(fdata)
      min +: sdata // sdata.+:(min)
      //   sdata :+ min
    }

}
