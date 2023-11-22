def composite(n: Int): List[Int] = {
  def getDivisors(element: Int): List[Int] = {
    for {
      divisor <- List.range(2, Math.sqrt(element).toInt + 1) if element % divisor == 0
    } yield divisor
  }
  
  for {
    element <- List.range(2, n + 1)
    if getDivisors(element) != Nil // sprawdzenei czy lista dzielnikow nie jest pusta jak jest to liczba pierwsza
  } yield element
}

def printRangeLists(begin : Int, end : Int): Unit = {
  for {
    element <- List.range(begin, end + 1)
  } yield {
    println(composite(element))
  }
}

printRangeLists(-19, 20)


// mod
/// https://mjod.kieg.science/teaching/paradygmaty-programowania/adt.pdf


def filter[A](predicate: A => Boolean)(list: List[A]): List[A] = {
  list match {
    case head :: tail =>
      if (predicate(head)) head :: filter(predicate)(tail)
      else filter(predicate)(tail)
    case _ => Nil
  }
}

def composite(end: Int): List[Int] = {
  def divisor(element : Int): Boolean = {
    List.range(2, Math.sqrt(element).toInt + 1).exists((acc: Int) => element % acc == 0)
  }

  filter(divisor)(List.range(2, end + 1))
}

composite(20)

