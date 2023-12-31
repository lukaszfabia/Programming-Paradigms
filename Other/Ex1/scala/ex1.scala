def flatten[A](xss: List[List[A]]): List[A] = {
  if xss!=Nil then xss.head:::flatten(xss.tail)
  else Nil
}

def count[A](x: A, xss: List[A]): Int = {
    if xss!=Nil then if (xss.head==x) then 1+count(x,xss.tail) else count(x,xss.tail)
    else 0
}

def replicate[A](x: A, n: Int): List[A] = {
    if n>0 then x::replicate(x, n-1)
    else Nil
}

def power2nd(xss: List[Int]): List[Int] ={
    if xss!=Nil then xss.head * xss.head :: power2nd(xss.tail)
    else Nil
}

val power2nd = (xss: List[Int]) => power2nd2(xss)

def palindrome[A](xs: List[A]): Boolean = {
    xs.reverse == xs
}

def listLength[A](xs: List[A]): Int = {
    if xs!=Nil then 1+listLength(xs.tail)
    else 0
}

//tests
println("Test for flatten: " + (flatten(List(List(1, 1), List(5, 8))) == List(1, 1, 5, 8)))
println("Test for count: " + (count(1, List(1, 2, 4, 1, 1, 2, 1, 1, 1)) == 6))
println("Test for replicate: " + (replicate("a", 5) == List("a", "a", "a", "a", "a")))
println("Test for power2nd: " + (power2nd(List(1, 2, 3, -4)) == List(1, 4, 9, 16)))
println("Test for palindrome: " + (palindrome(List(1, 2, 3, 2, 1)) == true))
println("Test for palindrome: " + (palindrome(List(1, 2, 3, 2, 2)) == false))
println("Test for listLength: " + (listLength(List(1, 2, 3, 2, 2)) == 5))
println("Test for power2nd2: " + (power2nd2(List(4,5,6,7,8,9)) == List(16,25,36,49,64,81)))

//:load ex1.scala