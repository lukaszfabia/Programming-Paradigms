def modifiedPascalF(n: Int): Option[List[Int]] = {
  def append(func: (Int, Int) => Int, part1: List[Int], part2: List[Int]): List[Int] = (part1, part2) match
    case (Nil, _) => part2
    case (_, Nil) => part1
    case (hd1 :: tl1, hd2 :: tl2) => func(hd1, hd2) :: append(func, tl1, tl2)

  def aux(k: Int): List[Int] = k match
    case 0 => List(1)
    case _ =>
      val helper = aux(k - 1)
      if k % 2 == 0 then 1 :: append(_ + _, helper, helper.tail)
      else 1 :: append(_ - _, helper, helper.tail)

  if n < 0 then None
  else Some(aux(n))
}


def modifiedPascalI(n: Int): Array[Int] = {
  var result = Array.fill(n + 1)(1)
  var curr: Array[Int] = null
  var i = 1
  var j: Int = 0
  while i <= n do
    curr = Array.fill(i + 1)(0)
    curr(0) = 1
    curr(i) = 1
    j = 1
    while j < i do
      if i % 2 == 0 then curr(j) = result(j - 1) + result(j)
      else curr(j) = result(j - 1) - result(j)
      j += 1
    result = curr
    i += 1

  result
}


def test(pascalImperative: Array[Int], pascalFunctional: Option[List[Int]]): Unit = {
  if pascalImperative.toList == pascalFunctional.getOrElse(Nil) then println("passed")
  else println("failed")
}


(0 to 100).foreach(i => test(modifiedPascalI(i), modifiedPascalF(i)))

(0 to 6).foreach(i => println(modifiedPascalF(i).getOrElse(Nil)))
(0 to 6).foreach(i => println(modifiedPascalI(i).mkString("Array(", ", ", ")")))





