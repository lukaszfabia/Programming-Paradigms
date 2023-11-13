def composite(n: Int): List[Int] = {
  for {
    element <- List.range(2, n + 1)
    if (for {
      divisor <- List.range(2, Math.sqrt(element).toInt + 1) if element % divisor == 0
    } yield divisor) != Nil // sprawdzenei czy lista dzielnikow nie jest pusta jak jest to liczba pierwsza
  } yield element
}

List.range(-1, 11).foreach(x => println(composite(x)))

