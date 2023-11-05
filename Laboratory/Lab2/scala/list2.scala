import scala.annotation.tailrec
// 2.1

// def cutOut[A](list: List[A], a: Int, b: Int): List[A] = {
//  @tailrec
//  def cut[B](input: List[B], begin: Int, end: Int, output: List[B]): List[B] = {
//    (input, begin, end) match {
//      case (_, _, -1) => output
//      case (head :: tail, 0, _) => cut(tail, 0, end - 1, head :: output)
//      case (_ :: tail, _, _) => cut(tail, begin - 1, end - 1, output)
//      case (Nil, _, _) => output
//    }
//  }

//  cut(list, a, b, Nil)
// }

def cutOut[A](a: Int)(b: Int): List[A] => List[A] = {
  def cut[B](list: List[B], begin: Int, end: Int): List[B] = {
    (list, begin, end) match {
      case (_, _, -1) => Nil
      case (head :: tail, 0, _) => head :: cut(tail, 0, end - 1)
      case (_ :: tail, _, _) => cut(tail, begin - 1, end - 1)
      case (Nil, _, _) => Nil
    }
  }

  cut(_: List[A], begin = a, end = b)
}

println(cutOut(1)(2)(List(1, 2, 222, 3, 4)) == List(2, 222))
println(cutOut(0)(1)(List(1, 2, 222, 3, 4)) == List(1, 2))
println(cutOut(0)(0)(List(1, 2, 222, 3, 4)) == List(1))
println(cutOut(0)(-1)(List(1, 2, 222, 3, 4)) == Nil)
println(cutOut(0)(-1)(Nil) == Nil)
println(cutOut(0)(1)(Nil) == Nil)
println(cutOut(0)(0)(Nil) == Nil)
println(cutOut(0)(4)(List(1, 2, 222, 3, 4, 11)) == List(1, 2, 222, 3, 4))
println(cutOut(0)(5)(List(1, 2, 222, 3, 4)) == List(1, 2, 222, 3, 4))
println(cutOut(-1)(10)(List(1, 2, 222, 3, 4)) == Nil)
println(cutOut(1)(1)(Nil) == Nil)
println(cutOut(12)(-12)(List(1, 2, 3, 5, 15)) == Nil)
println(cutOut(-12)(12)(List(1, 2, 3, 5, 15)) == Nil)
println(cutOut(0)(-10)(List(true, true, false, false, false)) == List(true, true, false, false, false))



// 2.2

//def split3Rec[A](input: List[A]): (List[A], List[A], List[A]) = {
//  if input == Nil || input.tail == Nil || input.tail.tail == Nil then (Nil, Nil, Nil)
//  else {
//    val (a, b, c) = split3Rec(input.tail.tail.tail)
//    if input.head != Nil && input.tail.head != Nil && input.tail.tail.head != Nil then (input.head :: a, input.tail.head :: b, input.tail.tail.head :: c)
//    else (a, b, c)
//  }
//}

def split3Rec[A](input: List[A]): (List[A], List[A], List[A]) = {
  input match {
    case element1 :: element2 :: element3 :: tail =>
      val (list1, list2, list3) = split3Rec(tail)
      (element1 :: list1, element2 :: list2, element3 :: list3)
    case _ => (Nil, Nil, Nil)
  }
}

def split3Tail[A](input: List[A]): (List[A], List[A], List[A]) = {
  @tailrec
  def split[B](input: List[B], list1: List[B], list2: List[B], list3: List[B]): (List[B], List[B], List[B]) = {
    input match {
      case element1 :: element2 :: element3 :: tail => split(tail, element1 :: list1, element2 :: list2, element3 :: list3)
      case _ => (list1, list2, list3)
    }
  }

  split(input, Nil, Nil, Nil)
}


println(split3Tail(List(1, 2, 3, 4, 5, 6, 7)) == (List(4, 1), List(5, 2), List(6, 3)))
println(split3Tail(List(1, 2, 3, 4, 5, 6)) == (List(4, 1), List(5, 2), List(6, 3)))
println(split3Tail(List(1, 2, 3, 4, 5))== (List(1), List(2), List(3)))
println(split3Tail(List(1, 2, 3, 4)) == (List(1), List(2), List(3)))
println(split3Tail(List(1, 2, 3)) == (List(1), List(2), List(3)))
println(split3Tail(List(1, 2))== (Nil, Nil, Nil))
println(split3Tail(List(1)) == (Nil, Nil, Nil))
println(split3Tail(Nil) == (Nil, Nil, Nil))


println(split3Rec(List(1, 2, 3, 4, 5, 6, 7)) == (List(1, 4), List(2, 5), List(3, 6)))
println(split3Rec(List(1, 2, 3, 4, 5, 6)) == (List(1, 4), List(2, 5), List(3, 6)))
println(split3Rec(List(1, 2, 3, 4, 5)) == (List(1), List(2), List(3)))
println(split3Rec(List(1, 2, 3, 4)) == (List(1), List(2), List(3)))
println(split3Rec(List(1, 2, 3)) == (List(1), List(2), List(3)))
println(split3Rec(List(1, 2)) == (Nil, Nil, Nil))
println(split3Rec(List(1)) == (Nil, Nil, Nil))
println(split3Rec(Nil) == (Nil, Nil, Nil))

