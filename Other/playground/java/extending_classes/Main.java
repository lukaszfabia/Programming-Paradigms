package Other.playground.java.extending_classes;


public class Main {
    public static class A {
        protected String str = "the highest class";
        public void exceute(){
           System.out.println("A");
        }
    }

    public static class B extends A {
        protected String string = "IDK";
        public void exceute(){
            System.out.println("B");
        }
    }

    public static class C extends B {
        public void exceute(){
            System.out.println("C");
            System.out.println(str);
            System.out.println(string);
        }
    }


    public static void main(String[] args) {
        // nie mozna castowac obiektu z klasy wyzej na klase nizej, classCastException
        // czyli mozna tylko w "gore" rzutowac
        B b = new B();
        A ab = (A) b; // ref jest na obiekt typu B instancji B wiec dalej bedzie brana metoda z klasy B
        A ac = new C();

        b.exceute();
        ab.exceute();
        ac.exceute();
    }
}
