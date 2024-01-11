sealed trait Time {
  def before(other: Time): Boolean

  def convert(other: Time): Time
}

class NormalTime(hour: Int, minute: Int) extends Time {
  private var _hour: Int = if hour < 0 || hour > 24 then 0 else hour
  private var _minute: Int = if minute < 0 || minute > 59 then 0 else minute

  def getHour: Int = _hour

  def getMinute: Int = _minute

  def before(other: Time): Boolean = {
    def setProperType(other: Time): Time = {
      if (other.isInstanceOf[NormalTime]) other
      else convert(other)
    }

    val properOther: Time = setProperType(other)

    (this, properOther) match {
      case (thisTime: NormalTime, otherTime: NormalTime) =>
        thisTime.getHour < otherTime.getHour || (thisTime.getHour == otherTime.getHour && thisTime.getMinute < otherTime.getMinute)
      case _ =>
        throw new IllegalArgumentException("Comparison between incompatible time types")
    }
  }

  def convert(other: Time): Time = {
    other match
      case otherTime: TimeFromMidnight => new NormalTime(otherTime.getMinute / 60, otherTime.getMinute % 60)
      case _ => other
  }

  override def toString: String = getHour + ":" + getMinute
}

class TimeFromMidnight(val minutes: Int) extends Time {
  private val _minute: Int = if minutes < 0 || minutes > 1440 then 0 else minutes

  def getMinute: Int = _minute

  def before(other: Time): Boolean = {
    def setProperType(other: Time): Time = {
      if other.isInstanceOf[TimeFromMidnight] then other
      else convert(other)
    }

    val properOther: Time = setProperType(other)

    (this, properOther) match {
      case (thisTime: TimeFromMidnight, otherTime: TimeFromMidnight) =>
        thisTime.getMinute < otherTime.getMinute
      case _ =>
        throw new IllegalArgumentException("Comparison between incompatible time types")
    }
  }

  def convert(other: Time): Time = {
    other match
      case otherTime: NormalTime =>
        val _hours = otherTime.getMinute % 60
        val _minutes = otherTime.getMinute - _hours * 60

        new TimeFromMidnight(_hours * 60 + _minutes)
      case _ => other
  }

  override def toString: String = getMinute.toString
}

class Vehicle(val producer: String, val model: String) {
  private var _year: Int = -1
  private var _licensePlate: String = ""

  //getters
  def year: Int = _year

  def licensePlate: String = _licensePlate

  def this(producer: String, model: String, year: Int) = {
    this(producer, model)
    if (year < 0) throw new IllegalArgumentException("Year must be positive")
    _year = year
  }

  def this(producer: String, model: String, licensePlate: String) = {
    this(producer, model)
    _licensePlate = licensePlate
  }

  def this(producer: String, model: String, year: Int, licensePlate: String) = {
    this(producer, model)
    if (year < 0) throw new IllegalArgumentException("Year must be positive")
    _year = year
    _licensePlate = licensePlate
  }

  def setYear(year: Int): Unit = {
    if (year < 0) throw new IllegalArgumentException("Year must be positive")
    _year = year
  }
}

class ExceptionsExamples {
  def method1(): Unit = {
    method2()
  }

  private def method2(): Unit = {
    method3()
  }

  private def method3(): Unit = {
    throw new Exception("there is an exception in method3")
  }
}

object Main {
  def main(args: Array[String]): Unit = {
    val car = new Vehicle("Ford", "Fiesta")
    println(car.producer)
    car.setYear(2010)
    println(car.year)

    val ex = new ExceptionsExamples()

    try {
      ex.method1()
    } catch {
      case e: Exception =>
        println(e.getMessage + "\n")
        e.printStackTrace()
        e.printStackTrace()
    }

    val time1: Time = new NormalTime(-111, 20)
    val time2: Time = new TimeFromMidnight(13412)
    println(time2.before(time1))
    println(time1.before(time2))
    val tim3: Time = time1.convert(time2)
    println(tim3)
    // println(f1(5))
    // println(f2(0))
  }

  private def f1(n: Int): Int = {
    if n==0 then 1 // dla nieparzystych licz nie bedzie to dzialalo n < 0
    else n + f1(n-2)
  }

  private def f2(k: Int): Int = k match {
    case 0 => f2(k+1)
    case 1 => k + 1
  }
}