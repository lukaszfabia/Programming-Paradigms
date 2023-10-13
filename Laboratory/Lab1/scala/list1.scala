import scala.annotation.tailrec

def reverse4[A, B, C, D](list: (A, B, C, D)): (D, C, B, A) = {
  (list._4, list._3, list._2, list._1)
}


// def sumProd

def isPerfect(n: Int): Boolean = {
  if (n < 1) then return false
  var sum = 0

  @tailrec
  def divider(i: Int): Int = {
    if (i != 0) {
      if (n % i == 0) {
        sum += i
      }
      divider(i - 1)
    } else {
      sum
    }
  }
  n == divider(n - 1)
}

def sitko(size: Int, list: List[Int]): List[Int] = {
  if (size != 0) {
    if (isPerfect(size)) {
      size :: sitko(size - 1, list)
    } else {
      sitko(size - 1, list)
    }
  } else {
    list
  }
}

sitko(1000, Nil)

def insert[A](list: List[A], element: A, index: Int): List[A] = {
  if (list != Nil) {
    if (index == 0) {
      element :: list
    } else {
      list.head :: insert(list.tail, element, index - 1)
    }
  } else {
    List(element)
  }
}