import scala.annotation.tailrec

def sumAndProd(xs: List[Int]): (Int, Int) = xs.foldLeft(0,1)((acc, x)=>(acc._1+x, acc._2*x))

println(sumAndProd(List(1,2,3,4,5))==(15,120))

def insertionSort[A](function: (A, A) => Boolean, list: List[A]): List[A] = {
  def insert(number: A, sortedList: List[A]): List[A] = {
    sortedList match {
      case Nil => List(number)
      case head :: tail =>
        if function(number, head) then number :: sortedList
        else head :: insert(number, tail)
    }
  }

  list.foldLeft(List.empty[A])((acc, head) => insert(head, acc))
}

println(insertionSort((x: Int, y: Int) => x > y, List(1, 2, 3, 4, 5)))


def mergeSort[A](function: (A, A)=>Boolean, list: List[A]): List[A] = {
    def merge(left: List[A], right: List[A]): List[A] = {
      (left, right) match {
        case (Nil, _) => right
        case (_, Nil) => left
        case (leftHead :: leftTail, rightHead :: rightTail) if function(leftHead, rightHead) => leftHead :: merge(leftTail, right)
        case _ => rightHead :: merge(left, rightTail)
      }
    }
    val n = list.length / 2
    if (n <=1 ) list
    else {
      val (left, right) = list.splitAt(n)
      merge(mergeSort(function, left), mergeSort(function, right))
    }
}

println(mergeSort((x: Int, y: Int) => x > y, List(1, 2, 3, 4, 5)))

