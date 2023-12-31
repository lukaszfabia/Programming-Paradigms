// import scala.annotation.tailrec

def reverse4[A, B, C, D](list: (A, B, C, D)): (D, C, B, A) = {
  (list._4, list._3, list._2, list._1)
}

// def sumProd(s: Int, e: Int): (Int, Int) = {
//   if s==e then return (0, 0)
//   def createList(s: Int, e: Int): List[Int] = {
//     if s >= e then Nil
//     else s :: createList(s + 1, e)
//   }

//   val list = createList(s, e)
//   val (sum, prod) = (0, 1)

//   def count(list: List[Int]): (Int, Int) = {
//     if list == Nil then (sum, prod)
//     else {
//       val (sum, prod) = count(list.tail)
//       (sum + list.head, prod * list.head)
//     }
//   }

//   count(list)
// }

def sumProd(s: Int, e: Int): (Int, Int) = {
  if s>=e then (0, 1)
  else {
    val (sum, prod) = sumProd(s + 1, e)
    (s + sum, s * prod)
  }
}

// def isPerfectTailRec(n: Int): Boolean = {
//   @tailrec
//   def divider(sum: Int, i: Int): Int = {
//     if i == 0 then sum
//     else if n % i == 0 then divider(sum + i, i - 1)
//     else divider(sum, i - 1)
//   }

//   n == divider(0, n - 1)
// }

def isPerfect(n: Int): Boolean = {
  if n < 1 then return false

  def dividerList(dividers: List[Int], i: Int): List[Int] = {
    if i < n then if n % i == 0 then i :: dividerList(dividers, i + 1) else dividerList(dividers, i + 1)
    else dividers
  }

  def sum(list: List[Int]): Int = {
    if list == Nil then 0
    else list.head + sum(list.tail)
  }

  sum(dividerList(Nil, 1)) == n
}

def insert[A](list: List[A], element: A, index: Int): List[A] = {
  if list == Nil then List(element)
  else {
    if index == 0 then element :: list
    else list.head :: insert(list.tail, element, index - 1)
  }
}

println("Test for reverse4")
println(reverse4(true, 'd', "string", 1) == (1, "string", 'd', true))
println(reverse4(1, 2, 3, 4) == (4, 3, 2, 1))

println("Test for sumProd")
println(sumProd(1, 5) == (10, 24))
println(sumProd(1, 1) == (0, 1))
println(sumProd(-1, -10) == (0, 1))
println(sumProd(1, 0) == (0, 1))
println(sumProd(1, 2) == (1, 1))
println(sumProd(-10, -5) == (-40, -30240))
println(sumProd(0, 0)==(0, 1))


println("Test for isPerfect")
val list = (0 to 500).toList
val perfectNumbers = list.filter(isPerfect)


println("Test for insert")
println(insert(List(1, 2, 3), 4, 0) == List(4, 1, 2, 3))
println(insert(List(1, 2, 3), 4, 1) == List(1, 4, 2, 3))
println(insert(List('h', 'e', 'l', 'o'), 'l', 2) == List('h', 'e', 'l', 'l', 'o'))
println(insert(List(true, false, true), false, -1) == List(true, false, true, false))
println(insert(List('1', '2', '3'), '4', 4) == List('1', '2', '3', '4'))

// modyfikacja 
def choice(list1: List[Int], list2: List[Int]): List[Int] = {
  if list1==Nil && list2==Nil then Nil
  else if list1 == Nil && list2!=Nil then list2
  else if list1 != Nil && list2==Nil then list1
  else if list1.head > list2.head then list1.head :: choice(list1.tail, list2.tail)
  else list2.head :: choice(list1.tail, list2.tail)
  // else if list1.head < list2.head then list2.head :: choice(list1.tail, list2.tail)
  // else list1.head :: choice(list1.tail, list2.tail)
}

println("Test for choice")
choice(List(1,2,3), List(4,5,6))==List(4,5,6)
choice(List(1,2,3), List(1,2,3))==List(1,2,3)
choice(List(1,2,3), List(0,0,0))==List(1,2,3)
choice(List(1,2,3), List(0,1,2))==List(1,2,3)
choice(List(1, 5, 3 ,8), List(2, 7, 4, 1))==List(2, 7, 4, 8)
choice(List(1, 5, 3 ,8), List(2, 7, 4, 1, 9))==List(2, 7, 4, 8, 9)
choice(List(1, 5, 3 ,8, 9), List(2, 7, 4, 1))==List(2, 7, 4, 8, 9)
choice(List(0,0,0,0), List()) == List(0,0,0,0)