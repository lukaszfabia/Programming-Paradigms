import scala.annotation.tailrec

// 0 1 1 2 3 5 8 13 21 34 55 89 144 233 377
def fib(x: Int) : Int = {
    x match {
        case 0 => 0
        case 1 => 1
        case _ => fib(x-1) + fib(x-2)
    }
}

def fibTail(x: Int) : Int  = {
    @tailrec
    def getFib(x: Int, prev: Int, prevPrev: Int): Int = {
        if (x == 0) 0
        else if (x == 1) prev
        else getFib(x-1, prev + prevPrev, prev)
    }
    getFib(x, 1, 0)
}

val list = (0 to 10).toList
val fibList = list.map(fib)
val fibTailList = list.map(fibTail)

println(fibList==fibTailList)

def root3(number: Double) : Double = {
    @tailrec
    def calculate(x: Double): Double = {
        val next = x - (x*x*x - number)/(3*x*x)
        if (next == x) x
        else calculate(next)
    }
    calculate(number)
}


println(root3(0.2))

//czy xs jest w ys
def initSegment[A](xs: List[A], ys: List[A]): Boolean = {
    if (xs.length > ys.length){
        false
    }else{
        (xs, ys) match{
            case (Nil, _) => true
            case (_, Nil) => false
            case _ => 
                if (ys.head == xs.head){
                    initSegment(xs.tail, ys.tail)
                }else{
                    false
                }
        }
    }
}

println(initSegment(Nil, Nil))
println(initSegment(List(1,2,3), List(1,2,3)))
println(!initSegment(List('a', 'b', 'c'), List('b', 'c')))


def replaceNth[A](xs: List[A], index: Int, element: A): List[A] = {
    (xs, index) match{
        case (Nil, _) => Nil
        case (_ :: tail, 0) => element::tail
        case (head :: tail, _) => head :: replaceNth(tail, index-1, element)
    }
}

println(replaceNth(List(1,2,3,4,5), 0, 99))
println(replaceNth(List(1,2,3,4,5), 2, 99))

// glupie zd z krotkami
val patternA = List(-2, -1, 0, 1, 2)
val patternB = List((1, 2), (0, 1))

val List(_, _, x, _, _) = patternA;
val List(_, (y, _)) = patternB;

println(x+y==0)