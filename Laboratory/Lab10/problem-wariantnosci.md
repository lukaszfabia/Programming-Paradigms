## Problem wariantności

### Co to jest typ?

Typ określa nam:

- zbiór wartości
- operacja

### Podtypowanie i dziedziecznie

- Podtypowanie - zgodność `interfejsów`
- Dziedzieczenie - przejęcie implementacji

_przykład C++ podtypowanie i dziedziczenie_ :

    class A {
        public:
        int f(int x) {return x;};
    };

    class B : public A {
        // int f(int x) -> interface z A
        void g(float y){
            //code
            ... = f(2*z)
        };
    };

_przykład C++ podtypowanie_ :

std::vector<T>

- insert()
- pushback()
- operator[]
- begin(), end()

std::forwardlist<T>

- size()
- insert()
- begin(), end()

Mozna zauwazyc, ze prawdopodobnie jest jakiś interface iterable (std::iterable<T>),
niestety w C++ nie ma czegoś takiego, ale mozna łatwo napisać:

    template<typename T, template<class C>>
    void print(const C<T> &container){
        for (const T& value: container){
            std::cout<<value<<" ";
        }
    }

_samo dziedziczenie_:

    class C: private A {
        void g(float x){
            //code
            =f(y*2);
        }
    };

Ten `private` nakłada się na wszystkie sekcje klasy A, czyli na zewnątrz nie będzie dostepnych metod z klasy A.

### Kwestia wariantności typów

Problem wariantności pojawia się gdy mamy `dwa` rodzaje polimorfizmu i mamy dwa, które sprawiają problem.

- `parametryczny`
- `inkluzyjny` - podtypowanie

  parametryczny + inkluzyjny = problem

- S <: T - S jest podtypem T

- S :> T - S jest nadtypem T

Niech

S <: T, C[U] - typ z parametrem

1. S <: T => C[S] oraz C[T] : `inwariancja` (są niezalezne)

2. S <: T => C[S] <: C[T] : `kowariancja` (grupa osób współpracujących ze sobą - kooperacja)

3. S <: T => S[S] :> C[T] : `kontrawariancja` (osoby będące ze sobą w konflikcie)

### Rodzaje wariantności

#### Kowariancja

S <: T => C[S] oraz C[T]
załózmy, ze List[T] jest kowariantna

_przykład Scala_

    class Person(val name: String)

    class Student(name : String) extends Person(name):
        var grades: List[Double]= Nil

    class Teacher(name: String) extends Person(name)

    var people: List[Person] = List[Student](new Student("Wojtek"))

    people.head.name // mozna why not

    people = new Teacher("Janusz")::people // blad bo Teacher nie jest podtypem Studenta, ale sie skomplikuje bo scala zrobi jakas sztuczke

**Typy `kowariantne` są źródłami danych(read only)**

#### Kontrwariancja

S <: T => S[S] :> C[T]
załózmy, ze List[T] jest kontrwariantna

_przykład Scala_

    var students: List[Student]= List[Person](new Teacher("Mietek"))

    students.head.grades // blad bo pan mietek nie jest uczniem

    students = new Students("Michał")::students // uda sie bo lista jest typu Stduent

**Typy `kontrawariantne` są ujściami danych(save only)**

#### Inwariancja

~ `kontrawariancja` + `kowariancja`

~ ani `kontrawariancja`, ani `kowariancja`

**Typy `inwariantne` są zarówno źródłami jak i wejściami danych**

### Podtypowanie funkcji

Mamy funkcje

    S' -> T' <: S -> T
    I[S']  O[T']  I[S] O[T]

1. S' >: S => I[S'] <: I[S] : `kontrawariancja`

2. T' <: T => O[T'] <: O[T] : `kowariancja`

_Scala_

- `inwariancja` C[T]

- `kowariancja` C[+T]

- `kontrawariancja` C[-T]

np.

    class Tinychin2[-X1, -X2, +R]
    // x1: -X1, x2: -X2 => r: R


    class List[+A]:
        def ::[B>:A](elem: B): List[B] =

        // A - student ex person
        // B - teacher ex person

        // jak przejdziemy po hierarchi to B okaze sie person i zwroci liste osob

    people = new Teacher("")::people
    // List[Person] = List[Student] => List[Person] = List[Person]
    // no i grades sie nie odczyta
