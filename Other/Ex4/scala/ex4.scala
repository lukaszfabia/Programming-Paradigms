// zadanie 5

sealed trait Graphs[A]

case class Graph[A](succ: A => List[A]) extends Graphs[A]

val g = Graph((i: Int) =>
  i match
    case 0 => List(3)
    case 1 => List(0, 2, 4)
    case 2 => List(1)
    case 3 => Nil
    case 4 => List(0, 2)
    case n =>
      throw new Exception(s"Graph g: node $n doesn't exist")
)



def breadthSearch[A](g: Graph[A])(startNode: A): List[A] = {
  def search(visited: List[A])(queue: List[A]): List[A] = {
    queue match
      case Nil => Nil
      case h :: t =>
        if visited contains h then search(visited)(t)
        else h :: search(h :: visited)(t ::: (g succ h))
  }

  search(Nil)(List(startNode))
}

def depthSearch[A](g: Graph[A])(startNode: A): List[A] = {
  def search(visited: List[A])(rest: List[A]): List[A] = {
    rest match
      case Nil => Nil
      case h :: t =>
        if visited contains h then search(visited)(t)
        else h :: search(h :: visited)((g succ h) ::: t)
  }

  search(Nil)(List(startNode))
}


depthSearch(g)(4)
breadthSearch(g)(4)