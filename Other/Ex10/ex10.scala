import scala.collection.mutable


// zad 4
def copy[A](dest: mutable.Seq[A], src: mutable.Seq[A]): Unit = {
  if dest.length == src.length then
    var index=0
    src.foreach(elem => {
        dest.update(index, src.seq(index))
        index +=1
    })
  else throw new IllegalArgumentException("lengths of dest and src are not equal")
}

//zad 2

abstract class Sequence[+A]:
    def append[A](x: Sequence[A]): Sequence[A] // nalezy podac typ jaki jest w parametrze funckji z A a A jest kowariantne 


// zad 1 

class GenericCellMut1[+T] (val x: T) // nalezy zmienic var na val bo problem byl z mutatorem bo 
// moznaby bylo przypisac różne instancje danego typu do zmiennej

// class GenericCellMut[-T] (private var x: T)

class GenericCellMut2[-T, A >: T] (private var x: A) // nalezy zagwarantowac ze A jest nadtypem T i
//  dziala, czyli typ A jest bardziej ogolny 




@main
def main(): Unit ={
  val dest = mutable.Seq(1, 2, 3)
  val src = mutable.Seq(4, 5, 6)
  copy(dest, src)
  println("result of dest: " + dest)
}
