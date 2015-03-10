import scala.reflect.internal.util.Collections

/**
 * Created by danecek on 2/24/15.
 */
object Main {

  def main(args: Array[String]) {
    val l = List(1, 34, 56, 2, 4, 2, 2, 34, 56, 2, 4, 2, 2, 34, 56, 2, 4, 2, 2, 34, 56, 2, 4, 2, 2, 34, 56, 2, 4, 2, 2)
    for (i <- 1 to 10) // new Range(1, 1, 10))
      mergeSort(l)
    val start = System.currentTimeMillis()
    println(mergeSort(l).mkString(", "))
    println(System.currentTimeMillis() - start)
  }

  def selectSort(data: List[Int]): List[Int] = {
    if (data.length <= 1) data
    else {
      val min = data.min
      val mins = data.filter(_ == min)
      val rest = data.filter(_ > min)
      val sl = selectSort(rest)
      mins ++ sl
    }
  }

  def selectSort2(data: List[Int]): List[Int] = {
    if (data.length <= 1) data
    else {
      val min = data.min
      def pred(n: Int) = n == min
      val mins = data.filter(pred)
      val rest = data.filter(_ > min)
      val sl = selectSort(rest)
      mins ++ sl
    }
  }

  def mergeSort(data: List[Int]): List[Int] = {

    def merge(left: List[Int], right: List[Int]): List[Int] = {
      if (left.isEmpty) right
      else if (right.isEmpty) left
      else {
        val lh = left.head
        val rh = right.head
        if (lh < rh)
          lh :: merge(left.tail, right)
        else
          rh :: merge(left, right.tail)
      }
    }

    val length = data.length
    if (length == 1) data
    else {
      val left = data.take(length / 2)
      val right = data.drop(length / 2)
      merge(mergeSort(left), mergeSort(right))
    }

  }

}
