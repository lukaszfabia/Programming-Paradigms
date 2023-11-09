import scala.annotation.tailrec

def takeNth1[A](f: Int => Boolean)(list: List[A]): List[A] = {
  def aux[B](f: Int => Boolean, list1: List[B], index: Int): List[B] = {
    (list1, f(index)) match
      case (head :: tail, true) => head :: aux(f, tail, index + 1)
      case (_ :: tail, _) => aux(f, tail, index + 1)
      case (Nil, _) => Nil
  }

  aux(f, list, 0)
}

def substituteIf[A](list1: List[A])(list2: List[A], element: A): List[A] = {
  @tailrec
  def contains[B](searched: B, list2: List[B]): Boolean = {
    list2 match
      case head :: tail =>
        if head == searched then true
        else contains(searched, tail)
      case _ => false
  }

  def set[B](list1: List[B], list2: List[B], element: B): List[B] = {
    list1 match
      case head :: tail =>
        if contains(head, list2) then element :: set(tail, list2, element)
        else head :: set(tail, list2, element)
      case _ => Nil
  }

  set(list1, list2, element)
}


substituteIf(List(1, 2, 3, 4, 5))(List(2, 4), 0) == List(1, 0, 3, 0, 5)
substituteIf(List())(List(2, 4), 0) == Nil
substituteIf(List(1, 1, 1, 1, 1))(List(2, 4), -1) == List(1, 1, 1, 1, 1)
substituteIf(List(1, 1, 1, 1, 1))(List(2, 1), 99) == List(99, 99, 99, 99, 99)
substituteIf(List(1, 2, 3, 4, 5))(Nil, 100) == List(1, 2, 3, 4, 5)
substituteIf(List(1, 2, 3, 4, 5))(List(1, 1), 99) == List(99, 2, 3, 4, 5)


@tailrec
def reverseList[B](list: List[B], output: List[B]): List[B] = {
  list match
    case head :: tail => reverseList(tail, head :: output)
    case _ => output
}

def isPalindrome[A](list: List[A]): Boolean = {
  list == reverseList(list, Nil)
}

//def factorial(n: Int): Int = fold

def filter[A](predicate: A => Boolean)(list: List[A]): List[A] = {
  list match
    case head :: tail =>
      if predicate(head) then head :: filter(predicate)(tail)
      else filter(predicate)(tail)
    case _ => Nil
}

def oddNumbers: Int => Boolean = _ % 2 == 0

filter(oddNumbers)(List(1, 2, 3, 4, 5, 6))

def sumSmallerThan100(list: List[Int]): Int = {
  @tailrec
  def acc(list1: List[Int], result: Int): Int = {
    list1 match
      case Nil => result
      case head :: tail =>
        if head > 100 then acc(tail, result)
        else acc(tail, result + head)
  }

  acc(list, 0)
}


// list jest podlistą list1 : list c list1
@tailrec
def isListIncludesInOther[A](list: List[A], list1: List[A]): Boolean = {
  (list, list1) match
    case (Nil, _) => true
    case (_, Nil) => false
    case (head :: tail, head1 :: tail1) =>
      if head == head1 then isListIncludesInOther(tail, tail1)
      else false
}

// [12,3,4,12] [12,12,24,3]
def sumOfBothHeads(list: List[Int], list1: List[Int]): List[Int] = {
  (list, list1) match
    case (Nil, Nil) => Nil
    case (Nil, head :: tail) => head :: sumOfBothHeads(Nil, tail)
    case (head :: tail, Nil) => head :: sumOfBothHeads(tail, Nil)
    case (head :: tail, head1 :: tail1) => head + head1 :: sumOfBothHeads(tail, tail1)
}

def takeOnlyBelowThanMean(list: List[Int]): List[Int] = {
  val mean = list.foldLeft(0)((acc: Int, number: Int) => acc + number) / list.length

  def smallerThanMean: Int => Boolean = _ < mean

  filter(smallerThanMean)(list)
}

takeOnlyBelowThanMean(List(1, 2, 3, 4, 2, 6))

def map[A](f: A => A)(list: List[A]): List[A] = {
  list match
    case head :: tail => f(head) :: map(f)(tail)
    case _ => Nil
}

def power: Int => Int = x => x * x

map(power)(List(1, 2, 3, 4, 5))
map((s: String) => s.toUpperCase)(List("asd", "szczyna", "Wrocaław"))


def reduce[A](f: (A, A) => A)(list: List[A]): A = {
  list match {
    case Nil => throw new UnsupportedOperationException("Cannot reduce an empty list")
    case _ => list.tail.foldLeft(list.head)(f)
  }
}


reduce((a: Int, b: Int) => a + b)(List(1, 2, 3, 4, 5))
println(reduce((a: String, b: String) => a + b)(List("se", "sasd", "ewfd", "asdkhq")))


def removeDuplicates[A](list: List[A]): List[A] = {
  val duplicateFilter = (list1: List[A]) => {
    list1 match
      case Nil => Nil
      case head :: tail => filter((element: A) => element != head)(tail)
  }

  list match
    case Nil => Nil
    case head :: tail => head :: removeDuplicates(duplicateFilter(tail))
}

removeDuplicates(List(1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2))