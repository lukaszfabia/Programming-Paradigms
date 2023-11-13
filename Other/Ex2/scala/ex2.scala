import scala.annotation.tailrec

var stackDeep = 0

def evenR(n: Int): Boolean = {
    stackDeep+=1
    if n == 0 then true else oddR(n-1)
}

def oddR(n: Int): Boolean = {
    stackDeep+=1
    if n == 0 then false else evenR(n-1)
}
// glebokosc jest taka sama w scali i w ocamlu 
// evenR(3)
// oddR(2)
// evenR(1)
// oddR(0)
// -------- 4
// zwracamy false

println(evenR(3))
println(stackDeep)

// 0 1 1 2 3 5 8 13 21 34 55 89 144 233 377
def fib(x: Int): Int = {
  x match {
    case 0 => 0
    case 1 => 1
    case _ => fib(x-1) + fib(x-2)
  }
}

def fibAsFunc: Int => Int = {
  case 0 => 0
  case 1 => 1
  case x => fibAsFunc(x - 1) + fibAsFunc(x - 2)
}

def fibTail(x: Int): Int = {
  @tailrec
  def getFib(x: Int, curr: Int, prev: Int): Int = {
    x match {
      case 0 => 0
      case 1 => prev
      case _ => getFib(x - 1, curr + prev, curr)
    }
  }

  getFib(x, 1, 0)
}

def fibAsFuncTail: Int => Int = {
  @tailrec
  def getFib(x: Int, curr: Int, prev: Int): Int = {
    x match{
      case 0 => 0
      case 1 => prev
      case _ => getFib(x-1, curr + prev, curr)
    }
  }
  (x: Int) => getFib(x, 1, 0)
}

val number42thTail = fibAsFuncTail(42)
// rekurencja ogonowa jest szybsza, bo nie tworzy nowych klatek na stosie, tylko wykorzystuje te, ktore juz sa
val number42thNormal = fibAsFunc(42)

number42thTail == number42thNormal

def root3(a: Double): Double = {
  val precision = Math.pow(10, -15) * Math.abs(a)

  @tailrec
  def calculate(x: Double): Double = {
    if Math.abs(x * x * x - a) <= precision then x
    else calculate(x + (a / (x * x) - x) / 3)
  }

  if a > 1 then calculate(a / 3)
  else calculate(a)
}

def root3asFunc: Double => Double = {
  (a: Double) =>
    val precision = Math.pow(10, -15) * Math.abs(a)

    @tailrec
    def calculate(a: Double)(x: Double): Double = {
      if Math.abs(x * x * x - a) <= precision then x
      else calculate(a)(x + (a / (x * x) - x) / 3)
    }
    
    if a > 1 then calculate(a)(x = a / 3)
    else calculate(a)(x = a)
}

println(root3asFunc(0.216))
println(root3asFunc(0.0))
println(root3asFunc(1.0))
println(root3asFunc(2.0))
println(root3asFunc(27))
println(root3asFunc(4.0))

println(root3(27.0))
println(root3(0.625))

//czy xs jest w ys
@tailrec
def initSegment[A](xs: List[A], ys: List[A]): Boolean = {
    (xs, ys) match {
        case (Nil, _) => true
        case (_, Nil) => false
        case _ => 
            if xs.head == ys.head then initSegment(xs.tail, ys.tail)
            else false
    }
}

println(initSegment(Nil, Nil))
println(initSegment(List(1,2,3), List(1,2,3)))
println(!initSegment(List('a', 'b', 'c'), List('b', 'c')))

// o(list.length + 1) - gdy index > list.length
// o(index + 1) - ogolna zlozonosc
def replaceNth[A](xs: List[A], index: Int, element: A): List[A] = {
    (xs, index) match{ 
        case (Nil, _) => Nil // wtedy ma sens bo jesli jest Nil i dowolny z elementem ktory ma zastapic element ktorego nie ma to jest Nil i chuj 
        case (_ :: tail, 0) => element::tail
        case (head :: tail, _) => head :: replaceNth(tail, index-1, element)
    }
}
// 1 :: 2 :: 3 :: replaceNth(List(4,5), 0, 99) - case 3
// 1 :: 2 :: 3 :: 99 :: xs.tail - case 2
// 1 :: 2 :: 3 :: 99 :: 5 - na case 2 wychodzimy z funkcji bo nie ma wywolania rekurencyjnego
// czyli w tym wypadku mamy rekurencje ogonowa czyli taka ktora
// dziala to do zredukowania indexu do 0 i wtedy wychodzi z funkcji w przypadku niepustej listy
println(replaceNth(List(1,2,3,4,5), 4, 99)) // 5 krokow
println(replaceNth(List(1,2,3,4,5), 2, 99)) // 3 kroki
println(replaceNth(Nil, 0, 99)) // 1 krok
println(replaceNth(List(1,2,3,4,5), 10, 99)) // 6 krokow

// glupie zd z krotkami
val patternA = List(-2, -1, 0, 1, 2)
val patternB = List((1, 2), (0, 1))

val List(_, _, x, _, _) = patternA;
val List(_, (y, _)) = patternB;

println(x==0 && y==0);
