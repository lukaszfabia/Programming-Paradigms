def composite(n: Int): List[Int] = {
  for {
    x <- List.range(2, n + 1)
    j <- 2 to Math.sqrt(x).toInt
    if x % j ==0
  } yield x
}
List.range(2, 11).foreach(x => println(composite(x)))