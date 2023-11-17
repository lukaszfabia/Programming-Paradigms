import scala.annotation.tailrec

// zadanie 1a
type Point = (Float, Float)

def distance(p1: Point, p2: Point): Float = {
  val (x1, y1) = p1
  val (x2, y2) = p2
  val dx = x1 - x2
  val dy = y1 - y2
  math.sqrt(dx * dx + dy * dy).toFloat
}

distance((1f, 5f), (2f, 3f))

type Point3D = (Float, Float, Float)

type PointND = List[Float] // wychodzi na to ze musimy miec coordów tyle ile wymiarow

// zadanie 2a, niech bedzie lista n elementowa punktow <=> r^n przestrzenia
// np Point(1,23,4) Point(1,4,1) dist = sqrt(0 + (23-4)^2 + (4-1)^2)

def distance(p1: PointND, p2: PointND): Float = {
  @tailrec
  def aux(p1: PointND, p2: PointND, acc: Float): Float = {
    (p1, p2) match {
      case (Nil, Nil) => acc
      case (x :: restOfFirst, y :: restOfSecond) => aux(restOfFirst, restOfSecond, acc + (x - y) * (x - y))
      case _ => throw new Exception("Points have to be the same dimension")
    }
  }

  math.sqrt(aux(p1, p2, acc = 0)).toFloat
}
val a = (1f, 1f, 1f)
val b = (2f, 2f, 2f)

distance(List(a._1, a._2, a._3), List(b._1, b._2, b._3)) // nie ma sensu tworzyc kolejnej funkcji do distance
// wezmy te bardziej abstrakcyja

val point1 = List(1f, 2f, 3f)
val point2 = List(4f, 5f, 6f)
distance(point1, point2)

// zadanie 2

//type Person = (String, String, Int, String, Float)
//
//def toStr(person: Person): String = s"${person._1} ${person._2} ${person._3} ${person._4} ${person._5}"
//
//toStr(("Jan", "Kowalski", 23, "M", 38f))
//
//type Partnership = (Person, Person)
//
//def getYounger(partnership: Partnership): Person = {
//  if partnership._1._3 == partnership._2._3 && partnership._1._5 > partnership._2._5 then partnership._2
//  else if partnership._1._3 < partnership._2._3 then partnership._1
//  else partnership._2
//}
//
//toStr(getYounger((("Jan", "Kowalski", 23, "M", 38f), ("Anna", "Nowak", 23, "F", 38f))))

case class Person(name: String, surname: String, age: Int, sex: String, kicksSize: Float) {
  override def toString: String = s"$name $surname $age $sex $kicksSize"
}

case class Partnership(p1: Person, p2: Person) {
  def getYounger: Person = {
    if p1.age == p2.age && p1.kicksSize > p2.kicksSize then p2
    else if p1.age < p2.age then p1
    else p2
  }
}

val person1 = Person("Jan", "Kowalski", 23, "M", 38f)
val person2 = Person("Anna", "Nowak", 23, "F", 38f)

val partnership = Partnership(person1, person2)
partnership.getYounger

enum Day:
  case Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday

sealed trait WeekDay

case object Monday extends WeekDay

case object Tuesday extends WeekDay

case object Wednesday extends WeekDay

case object Thursday extends WeekDay

case object Friday extends WeekDay

case object Saturday extends WeekDay

case object Sunday extends WeekDay


def getNextDay(day: WeekDay): WeekDay = {
  day match {
    case Monday => Tuesday
    case Tuesday => Wednesday
    case Wednesday => Thursday
    case Thursday => Friday
    case Friday => Saturday
    case Saturday => Sunday
    case Sunday => Monday
  }
}

val toStr: WeekDay => String = _.toString

print("ona jest " + toStr(getNextDay(getNextDay(Monday))))

sealed trait Maybe[A]

case class Just[A](value: A) extends Maybe[A]

case class Nothing[A]() extends Maybe[A]

def safeHead[A](list: List[A]): Maybe[A] = {
  list match {
    case Nil => Nothing()
    case x :: _ => Just(x)
  }
}

safeHead(Nil)