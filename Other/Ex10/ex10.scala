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

val dest = mutable.Seq(1, 2, 3)
val src = mutable.Seq(4, 5, 6)
copy(dest, src)
println("result of dest: " + dest)

//zad 2

abstract class Sequence[+A]:
    def append[A](x: Sequence[A]): Sequence[A]
// przyczyna bledu jest taka ze funkcja nie miala 
// zdefiniowanego odp typu chodzi o append[A] <- 


// zad 1 

// class GenericCellImm[T] (val x: T)

// class GenericCellImm[+T] (val x: T)

// class GenericCellMut[T] (var x: T)

// class GenericCellMut[+T] (val x: T)
