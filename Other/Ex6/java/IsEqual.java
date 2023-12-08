public class IsEqual {
    static boolean isEqual1(int m, int n) {
        return m == n;
    }

    static boolean isEqual2(Integer m, Integer n) {
        return m == n;
    }

    public static void main(String[] args) {
        System.out.println(isEqual1(500, 500)); // sprawdzamy czy wartosci sa rowne
        System.out.println(isEqual2(500, 500)); // sprawdzamy z czy referencje sa rowne a ze
        // utworzylismy nowe obiekty to nie sa rowne
        // czyli nalezaloby uzyc equals, bo wtedy porownujemy wartosci
    }
}