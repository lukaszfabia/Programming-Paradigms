def composite(n: Int): List[Int] = {
  def divisorList(element: Int): List[Int] = {
    for {
      divisor <- List.range(2, Math.sqrt(element).toInt + 1) if element % divisor == 0
    } yield divisor
  }

  for {
    element <- List.range(2, n + 1)
    if divisorList(element) != Nil // sprawdzenei czy lista dzielnikow nie jest pusta jak jest to liczba pierwsza
  } yield element
}

def printRangeLists(begin : Int, end : Int): Unit = {
  for {
    element <- List.range(begin, end + 1)
  } yield {
    println(composite(element))
  }
}

printRangeLists(1, 20)
