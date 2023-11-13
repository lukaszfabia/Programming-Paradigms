def composite(n: Int): List[Int] = {
  for {
    element <- List.range(2, n + 1)
    divider <- 2 to Math.sqrt(element).toInt
    if element % divider == 0
  } yield element
}
List.range(2, 11).foreach(x => println(composite(x)))