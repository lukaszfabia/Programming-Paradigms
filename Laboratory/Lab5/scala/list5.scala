import java.util

var calls = 0
def stirling(n: Int, m: Int): Int = {
  calls += 1
  if n < m then throw new IllegalArgumentException("n < m")
  (n, m) match {
    case (0, 0) => 1
    case (n, 0) => 0
    case (0, m) => 0
    case (n, m) =>
      if n == m then 1
      else stirling(n - 1, m - 1) + m * stirling(n - 1, m)
  }
}

//println(stirling(0, 0)) // Expected output: 1
println(stirling(5, 2)) // Expected output: 15
println(calls)
calls = 0
println(stirling(10, 5)) // Expected output: 42525
println(calls)
//
//println(stirling(3, 3)) // Expected output: 1
//
//println(stirling(6, 0)) // Expected output: 0

def memoized_stirling(n: Int, m: Int): Int = {
  // mozna uzyc ew HashMap
  val cache = new util.Hashtable[(Int, Int), Int]()

  def aux(n: Int, m: Int): Int = {
    calls += 1
    if n < m then throw new IllegalArgumentException("n < m")
    (n, m) match {
      case (0, 0) => 1
      case (n, 0) => 0
      case (0, m) => 0
      case (n, m) =>
        if n == m then 1
        else {
          if cache.containsKey((n, m)) then cache.get((n, m))
          else {
            val value = aux(n - 1, m - 1) + m * aux(n - 1, m)
            cache.put((n, m), value)
            value
          }
        }
    }
  }

  aux(n, m)
}

// 19 w normal
//println(memoized_stirling(0, 0)) // Expected output: 1
calls = 0
println(memoized_stirling(5, 2)) // Expected output: 15
println(calls)
//
calls = 0
println(memoized_stirling(10, 5)) // Expected output: 42525
println(calls)
//
//println(memoized_stirling(3, 3)) // Expected output: 1


// zadanie 2 

def make_memoize[A, B](func: A => B): A => B = {
  val cache = new util.Hashtable[A, B]()

  (a: A) => {
    if cache.containsKey(a) then cache.get(a)
    else {
      val value = func(a)
      cache.put(a, value)
      value
    }
  }
}


def fib(n: Int): Int = {
  calls += 1
  n match
    case 0 => 1
    case 1 => 1
    case _ => fib(n - 1) + fib(n - 2)
}


val memoizedFib = make_memoize(fib)
println(memoizedFib(5))
println("first " + calls)
calls = 0
println(memoizedFib(5))
println(calls==0)
println(memoizedFib(6))
println("first " + calls)
calls = 0
println(memoizedFib(6))
println(calls==0)


// zadanie 3 

lazy val lazyFactorial = stirling(10, 5)
val tryhardFactorial = stirling(10, 5) // w tym przypadku nie ma znaczenia czy my to wywolamy czy nie obliczby wartosc i przypisze do val tryhardFactorial

for i <- 1 to 10 do
  println("czekamy na wynik")


// czyli generalnie jesli nie nastapilo jawne wywolanie w kodzie wartosci
// lazyFactorial to nie bedziemy jej obliczac,
// czyli my w sumie robimy rezerwacje pamieci i z funkcji zczytujemy jaki typ bedzie zwracany

