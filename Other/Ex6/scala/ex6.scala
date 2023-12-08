import scala.annotation.tailrec


@tailrec
def whileLoop(condition: => Boolean)(body: => Unit): Unit =
  if condition then
    body
    whileLoop(condition)(body)

var count = 0
whileLoop(count < 6) {
  println(count)
  count += 1
}

def quickSortRec(arr: Array[Int], l: Int, r: Int): Unit = {

  def swap(arr: Array[Int], i: Int, j: Int): Unit = {
    val tmp = arr(i)
    arr(i) = arr(j)
    arr(j) = tmp
  }

  def choosePivot(arr: Array[Int], m: Int, n: Int): Int = arr((m + n) / 2)

  def partition(arr: Array[Int], l: Int, r: Int, pivot: Int): Int = {
    var i = l
    var j = r
    while i <= j do
      while arr(i) < pivot do i += 1
      while arr(j) > pivot do j -= 1
      if i <= j then
        swap(arr, i, j)
        i += 1
        j -= 1
    i
  }

  if l < r then
    val pivot = choosePivot(arr, l, r)
    val index = partition(arr, l, r, pivot)
    quickSortRec(arr, l, index - 1)
    quickSortRec(arr, index, r)
}

def quickSort(arr: Array[Int]): Unit = {
  quickSortRec(arr, 0, arr.length - 1)
}

var arr = Array(1, 5, 3, 2, 4)

quickSort(arr)

var index = 0
whileLoop(index < arr.length) {
  print(arr(index).toString + " ")
  index += 1
}