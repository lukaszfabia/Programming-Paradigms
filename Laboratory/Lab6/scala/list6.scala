import scala.annotation.tailrec

sealed trait Expression

case class Val(value: Double) extends Expression

case class Fun((Double, Double) => Double) extends Expression

case object Sum extends Expression

case object Diff extends Expression

case object Prod extends Expression

case object Div extends Expression

def evaluate(expression: List[Expression]): Option[Double] = {
  @tailrec
  def aux(stack: List[Double], rest: List[Expression]): Option[Double] = {
    rest match {
      case Val(value) :: tail => aux(value :: stack, tail)
      case Sum :: tail =>
        stack match {
          case x :: y :: rest => aux((x + y) :: rest, tail)
          case _ => None
        }
      case Diff :: tail =>
        stack match {
          case x :: y :: rest => aux((x - y) :: rest, tail)
          case _ => None
        }
      case Prod :: tail =>
        stack match {
          case x :: y :: rest => aux((x * y) :: rest, tail)
          case _ => None
        }
      case Div :: tail =>
        stack match {
          case x :: y :: rest if y != 0 => aux((x / y) :: rest, tail)
          case _ => None
        }
      case Nil =>
        stack match {
          case result :: Nil => Some(result)
          case _ => None
        }
    }
  }

  aux(Nil, expression)
}

// stack = []
// rest = List(List(Val(1.0), Val(3.5), Sum))
// Val(1.) :: stack = [1.] rest = [Val(3.5); Sum]
// Val(3.5) :: stack = [3.5; 1.] = rest = [Sum]
// case Sum => 3.5 :: 1. :: [Sum] => aux((3.5+1.)::rest, []) =>
// (stack = [4.5] rest = [])
// case Nil => 4.5 :: Nil => Some(4.5)

evaluate(List(Val(1.0), Val(3.5), Sum))
evaluate(List(Val(0.0), Val(1.0), Div))
evaluate(List(Val(1.0), Val(3.5), Sum, Val(2.0), Prod))
evaluate(List(Val(1.0), Val(3.5), Sum, Val(0.0), Div))
evaluate(Nil)
evaluate(List(Val(1)))
evaluate(List(Val(1), Diff))
evaluate(List(Val(1), Val(2)))
evaluate(List(Sum, Val(1), Val(2)))
evaluate(List(Val(1), Val(2), Sum, Val(3), Prod, Val(4), Diff))



