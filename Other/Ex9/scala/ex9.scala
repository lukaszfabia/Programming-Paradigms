class Vehicle(val producer: String, val model: String) {
    private final var _year: Int = -1
    private var _licensePlate: String = ""

    //gettery
    def year = _year
    def licensePlate = _licensePlate

    def this(producer: String, model: String, year: Int) = {
        this(producer, model)
        _year = year
    }

    def this(producer: String, model: String, licensePlate: String) = {
        this(producer, model)
        _licensePlate = licensePlate
    }

    def this(producer: String, model: String, year: Int, licensePlate: String) = {
        this(producer, model)
        _year = year
        _licensePlate = licensePlate
    }

    def setYear(year: Int) = {
        _year = year
    }
}   


object MyObject {
    def main(args: Array[String]): Unit = {
        println("Hello, World!")
        val car = new Vehicle("Ford", "Fiesta")
        println(car.producer)
        car.setYear(2010)
        println(car.year)
    }
}