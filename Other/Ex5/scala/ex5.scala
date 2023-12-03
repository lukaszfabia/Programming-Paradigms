def repeat[A](n: Int)(list: LazyList[A]): LazyList[A] = {
  def aux[B](x: Int)(list: LazyList[B]): LazyList[B] = {
    (list, x) match
      case (LazyList(), _) => LazyList()
      case (head #:: tail, 0) => aux(n)(tail)
      case (head #:: tail, _) => head #:: aux(x - 1)(list)
  }

  aux(n)(list)
}


def fib(): LazyList[Int] = {
  def aux(a: Int, b: Int): LazyList[Int] = {
    a #:: aux(b, a + b)
  }

  aux(0, 1)
}

repeat(3)(LazyList(1, 2, 3)).toList

fib().take(10).toList


sealed trait lBT[+A]

case object LEmpty extends lBT[Nothing]

case class LNode[+A](elem: A, left: () => lBT[A], right: () => lBT[A]) extends lBT[A]

def lBreadth[A](tree: lBT[A]): LazyList[A] = {
  def aux(queue: List[lBT[A]]): LazyList[A] = {
    queue match
      case Nil => LazyList()
      case LEmpty :: tail => aux(tail)
      case LNode(elem, left, right) :: tail => elem #:: aux(tail ::: List(left(), right()))
  }

  aux(List(tree))
}

def lTree(x: Int): lBT[Int] = {
  LNode(x, () => lTree(2 * x), () => lTree(2 * x + 1))
}

lBreadth(lTree(4)).take(10).toList